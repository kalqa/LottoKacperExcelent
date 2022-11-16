package pl.lotto.numbergenerator;

public class NumberGeneratorConfiguration {

    // composition root
    WinningNumbersGeneratorFacade createForTests(RandomNumberGenerable generator) {
        WinningNumberValidator winningNumberValidator = new WinningNumberValidator();
        return new WinningNumbersGeneratorFacade(generator, winningNumberValidator);
    }
}
