package pl.lotto.resultannouncer;

import org.junit.jupiter.api.Test;
import pl.lotto.numberreceiver.dto.TicketDto;
import pl.lotto.resultannouncer.dto.ResultAnnouncerMessageDto;

import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

class ResultAnnouncerFacadeTest {

//    @Test
//    public void should_return_no_winning_message_if_there_is_no_hits() {
//        //given
//        ResultAnnouncerFacade resultAnnouncerFacade = new ResultAnnouncerFacade();
//        String hash = UUID.randomUUID().toString();
//        TicketDto ticket = TicketDto.builder()
//                .hash(hash)
//                .drawDate(LocalDate.now())
//                .build();
//        Set<Integer> winningNumbers = Set.of(4,5,6,7,8,9);
//        //when
//        ResultAnnouncerMessageDto messageDto = resultAnnouncerFacade.checkResults(hash);
//        //then
//        assertThat(messageDto).isEqualTo(ResultAnnouncerMessageDto.NO_HITS_TRY_AGAIN);
//
//    }

//    @Test
//    public void should_return_no_ticket_message_if_there_is_no_ticket_with_such_hash() {
//
//    }
//
//    @Test
//    public void should_return_no_results_yet_message_if_before_draw_due_date() {
//
//    }
//
//    @Test
//    public void should_return_winning_message_if_won() {
//        //given
//        ResultAnnouncerFacade resultAnnouncerFacade = new ResultAnnouncerFacade();
//        String hash = UUID.randomUUID().toString();
//        TicketDto ticket = TicketDto.builder()
//                .hash(hash)
//                .drawDate(LocalDate.now())
//                .build();
//        Set<Integer> winningNumbers = Set.of(4,5,6,7,8,9);
//        //when
//        ResultAnnouncerMessageDto messageDto = resultAnnouncerFacade.checkResults(hash);
//        //then
//        assertThat(messageDto).isEqualTo(ResultAnnouncerMessageDto.CONGRATULATIONS_YOU_WON);
//
//    }

}
