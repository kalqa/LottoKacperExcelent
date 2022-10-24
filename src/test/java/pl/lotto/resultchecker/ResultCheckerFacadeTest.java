package pl.lotto.resultchecker;

import org.junit.jupiter.api.Test;
import pl.lotto.resultchecker.dto.ResultCheckerResponseDto;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class ResultCheckerFacadeTest {


    @Test
    public void it_should_return_winning_message_when_input_numbers_are_winning_numbers(){
        //given
        ResultCheckerFacade resultCheckerFacade = new ResultCheckerFacade();
        Set<Integer> inputNumbers = Set.of(1,2,3,4,5,6);
        Set<Integer> winningNumbers = Set.of(1,2,3,4,5,6);
        //when
        ResultCheckerResponseDto response = resultCheckerFacade.generateResult(inputNumbers, winningNumbers);
        //then
        ResultCheckerResponseDto expectedResponse = ResultCheckerResponseDto.WINNING_NUMBERS;
        assertThat(response).isEqualTo(expectedResponse);
    }

    @Test
    public void it_should_return_loosing_message_when_input_numbers_are_not_winning_numbers(){
        //given
        ResultCheckerFacade resultCheckerFacade = new ResultCheckerFacade();
        Set<Integer> inputNumbers = Set.of(1,2,3,4,5,6);
        Set<Integer> winningNumbers = Set.of(1,2,3,4,5,7);
        //when
        ResultCheckerResponseDto response = resultCheckerFacade.generateResult(inputNumbers, winningNumbers);
        //then
        ResultCheckerResponseDto expectedResponse = ResultCheckerResponseDto.TRY_AGAIN;
        assertThat(response).isEqualTo(expectedResponse);
    }

}