package pl.lotto.numberreceiver;

import pl.lotto.numberreceiver.dto.NumberReceiverResponseDto;

import java.util.List;
import java.util.function.Predicate;

public class NumberReceiverFacade {


    public static final int QUANTITY_OF_NUMBERS_FROM_USER = 6;
    public static final int MAX_VALUE_NUMBER_FROM_USER = 99;
    public static final int MIN_VALUE_NUMBER_FROM_USER = 1;
    public static final String FAILED_USER_MESSAGE = "failed";
    public static final String SUCCESS_USER_MESSAGE = "correct";

    public NumberReceiverResponseDto inputNumbers(List<Integer> numbersFromUser) {
        boolean isOutOfRange = numbersFromUser.stream()
                .anyMatch(isNumberOutOfRange());
        if (isOutOfRange) {
            return new NumberReceiverResponseDto(FAILED_USER_MESSAGE);
        }
        if(!didUserGiveSixNumbers(numbersFromUser)){
            return new NumberReceiverResponseDto(FAILED_USER_MESSAGE);
        }
        return new NumberReceiverResponseDto(SUCCESS_USER_MESSAGE);
    }

    private static boolean didUserGiveSixNumbers(List<Integer> numbersFromUser) {
        return numbersFromUser.size() == QUANTITY_OF_NUMBERS_FROM_USER;
    }

    private static Predicate<Integer> isNumberOutOfRange() {
        return integer -> integer > MAX_VALUE_NUMBER_FROM_USER || integer < MIN_VALUE_NUMBER_FROM_USER;
    }
}
