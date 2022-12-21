package pl.lotto.resultchecker;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.lotto.numbergenerator.WinningNumbersGeneratorFacade;
import pl.lotto.numbergenerator.dto.WinningNumbersDto;
import pl.lotto.numberreceiver.NumberReceiverFacade;
import pl.lotto.numberreceiver.dto.TicketDto;
import pl.lotto.resultchecker.dto.ResultDto;
import pl.lotto.resultchecker.dto.WinnersDto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


class ResultCheckerFacadeTest {

    private final PlayerRepository playerRepository = new PlayerRepositoryTestImpl();

    WinningNumbersGeneratorFacade generatorFacade;
    NumberReceiverFacade receiverFacade;

    @BeforeEach
    public void setUp() {
        generatorFacade = mock(WinningNumbersGeneratorFacade.class);
        receiverFacade = mock(NumberReceiverFacade.class);
    }


    @Test
    public void it_should_generate_only_one_winner_if_there_is_only_one_winner() {
        //given
        LocalDateTime drawDate = LocalDateTime.of(2022, 12, 17, 12, 0, 0);
        when(generatorFacade.generateWinningNumbers()).thenReturn(WinningNumbersDto.builder()
                .winningNumbers(Set.of(1, 2, 3, 4, 5, 6))
                .build());
        when(receiverFacade.getAllTicketsByDate(drawDate)).thenReturn(
                List.of(TicketDto.builder()
                                .hash("001")
                                .numbers(Set.of(1, 2, 3, 4, 5, 6))
                                .drawDate(drawDate)
                                .build(),
                        TicketDto.builder()
                                .hash("002")
                                .numbers(Set.of(1, 2, 7, 8, 9, 10))
                                .drawDate(drawDate)
                                .build(),
                        TicketDto.builder()
                                .hash("003")
                                .numbers(Set.of(7, 8, 9, 10, 11, 12))
                                .drawDate(drawDate)
                                .build())
        );
        when(receiverFacade.nextDrawDate()).thenReturn(drawDate);
        ResultCheckerFacade resultCheckerFacade = new ResultCheckerConfiguration().createForTest(generatorFacade, receiverFacade, playerRepository);
        //when
        WinnersDto winnersDto = resultCheckerFacade.generateWinners();
        //then
        List<ResultDto> winners = winnersDto.getWinners();
        ResultDto winner = ResultDto.builder()
                .hash("001")
                .numbers(Set.of(1, 2, 3, 4, 5, 6))
                .hitNumbers(Set.of(1, 2, 3, 4, 5, 6))
                .drawDate(drawDate)
                .isWinner(true)
                .build();
        assertThat(winners).containsOnly(winner);

    }

    @Test
    public void it_should_generate_winners_if_there_is_more_than_one_winning_ticket() {
        //given
        LocalDateTime drawDate = LocalDateTime.of(2022, 12, 17, 12, 0, 0);
        when(generatorFacade.generateWinningNumbers()).thenReturn(WinningNumbersDto.builder()
                .winningNumbers(Set.of(1, 2, 3, 4, 5, 6))
                .build());
        when(receiverFacade.getAllTicketsByDate(drawDate)).thenReturn(
                List.of(TicketDto.builder()
                                .hash("001")
                                .numbers(Set.of(1, 2, 3, 4, 5, 6))
                                .drawDate(drawDate)
                                .build(),
                        TicketDto.builder()
                                .hash("002")
                                .numbers(Set.of(1, 2, 3, 7, 8, 9))
                                .drawDate(drawDate)
                                .build(),
                        TicketDto.builder()
                                .hash("003")
                                .numbers(Set.of(1, 2, 3, 4, 8, 9))
                                .drawDate(drawDate)
                                .build(),
                        TicketDto.builder()
                                .hash("004")
                                .numbers(Set.of(1, 2, 8, 9, 10, 11))
                                .drawDate(drawDate)
                                .build()));

        when(receiverFacade.nextDrawDate()).thenReturn(drawDate);
        ResultCheckerFacade resultCheckerFacade = new ResultCheckerConfiguration().createForTest(generatorFacade, receiverFacade, playerRepository);

        //when
        WinnersDto winnersDto = resultCheckerFacade.generateWinners();
        //then
        List<ResultDto> winners = winnersDto.getWinners();
        ResultDto winner = ResultDto.builder()
                .hash("001")
                .numbers(Set.of(1, 2, 3, 4, 5, 6))
                .hitNumbers(Set.of(1, 2, 3, 4, 5, 6))
                .drawDate(drawDate)
                .isWinner(true)
                .build();
        ResultDto winner2 = ResultDto.builder()
                .hash("002")
                .numbers(Set.of(1, 2, 3, 7, 8, 9))
                .hitNumbers(Set.of(1, 2, 3))
                .drawDate(drawDate)
                .isWinner(true)
                .build();
        ResultDto winner3 = ResultDto.builder()
                .hash("003")
                .numbers(Set.of(1, 2, 3, 4, 8, 9))
                .hitNumbers(Set.of(1, 2, 3, 4))
                .drawDate(drawDate)
                .isWinner(true)
                .build();
        assertThat(winners).containsOnly(winner, winner2, winner3);
    }

    @Test
    public void it_should_not_generate_winners_if_there_is_none_matching_ticket() {
        //given
        LocalDateTime drawDate = LocalDateTime.of(2022, 12, 17, 12, 0, 0);
        when(generatorFacade.generateWinningNumbers()).thenReturn(WinningNumbersDto.builder()
                .winningNumbers(Set.of(1, 2, 3, 4, 5, 6))
                .build());
        when(receiverFacade.getAllTicketsByDate(drawDate)).thenReturn(
                List.of(TicketDto.builder()
                                .hash("001")
                                .numbers(Set.of(7, 8, 9, 10, 11, 12))
                                .drawDate(drawDate)
                                .build(),
                        TicketDto.builder()
                                .hash("002")
                                .numbers(Set.of(7, 8, 9, 10, 11, 13))
                                .drawDate(drawDate)
                                .build(),
                        TicketDto.builder()
                                .hash("003")
                                .numbers(Set.of(7, 8, 9, 10, 11, 14))
                                .drawDate(drawDate)
                                .build())
        );
        when(receiverFacade.nextDrawDate()).thenReturn(drawDate);
        ResultCheckerFacade resultCheckerFacade = new ResultCheckerConfiguration().createForTest(generatorFacade, receiverFacade, playerRepository);

        //when
        WinnersDto winnersDto = resultCheckerFacade.generateWinners();
        //then
        List<ResultDto> winners = winnersDto.getWinners();
        assertThat(winners).isEmpty();
    }

    @Test
    public void it_should_generate_result_with_correct_credentials() {
        //given
        LocalDateTime drawDate = LocalDateTime.of(2022, 12, 17, 12, 0, 0);
        when(generatorFacade.generateWinningNumbers()).thenReturn(WinningNumbersDto.builder()
                .winningNumbers(Set.of(1, 2, 3, 4, 5, 6))
                .build());
        String hash = "001";
        when(receiverFacade.getAllTicketsByDate(drawDate)).thenReturn(
                List.of(TicketDto.builder()
                                .hash(hash)
                                .numbers(Set.of(7, 8, 9, 10, 11, 12))
                                .drawDate(drawDate)
                                .build(),
                        TicketDto.builder()
                                .hash("002")
                                .numbers(Set.of(7, 8, 9, 10, 11, 13))
                                .drawDate(drawDate)
                                .build(),
                        TicketDto.builder()
                                .hash("003")
                                .numbers(Set.of(7, 8, 9, 10, 11, 14))
                                .drawDate(drawDate)
                                .build())
        );
        when(receiverFacade.nextDrawDate()).thenReturn(drawDate);
        ResultCheckerFacade resultCheckerFacade = new ResultCheckerConfiguration().createForTest(generatorFacade, receiverFacade, playerRepository);
        resultCheckerFacade.generateWinners();
        //when

        ResultDto resultDto = resultCheckerFacade.generateResult(hash);
        //then
        ResultDto expectedResult = ResultDto.builder()
                .hash(hash)
                .numbers(Set.of(7, 8, 9, 10, 11, 12))
                .hitNumbers(Set.of())
                .drawDate(drawDate)
                .isWinner(false)
                .build();
        assertThat(resultDto).isEqualTo(expectedResult);
    }
}