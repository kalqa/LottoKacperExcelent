package pl.lotto.numbergenerator;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.lotto.numberreceiver.NumberReceiverFacade;

@Configuration
public class NumberGeneratorConfiguration {


    @Bean
    WinningNumbersGeneratorFacade winningNumbersGeneratorFacade( WinningNumbersRepository winningNumbersRepository, NumberReceiverFacade numberReceiverFacade) {
        RandomNumberGenerable randomNumberGenerator = new RandomGenerator();
        WinningNumberValidator winningNumberValidator = new WinningNumberValidator();
        return new WinningNumbersGeneratorFacade(randomNumberGenerator, winningNumberValidator,winningNumbersRepository, numberReceiverFacade);
    }


    WinningNumbersGeneratorFacade createForTest(RandomNumberGenerable generator, WinningNumbersRepository winningNumbersRepository, NumberReceiverFacade numberReceiverFacade) {
        WinningNumberValidator winningNumberValidator = new WinningNumberValidator();
        return new WinningNumbersGeneratorFacade(generator, winningNumberValidator,winningNumbersRepository, numberReceiverFacade);
    }

//    public WinningNumbersGeneratorFacade createForTest(RandomNumberGenerable generator) {
//    }
}
