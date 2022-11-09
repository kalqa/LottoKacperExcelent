package pl.lotto.numbergenerator;

import org.junit.jupiter.api.Test;
import pl.lotto.numbergenerator.dto.WinningNumbersDto;

import java.util.Comparator;
import java.util.HashSet;

import static org.assertj.core.api.Assertions.assertThat;

public class WinningNumbersGeneratorFacadeTest {

    @Test
    public void it_should_return_set_of_required_size() {
        //given
        WinningNumbersGeneratorFacade numbersGenerator = new WinningNumbersGeneratorFacade();
        //when
        WinningNumbersDto generatedNumbers = numbersGenerator.generateWinningNumbers();
        //then
        assertThat(generatedNumbers.getWinningNumbers().size()).isEqualTo(6);
    }

    @Test
    public void it_should_return_set_of_required_size_within_required_range() {
        //given
        WinningNumbersGeneratorFacade numbersGenerator = new WinningNumbersGeneratorFacade();
        int upperBand = 99;
        int lowerBand = 1;
        //when
        WinningNumbersDto generatedNumbers = numbersGenerator.generateWinningNumbers();
        //then
        Integer minValue = generatedNumbers.getWinningNumbers().stream().min(Comparator.comparing(Integer::valueOf)).get();
        Integer maxValue = generatedNumbers.getWinningNumbers().stream().max(Comparator.comparing(Integer::valueOf)).get();

        assertThat(minValue).isGreaterThanOrEqualTo(lowerBand);
        assertThat(maxValue).isLessThanOrEqualTo(upperBand);
    }

    @Test
    public void it_should_return_collection_of_unique_values() {
        //given
        WinningNumbersGeneratorFacade numbersGenerator = new WinningNumbersGeneratorFacade();
        //when
        WinningNumbersDto generatedNumbers = numbersGenerator.generateWinningNumbers();
        //then
        int generatedNumbersSize = new HashSet<>(generatedNumbers.getWinningNumbers()).size();
        assertThat(generatedNumbersSize).isEqualTo(6);
    }
}


