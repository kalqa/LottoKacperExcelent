package pl.lotto.numbergenerator;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.lotto.numberreceiver.NumberReceiverFacade;

@Configuration
public class WinningNumbersConfiguration {


    @Bean
    WinningNumbersGeneratorFacade winningNumbersGeneratorFacade(WinningNumbersRepository winningNumbersRepository, NumberReceiverFacade numberReceiverFacade, RandomNumberGenerable randomNumberGenerator, WinningNumbersFacadeProperties properties) {
        WinningNumberValidator winningNumberValidator = new WinningNumberValidator();
        return new WinningNumbersGeneratorFacade(randomNumberGenerator, winningNumberValidator, winningNumbersRepository, numberReceiverFacade, properties);
    }

    WinningNumbersGeneratorFacade createForTest(RandomNumberGenerable randomNumberGenerator, WinningNumbersRepository winningNumbersRepository, NumberReceiverFacade numberReceiverFacade) {
        WinningNumbersFacadeProperties winningNumbersConfiguration = WinningNumbersFacadeProperties.builder()
                .maximalWinningNumbers(6)
                .lowestNumber(1)
                .maxNumber(99)
                .build();
        return winningNumbersGeneratorFacade(winningNumbersRepository, numberReceiverFacade, randomNumberGenerator, winningNumbersConfiguration);
    }
}
