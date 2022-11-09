package pl.lotto.numbergenerator;

import java.util.Set;

public class WinningNumberGeneratorTestImpl implements RandomNumberGenerable {


    private final Set<Integer> generatedNumbers;

    WinningNumberGeneratorTestImpl(Set<Integer> generatedNumbers) {
        this.generatedNumbers = generatedNumbers;
    }

    WinningNumberGeneratorTestImpl() {
        generatedNumbers = Set.of(1, 2, 3, 4, 5, 6);
    }

    @Override
    public Set<Integer> generateRandomWinningNumbers() {
        return generatedNumbers;
    }
}
