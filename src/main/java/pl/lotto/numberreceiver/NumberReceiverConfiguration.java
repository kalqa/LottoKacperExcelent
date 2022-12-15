package pl.lotto.numberreceiver;

import java.time.Clock;

public class NumberReceiverConfiguration {


    NumberReceiverFacade createForTest(HashGenerable hashGenerator, Clock clock) {
        NumberValidator numberValidator = new NumberValidator();
        DrawDateGenerator drawDateGenerator = new DrawDateGenerator(clock);
        return new NumberReceiverFacade(numberValidator, drawDateGenerator, hashGenerator);
    }


}
