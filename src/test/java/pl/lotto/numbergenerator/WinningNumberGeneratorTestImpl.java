package pl.lotto.numbergenerator;

import java.util.Set;
import pl.lotto.numbergenerator.dto.SixRandomNumbersDto;


public class WinningNumberGeneratorTestImpl implements RandomNumberGenerable {

    private final Set<Integer> generatedNumbers;

    WinningNumberGeneratorTestImpl() {
        generatedNumbers = Set.of(1, 2, 3, 4, 5, 6);
    }

    WinningNumberGeneratorTestImpl(Set<Integer> generatedNumbers) {
        this.generatedNumbers = generatedNumbers;
    }

    @Override
    public SixRandomNumbersDto generateSixRandomNumbers(int count, int lowerBand, int upperBand) {
        return SixRandomNumbersDto.builder().numbers(generatedNumbers).build();
    }
}
