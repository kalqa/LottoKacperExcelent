package pl.lotto.numberreceiver;

import org.junit.jupiter.api.Test;
import pl.lotto.numberreceiver.dto.NumberReceiverResponseDto;
import pl.lotto.numberreceiver.dto.TicketDto;

import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

public class NumberReceiverFacadeTest {

    private final NumberValidator numberValidator;
    private final DrawDateGenerator drawDateGenerator;
    private final HashGenerator hashGenerator;


    public NumberReceiverFacadeTest(NumberValidator numberValidator, DrawDateGenerator drawDateGenerator, HashGenerator hashGenerator) {
        this.numberValidator = numberValidator;
        this.drawDateGenerator = drawDateGenerator;
        this.hashGenerator = hashGenerator;
    }



    @Test
    public void should_return_correct_message_when_user_input_six_numbers_in_range() {
        // given
        NumberReceiverFacade numberReceiverFacade = new NumberReceiverFacade(numberValidator,drawDateGenerator,hashGenerator);
        Set<Integer> numbersFromUser = Set.of(1, 2, 3, 4, 5, 6);
        LocalDate nextDrawDate = drawDateGenerator.getNextDrawDate();

        TicketDto generatedTicket = TicketDto.builder()
                .hash(hashGenerator.getHash())
                .numbers(numbersFromUser)
                .drawDate(nextDrawDate)
                .build();

        // when
        String response = numberReceiverFacade.inputNumbers(numbersFromUser).message();

        // then
        String expectedResponse = new NumberReceiverResponseDto(generatedTicket,ValidationResult.INPUT_SUCCESS.info).message();
        assertThat(response).isEqualTo(expectedResponse);
    }

//    @Test
//    public void should_return_failed_message_when_user_input_six_numbers_but_one_number_is_out_of_range() {
//        // given
//        NumberReceiverFacade numberReceiverFacade = new NumberReceiverFacade(numberValidator,drawDateGenerator);
//        Set<Integer> numbersFromUser = Set.of(1, 2, 3, 4, 5, 100);
//
//        // when
//        NumberReceiverResponseDto response = numberReceiverFacade.inputNumbers(numbersFromUser);
//
//        // then
//        NumberReceiverResponseDto expectedResponse = NumberReceiverResponseDto.INPUT_ERROR;
//        assertThat(response).isEqualTo(expectedResponse);
//    }
//
//    @Test
//    public void should_return_failed_message_when_user_input_six_numbers_but_one_number_is_out_of_range_and_is_negative() {
//        // given
//        NumberReceiverFacade numberReceiverFacade = new NumberReceiverFacade(numberValidator,drawDateGenerator);
//        Set<Integer> numbersFromUser = Set.of(1, 2, 3, -4, 5, 6);
//
//        // when
//        NumberReceiverResponseDto response = numberReceiverFacade.inputNumbers(numbersFromUser);
//
//        // then
//        NumberReceiverResponseDto expectedResponse = NumberReceiverResponseDto.INPUT_ERROR;
//        assertThat(response).isEqualTo(expectedResponse);
//    }
//
//    @Test
//    public void should_return_failed_message_when_user_input_less_than_six_numbers() {
//        // given
//        NumberReceiverFacade numberReceiverFacade = new NumberReceiverFacade(numberValidator,drawDateGenerator);
//        Set<Integer> numbersFromUser = Set.of(1, 2, 3, 4, 5);
//
//        // when
//        NumberReceiverResponseDto response = numberReceiverFacade.inputNumbers(numbersFromUser);
//
//        // then
//        NumberReceiverResponseDto expectedResponse = NumberReceiverResponseDto.INPUT_ERROR;
//        assertThat(response).isEqualTo(expectedResponse);
//    }
//
//    @Test
//    public void should_return_failed_message_when_user_input_more_than_six_numbers() {
//        // given
//        NumberReceiverFacade numberReceiverFacade = new NumberReceiverFacade(numberValidator,drawDateGenerator);
//        Set<Integer> numbersFromUser = Set.of(1, 2, 3, 4, 5, 6, 7);
//
//        // when
//        NumberReceiverResponseDto response = numberReceiverFacade.inputNumbers(numbersFromUser);
//
//        // then
//        NumberReceiverResponseDto expectedResponse = NumberReceiverResponseDto.INPUT_ERROR;
//        assertThat(response).isEqualTo(expectedResponse);
//    }
}
