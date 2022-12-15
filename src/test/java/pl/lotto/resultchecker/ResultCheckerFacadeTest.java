package pl.lotto.resultchecker;

import org.junit.jupiter.api.Test;
import pl.lotto.numbergenerator.WinningNumbersGeneratorFacade;
import pl.lotto.numbergenerator.dto.WinningNumbersDto;
import pl.lotto.numberreceiver.NumberReceiverFacade;
import pl.lotto.numberreceiver.dto.TicketDto;
import pl.lotto.resultchecker.dto.WinnerDto;
import pl.lotto.resultchecker.dto.WinnersDto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import static org.assertj.core.api.Assertions.assertThat;


class ResultCheckerFacadeTest {


    @Test
    public void it_should_generate_one_winner_if_there_is_only_one_winner() {
        //given
        LocalDateTime drawDate = LocalDateTime.of(2022, 12, 17, 12, 0, 0);
        WinningNumbersGeneratorFacade generatorFacade = mock(WinningNumbersGeneratorFacade.class);
        NumberReceiverFacade receiverFacade = mock(NumberReceiverFacade.class);
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
                                .numbers(Set.of(1, 2, 3, 4, 5, 7))
                                .drawDate(drawDate)
                                .build(),
                        TicketDto.builder()
                                .hash("003")
                                .numbers(Set.of(1, 2, 3, 4, 5, 8))
                                .drawDate(drawDate)
                                .build())
        );
        when(receiverFacade.nextDrawDate()).thenReturn(drawDate);
        ResultCheckerFacade resultCheckerFacade = new ResultCheckerFacade(generatorFacade, receiverFacade);

        //when
        List<WinnerDto> winners = resultCheckerFacade.generateWinners().getWinners();
        //then
        WinnerDto winner = WinnerDto.builder()
                .hash("001")
                .numbers(Set.of(1, 2, 3, 4, 5, 6))
                .drawDate(drawDate)
                .build();
        assertThat(winners).contains(winner);
    }

    @Test
    public void it_should_generate_winners_if_there_is_more_than_one_winning_ticket() {
        //given
        LocalDateTime drawDate = LocalDateTime.of(2022, 12, 17, 12, 0, 0);
        WinningNumbersGeneratorFacade generatorFacade = mock(WinningNumbersGeneratorFacade.class);
        NumberReceiverFacade receiverFacade = mock(NumberReceiverFacade.class);
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
                                .numbers(Set.of(1, 2, 3, 4, 5, 6))
                                .drawDate(drawDate)
                                .build(),
                        TicketDto.builder()
                                .hash("003")
                                .numbers(Set.of(1, 2, 3, 4, 5, 8))
                                .drawDate(drawDate)
                                .build())
        );
        when(receiverFacade.nextDrawDate()).thenReturn(drawDate);
        ResultCheckerFacade resultCheckerFacade = new ResultCheckerFacade(generatorFacade, receiverFacade);

        //when
        List<WinnerDto> winners = resultCheckerFacade.generateWinners().getWinners();
        //then
        WinnerDto winner = WinnerDto.builder()
                .hash("001")
                .numbers(Set.of(1, 2, 3, 4, 5, 6))
                .drawDate(drawDate)
                .build();
        WinnerDto winner2 = WinnerDto.builder()
                .hash("001")
                .numbers(Set.of(1, 2, 3, 4, 5, 6))
                .drawDate(drawDate)
                .build();
        assertThat(winners).contains(winner,winner2);
    }

    @Test
    public void it_should_not_generate_winners_if_there_is_none_matching_ticket() {
        //given
        LocalDateTime drawDate = LocalDateTime.of(2022, 12, 17, 12, 0, 0);
        WinningNumbersGeneratorFacade generatorFacade = mock(WinningNumbersGeneratorFacade.class);
        NumberReceiverFacade receiverFacade = mock(NumberReceiverFacade.class);
        when(generatorFacade.generateWinningNumbers()).thenReturn(WinningNumbersDto.builder()
                .winningNumbers(Set.of(1, 2, 3, 4, 5, 6))
                .build());
        when(receiverFacade.getAllTicketsByDate(drawDate)).thenReturn(
                List.of(TicketDto.builder()
                                .hash("001")
                                .numbers(Set.of(1, 2, 3, 4, 5, 9))
                                .drawDate(drawDate)
                                .build(),
                        TicketDto.builder()
                                .hash("002")
                                .numbers(Set.of(1, 2, 3, 4, 5, 7))
                                .drawDate(drawDate)
                                .build(),
                        TicketDto.builder()
                                .hash("003")
                                .numbers(Set.of(1, 2, 3, 4, 5, 8))
                                .drawDate(drawDate)
                                .build())
        );
        when(receiverFacade.nextDrawDate()).thenReturn(drawDate);
        ResultCheckerFacade resultCheckerFacade = new ResultCheckerFacade(generatorFacade, receiverFacade);

        //when
        List<WinnerDto> winners = resultCheckerFacade.generateWinners().getWinners();
        //then
        assertThat(winners).isEmpty();
    }
}