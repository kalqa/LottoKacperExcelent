package pl.lotto.numberreceiver;

import pl.lotto.numberreceiver.dto.NumberReceiverResponseDto;

import java.util.Set;

public class NumberReceiverFacade {

    private static final int QUANTITY_OF_NUMBERS_FROM_USER = 6;
    private static final int MAX_VALUE_NUMBER_FROM_USER = 99;
    private static final int MIN_VALUE_NUMBER_FROM_USER = 1;


    public NumberReceiverResponseDto inputNumbers(Set<Integer> numbersFromUser) {

        if (isNumberOutOfRange(numbersFromUser)) {
            return NumberReceiverResponseDto.FAILED;
        }
        if(!didUserGiveSixNumbers(numbersFromUser)){
            return NumberReceiverResponseDto.FAILED;
        }
        return NumberReceiverResponseDto.SUCCESS;
    }

    private static boolean didUserGiveSixNumbers(Set<Integer> numbersFromUser) {
        return numbersFromUser.size() == QUANTITY_OF_NUMBERS_FROM_USER;
    }

    private static boolean isNumberOutOfRange(Set<Integer> numbersFromUser) {
        for (Integer number : numbersFromUser) {
            if(number > MAX_VALUE_NUMBER_FROM_USER || number < MIN_VALUE_NUMBER_FROM_USER)
                return true;
        }
        return false;
    }
}
