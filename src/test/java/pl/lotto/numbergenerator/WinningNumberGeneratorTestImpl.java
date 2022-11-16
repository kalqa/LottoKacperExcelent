package pl.lotto.numbergenerator;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.Set;


public class WinningNumberGeneratorTestImpl implements RandomNumberGenerable {

    private final Set<Integer> generatedNumbers;

    WinningNumberGeneratorTestImpl() {
        generatedNumbers = Set.of(1,2,3,4,5,6);
    }

    WinningNumberGeneratorTestImpl(Set<Integer> generatedNumbers) {
        this.generatedNumbers = generatedNumbers;
    }

    @Override
    public Set<Integer> generateRandomWInningNumbers() {
        return generatedNumbers;
    }
}
