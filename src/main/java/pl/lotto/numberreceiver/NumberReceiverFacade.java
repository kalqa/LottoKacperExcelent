package pl.lotto.numberreceiver;

import java.util.List;
import pl.lotto.numberreceiver.dto.NumberReceiverResponseDto;

public class NumberReceiverFacade {


    public NumberReceiverResponseDto inputNumbers(List<Integer> integers) {
        return new NumberReceiverResponseDto("correct");
    }
}
