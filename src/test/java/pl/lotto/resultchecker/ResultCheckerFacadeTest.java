package pl.lotto.resultchecker;

import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import pl.lotto.numbergenerator.WinningNumbersGeneratorFacade;
import pl.lotto.numbergenerator.dto.WinningNumbersDto;
import pl.lotto.numberreceiver.NumberReceiverFacade;
import pl.lotto.numberreceiver.dto.TicketDto;
import pl.lotto.resultchecker.dto.WinnerDto;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ResultCheckerFacadeTest {


    @Test
    public void it_should_return_ticket_with_all_matching_numbers_if_ticket_is_a_winning_ticket() {
        //given
        WinningNumbersGeneratorFacade generatorFacade = mock(WinningNumbersGeneratorFacade.class);
        NumberReceiverFacade receiverFacade = mock(NumberReceiverFacade.class);
        when(generatorFacade.generateWinningNumbers()).thenReturn(WinningNumbersDto.builder()
                .winningNumbers(Set.of(1, 2, 3, 4, 5, 6))
                .build());
        when(receiverFacade.getAllTicketsByDate()).thenReturn(
                List.of(TicketDto.builder()
                        .hash("123")
                        .numbers(Set.of(1, 2, 3, 4, 5, 6))
                        .build())
        );
        ResultCheckerFacade resultCheckerFacade = new ResultCheckerFacade(generatorFacade, receiverFacade);
        //when
        List<WinnerDto> winnerDtos = resultCheckerFacade.generateWinners();
        //then
    }


//
//
//    @Test
//    public void it_should_return_ticket_with_all_matching_numbers_if_ticket_is_a_winning_ticket() {
//        //given
//        ResultCheckerFacade resultCheckerFacade = new ResultCheckerFacade();
//        String hash = UUID.randomUUID().toString();
//        TicketDto ticket = TicketDto.builder()
//                .hash(hash)
//                .numbers(Set.of(1, 2, 3, 4, 5, 6))
//                .drawDate(LocalDateTime.now())
//                .build();
//        Set<Integer> winningNumbers = Set.of(1, 2, 3, 4, 5, 6);
//        //when
//        Set<Integer> resultNumbers = resultCheckerFacade.generateResult(ticket, winningNumbers).getNumbers();
//        //then
//        Set<Integer> expectedResult = Set.of(1, 2, 3, 4, 5, 6);
//        assertThat(resultNumbers).isEqualTo(expectedResult);
//    }
//
//    @Test
//    public void it_should_return_ticket_with_empty_set_if_no_matching_numbers() {
//        //given
//        ResultCheckerFacade resultCheckerFacade = new ResultCheckerFacade();
//        String hash = UUID.randomUUID().toString();
//        TicketDto ticket = TicketDto.builder()
//                .hash(hash)
//                .numbers(Set.of(1, 2, 3, 4, 5, 6))
//                .drawDate(LocalDateTime.now())
//                .build();
//        Set<Integer> winningNumbers = Set.of(7, 8, 9, 10, 11, 12);
//        //when
//        Set<Integer> resultNumbers = resultCheckerFacade.generateResult(ticket, winningNumbers).getNumbers();
//        //then
//        assertThat(resultNumbers).isEmpty();
//    }
//
//    @Test
//    public void it_should_return_ticket_with_only_matching_numbers() {
//        //given
//        ResultCheckerFacade resultCheckerFacade = new ResultCheckerFacade();
//        String hash = UUID.randomUUID().toString();
//        TicketDto ticket = TicketDto.builder()
//                .hash(hash)
//                .numbers(Set.of(1, 2, 3, 4, 5, 6))
//                .drawDate(LocalDateTime.now())
//                .build();
//        Set<Integer> winningNumbers = Set.of(4, 5, 6, 7, 8, 9);
//        //when
//        Set<Integer> resultNumbers = resultCheckerFacade.generateResult(ticket, winningNumbers).getNumbers();
//        //then
//        Set<Integer> expectedResult = Set.of(4, 5, 6);
//        assertThat(resultNumbers).isEqualTo(expectedResult);
//    }
//
//    //TODO:omówić z Bartkiem dlaczego zawsze  test przechodzi, czy w ogole testowac zawartosc metody czy tylko to co zwraca, jak tak to czy nie zrobic osobnej metody dla dodawania wynikow
//    //TODO: w sumie sie sam zagubiłem o co mi tutaj chodziło
//    @Test
//    public void it_should_add_ticket_to_winners_list_if_winning_ticket() {
//        //given
//        ResultCheckerFacade resultCheckerFacade = new ResultCheckerFacade();
//        String hash = UUID.randomUUID().toString();
//        TicketDto ticket = TicketDto.builder()
//                .hash(hash)
//                .numbers(Set.of(1, 2, 3, 4, 5, 6))
//                .drawDate(LocalDateTime.now())
//                .build();
//        Set<Integer> winningNumbers = Set.of(1, 2, 3, 4, 5, 6);
//        //when
//        ResultTicketDto winningTicket = resultCheckerFacade.generateResult(ticket, winningNumbers);
//        //then
//        WinnersDto winnersDto = WinnersDto.builder()
//                .winners(new ArrayList<>())
//                .build();
//        List<ResultTicketDto> winners = winnersDto.getWinners();
//        winners.add(winningTicket);
//        assertThat(winners).isNotEmpty();
//    }
//
//    @Test
//    public void it_should_not_add_ticket_to_winners_list_if_there_is_no_winning_ticket() {
//        //given
//        ResultCheckerFacade resultCheckerFacade = new ResultCheckerFacade();
//        String hash = UUID.randomUUID().toString();
//        TicketDto ticket = TicketDto.builder()
//                .hash(hash)
//                .numbers(Set.of(1, 2, 3, 4, 5, 6))
//                .drawDate(LocalDateTime.now())
//                .build();
//        Set<Integer> winningNumbers = Set.of(1, 3, 4, 5, 6, 7);
//        //when
//        resultCheckerFacade.generateResult(ticket, winningNumbers);
//        //then
//        WinnersDto winnersDto = WinnersDto.builder()
//                .winners(new ArrayList<>())
//                .build();
//        List<ResultTicketDto> winners = winnersDto.getWinners();
//        assertThat(winners).isEmpty();
//    }
}
