package pl.lotto.numberreceiver;

import org.junit.jupiter.api.Test;
import pl.lotto.numberreceiver.dto.NumberReceiverResponseDto;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class NumberReceiverFacadeTest {

    @Test
    public void should_return_correct_message_when_user_input_six_numbers_in_range() {
        // given
        NumberReceiverFacade numberReceiverFacade = new NumberReceiverFacade();
        Set<Integer> numbersFromUser = Set.of(1, 2, 3, 4, 5, 6);

        // when
        NumberReceiverResponseDto response = numberReceiverFacade.inputNumbers(numbersFromUser);

        // then
        NumberReceiverResponseDto expectedResponse = NumberReceiverResponseDto.SUCCESS;
        assertThat(response).isEqualTo(expectedResponse);
    }

    @Test
    public void should_return_failed_message_when_user_input_six_numbers_but_one_number_is_out_of_range() {
        // given
        NumberReceiverFacade numberReceiverFacade = new NumberReceiverFacade();
        Set<Integer> numbersFromUser = Set.of(1, 2, 3, 4, 5, 100);

        // when
        NumberReceiverResponseDto response = numberReceiverFacade.inputNumbers(numbersFromUser);

        // then
        NumberReceiverResponseDto expectedResponse = NumberReceiverResponseDto.FAILED;
        assertThat(response).isEqualTo(expectedResponse);
    }

    @Test
    public void should_return_failed_message_when_user_input_six_numbers_but_one_number_is_out_of_range_and_is_negative() {
        // given
        NumberReceiverFacade numberReceiverFacade = new NumberReceiverFacade();
        Set<Integer> numbersFromUser = Set.of(1, 2, 3, -4, 5, 6);

        // when
        NumberReceiverResponseDto response = numberReceiverFacade.inputNumbers(numbersFromUser);

        // then
        NumberReceiverResponseDto expectedResponse = NumberReceiverResponseDto.FAILED;
        assertThat(response).isEqualTo(expectedResponse);
    }

    @Test
    public void should_return_failed_message_when_user_input_less_than_six_numbers() {
        // given
        NumberReceiverFacade numberReceiverFacade = new NumberReceiverFacade();
        Set<Integer> numbersFromUser = Set.of(1, 2, 3, 4, 5);

        // when
        NumberReceiverResponseDto response = numberReceiverFacade.inputNumbers(numbersFromUser);

        // then
        NumberReceiverResponseDto expectedResponse = NumberReceiverResponseDto.FAILED;
        assertThat(response).isEqualTo(expectedResponse);
    }

    @Test
    public void should_return_failed_message_when_user_input_more_than_six_numbers() {
        // given
        NumberReceiverFacade numberReceiverFacade = new NumberReceiverFacade();
        Set<Integer> numbersFromUser = Set.of(1, 2, 3, 4, 5, 6, 7);

        // when
        NumberReceiverResponseDto response = numberReceiverFacade.inputNumbers(numbersFromUser);

        // then
        NumberReceiverResponseDto expectedResponse = NumberReceiverResponseDto.FAILED;
        assertThat(response).isEqualTo(expectedResponse);
    }
}
