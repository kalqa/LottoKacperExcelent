package pl.lotto.numbergenerator;

public class NumberGeneratorConfiguration {

    WinningNumbersGeneratorFacade createForTest(RandomNumberGenerable generator) {
        WinningNumberValidator winningNumberValidator = new WinningNumberValidator();
        return new WinningNumbersGeneratorFacade(generator, winningNumberValidator);
    }
}
