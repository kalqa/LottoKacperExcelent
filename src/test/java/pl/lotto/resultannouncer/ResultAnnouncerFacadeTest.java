package pl.lotto.resultannouncer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.lotto.resultannouncer.dto.ResponseDto;
import pl.lotto.resultannouncer.dto.ResultAnnouncerResponseDto;
import pl.lotto.resultchecker.ResultCheckerFacade;
import pl.lotto.resultchecker.dto.ResultDto;

import java.time.LocalDateTime;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static pl.lotto.resultannouncer.MessageResponse.*;

class ResultAnnouncerFacadeTest {


    ResponseRepository responseRepository = new ResponseRepositoryTestImpl();
    ResultCheckerFacade resultCheckerFacade;

    @BeforeEach
    public void setUp() {
        resultCheckerFacade = mock(ResultCheckerFacade.class);
    }

    @Test
    public void it_should_return_response_with_lose_message_if_ticket_is_not_winning_ticket() {
        //given
        LocalDateTime drawDate = LocalDateTime.of(2022, 12, 17, 12, 0, 0);
        String hash = "123";
        ResultAnnouncerFacade resultAnnouncerFacade = new ResultAnnouncerFacade(resultCheckerFacade, responseRepository);
        ResultDto resultDto = ResultDto.builder()
                .hash("123")
                .numbers(Set.of(1, 2, 3, 4, 5, 6))
                .hitNumbers(Set.of())
                .drawDate(drawDate)
                .isWinner(false)
                .build();
        when(resultCheckerFacade.generateResult(hash)).thenReturn(resultDto);
        //when
        ResultAnnouncerResponseDto resultAnnouncerResponseDto = resultAnnouncerFacade.checkResult(hash);
        //then
        ResponseDto responseDto = ResponseDto.builder()
                .hash("123")
                .numbers(Set.of(1, 2, 3, 4, 5, 6))
                .hitNumbers(Set.of())
                .drawDate(drawDate)
                .isWinner(false)
                .build();

        ResultAnnouncerResponseDto expectedResult = new ResultAnnouncerResponseDto(responseDto,LOSE_MESSAGE.info);
        assertThat(resultAnnouncerResponseDto).isEqualTo(expectedResult);
    }

    @Test
    public void it_should_return_response_with_win_message_if_ticket_is_winning_ticket() {
        //given
        LocalDateTime drawDate = LocalDateTime.of(2022, 12, 17, 12, 0, 0);
        String hash = "123";
        ResultAnnouncerFacade resultAnnouncerFacade = new ResultAnnouncerFacade(resultCheckerFacade, responseRepository);
        ResultDto resultDto = ResultDto.builder()
                .hash("123")
                .numbers(Set.of(1, 2, 3, 4, 5, 6))
                .hitNumbers(Set.of(1,2,3,4,9,0))
                .drawDate(drawDate)
                .isWinner(true)
                .build();
        when(resultCheckerFacade.generateResult(hash)).thenReturn(resultDto);
        //when
        ResultAnnouncerResponseDto resultAnnouncerResponseDto = resultAnnouncerFacade.checkResult(hash);
        //then
        ResponseDto responseDto = ResponseDto.builder()
                .hash("123")
                .numbers(Set.of(1, 2, 3, 4, 5, 6))
                .hitNumbers(Set.of(1,2,3,4,9,0))
                .drawDate(drawDate)
                .isWinner(true)
                .build();

        ResultAnnouncerResponseDto expectedResult = new ResultAnnouncerResponseDto(responseDto,WIN_MESSAGE.info);
        assertThat(resultAnnouncerResponseDto).isEqualTo(expectedResult);
    }
    @Test
    public void it_should_return_response_with_wait_message_if_date_is_before_announcement_time() {
        //given
        LocalDateTime drawDate = LocalDateTime.of(2022, 12, 31, 12, 0, 0);
        String hash = "123";
        ResultAnnouncerFacade resultAnnouncerFacade = new ResultAnnouncerFacade(resultCheckerFacade, responseRepository);
        ResultDto resultDto = ResultDto.builder()
                .hash("123")
                .numbers(Set.of(1, 2, 3, 4, 5, 6))
                .hitNumbers(Set.of(1,2,3,4,9,0))
                .drawDate(drawDate)
                .isWinner(true)
                .build();
        when(resultCheckerFacade.generateResult(hash)).thenReturn(resultDto);
        //when
        ResultAnnouncerResponseDto resultAnnouncerResponseDto = resultAnnouncerFacade.checkResult(hash);
        //then
        ResponseDto responseDto = ResponseDto.builder()
                .hash("123")
                .numbers(Set.of(1, 2, 3, 4, 5, 6))
                .hitNumbers(Set.of(1,2,3,4,9,0))
                .drawDate(drawDate)
                .isWinner(true)
                .build();

        ResultAnnouncerResponseDto expectedResult = new ResultAnnouncerResponseDto(responseDto,WAIT_MESSAGE.info);
        assertThat(resultAnnouncerResponseDto).isEqualTo(expectedResult);
    }

    @Test
    public void it_should_return_response_with_hash_does_not_exist_message_if_hash_does_not_exist() {
        //given
        String hash = "123";
        ResultAnnouncerFacade resultAnnouncerFacade = new ResultAnnouncerFacade(resultCheckerFacade, responseRepository);

        when(resultCheckerFacade.generateResult(hash)).thenReturn(null);
        //when
        ResultAnnouncerResponseDto resultAnnouncerResponseDto = resultAnnouncerFacade.checkResult(hash);
        //then
        ResultAnnouncerResponseDto expectedResult = new ResultAnnouncerResponseDto(null,HASH_DOES_NOT_EXIST_MESSAGE.info);
        assertThat(resultAnnouncerResponseDto).isEqualTo(expectedResult);
    }
}