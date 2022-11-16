package pl.lotto.numberreceiver;

import org.junit.jupiter.api.Test;
import pl.lotto.numberreceiver.dto.NumberReceiverResponseDto;
import pl.lotto.numberreceiver.dto.TicketDto;

import java.time.LocalDate;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class NumberReceiverFacadeTest {


    @Test
    public void it_should_return_correct_response_when_user_input_six_numbers_in_range() {
        // given
        NumberValidator numberValidator = new NumberValidator();
        DrawDateGenerator drawDateGenerator = new DrawDateGenerator();
        HashGenerable hashGenerator = new HashGeneratorTestImpl();
        NumberReceiverFacade numberReceiverFacade = new NumberReceiverFacade(numberValidator, drawDateGenerator, hashGenerator);
        Set<Integer> numbersFromUser = Set.of(1, 2, 3, 4, 5, 6);
        LocalDate nextDrawDate = drawDateGenerator.getNextDrawDate();

        TicketDto generatedTicket = TicketDto.builder()
                .hash(hashGenerator.getHash())
                .numbers(numbersFromUser)
                .drawDate(nextDrawDate)
                .build();

        // when
        NumberReceiverResponseDto response = numberReceiverFacade.inputNumbers(numbersFromUser);

        // then
        NumberReceiverResponseDto expectedResponse = new NumberReceiverResponseDto(generatedTicket, ValidationResult.INPUT_SUCCESS.info);
        assertThat(response).isEqualTo(expectedResponse);
        //TODO: dopytać czy tak jest okej hash generator
    }

    @Test
    public void it_should_return_failed_message_when_user_input_six_numbers_but_one_number_is_out_of_range() {
        // given
        NumberValidator numberValidator = new NumberValidator();
        DrawDateGenerator drawDateGenerator = new DrawDateGenerator();
        HashGenerator hashGenerator = new HashGenerator();
        NumberReceiverFacade numberReceiverFacade = new NumberReceiverFacade(numberValidator, drawDateGenerator, hashGenerator);
        Set<Integer> numbersFromUser = Set.of(1, 2, 3, 4, 5, 100);

        // when
        NumberReceiverResponseDto response = numberReceiverFacade.inputNumbers(numbersFromUser);

        // then
        NumberReceiverResponseDto expectedResponse = new NumberReceiverResponseDto(null, ValidationResult.NOT_IN_RANGE.info);
        assertThat(response).isEqualTo(expectedResponse);
    }

    @Test
    public void it_should_return_failed_message_when_user_input_six_numbers_but_one_number_is_out_of_range_and_is_negative() {
        // given
        NumberValidator numberValidator = new NumberValidator();
        DrawDateGenerator drawDateGenerator = new DrawDateGenerator();
        HashGenerator hashGenerator = new HashGenerator();
        NumberReceiverFacade numberReceiverFacade = new NumberReceiverFacade(numberValidator, drawDateGenerator, hashGenerator);
        Set<Integer> numbersFromUser = Set.of(1, 2, 3, -4, 5, 6);

        // when
        NumberReceiverResponseDto response = numberReceiverFacade.inputNumbers(numbersFromUser);

        // then
        NumberReceiverResponseDto expectedResponse = new NumberReceiverResponseDto(null, ValidationResult.NOT_IN_RANGE.info);
        assertThat(response).isEqualTo(expectedResponse);
    }

    @Test
    public void it_should_return_failed_message_when_user_input_less_than_six_numbers() {
        // given
        NumberValidator numberValidator = new NumberValidator();
        DrawDateGenerator drawDateGenerator = new DrawDateGenerator();
        HashGenerator hashGenerator = new HashGenerator();
        NumberReceiverFacade numberReceiverFacade = new NumberReceiverFacade(numberValidator, drawDateGenerator, hashGenerator);
        Set<Integer> numbersFromUser = Set.of(1, 2, 3, 4, 5);

        // when
        NumberReceiverResponseDto response = numberReceiverFacade.inputNumbers(numbersFromUser);

        // then
        NumberReceiverResponseDto expectedResponse = new NumberReceiverResponseDto(null, ValidationResult.NOT_SIX_NUMBERS_GIVEN.info);
        assertThat(response).isEqualTo(expectedResponse);
    }

    @Test
    public void it_should_return_failed_message_when_user_input_more_than_six_numbers() {
        // given
        NumberValidator numberValidator = new NumberValidator();
        DrawDateGenerator drawDateGenerator = new DrawDateGenerator();
        HashGenerator hashGenerator = new HashGenerator();
        NumberReceiverFacade numberReceiverFacade = new NumberReceiverFacade(numberValidator, drawDateGenerator, hashGenerator);
        Set<Integer> numbersFromUser = Set.of(1, 2, 3, 4, 5, 6, 7);

        // when
        NumberReceiverResponseDto response = numberReceiverFacade.inputNumbers(numbersFromUser);

        // then
        NumberReceiverResponseDto expectedResponse = new NumberReceiverResponseDto(null, ValidationResult.NOT_SIX_NUMBERS_GIVEN.info);
        assertThat(response).isEqualTo(expectedResponse);
    }

    @Test
    public void it_should_return_correct_hash() {
        // given
        NumberValidator numberValidator = new NumberValidator();
        DrawDateGenerator drawDateGenerator = new DrawDateGenerator();
        HashGenerable hashGenerator = new HashGenerator();
        NumberReceiverFacade numberReceiverFacade = new NumberReceiverFacade(numberValidator, drawDateGenerator, hashGenerator);
        Set<Integer> numbersFromUser = Set.of(1, 2, 3, 4, 5, 6);
        LocalDate nextDrawDate = drawDateGenerator.getNextDrawDate();

        // when
        String response = numberReceiverFacade.inputNumbers(numbersFromUser).ticketDto().getHash();

        // then
        assertThat(response).hasSize(36);
        //TODO: dopytać czy tak jest okej hash generator
    }

//    @Test
//    public void it_should_return_correct_draw_date() {
//        // given
//        NumberValidator numberValidator = new NumberValidator();
//        DrawDateGenerator drawDateGenerator = new DrawDateGenerator();
//        HashGenerable hashGenerator = new HashGenerator();
//        NumberReceiverFacade numberReceiverFacade = new NumberReceiverFacade(numberValidator, drawDateGenerator, hashGenerator);
//        Set<Integer> numbersFromUser = Set.of(1, 2, 3, 4, 5, 6);
//
//        // when
//        LocalDate testedDrawDate = numberReceiverFacade.inputNumbers(numbersFromUser).ticketDto().getDrawDate();
//
//        // then
//        Clock clock = Clock;
//        LocalDate expectedDrawDate = LocalDate.now(clock);
//        assertThat(testedDrawDate).isEqualTo(expectedDrawDate);
//        //TODO: nie wiem jak się do tego dobrać, jak ustawić datę i czas wygenerowania biletu
//    }
}
