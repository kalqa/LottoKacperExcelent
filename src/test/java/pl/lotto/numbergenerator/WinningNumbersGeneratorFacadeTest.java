package pl.lotto.numbergenerator;

import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.Test;
import pl.lotto.numbergenerator.dto.WinningNumbersDto;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class WinningNumbersGeneratorFacadeTest {

    @Test
    public void it_should_return_set_of_required_size() {
        //given
        RandomNumberGenerable generator = new WinningNumberGenerator();
        WinningNumbersGeneratorFacade numbersGenerator = new WinningNumbersGeneratorFacade(generator);
        //when
        WinningNumbersDto generatedNumbers = numbersGenerator.generateWinningNumbers();
        //then
        assertThat(generatedNumbers.getWinningNumbers().size()).isEqualTo(6);
    }

    @Test
    public void it_should_return_set_of_required_size_within_required_range() {
        //given
//        RandomNumberGenerable generator = new WinningNumberGeneratorTestImpl();
        RandomNumberGenerable generator = new WinningNumberGenerator();
        WinningNumbersGeneratorFacade numbersGenerator = new WinningNumbersGeneratorFacade(generator);
        //when
        WinningNumbersDto generatedNumbers = numbersGenerator.generateWinningNumbers();
        //then
        int lowerBand = 1;
        int upperBand = 99;
        boolean allMatch = generatedNumbers.getWinningNumbers()
                .stream()
                .allMatch(number -> number >= lowerBand && number <= upperBand);
        System.out.println(generatedNumbers);
        assertThat(allMatch).isTrue();
//
//
//        assertThat(minValue).isb(lowerBand);
//        assertThat(maxValue).isLessThanOrEqualTo(upperBand);
        // assert object pattern
    }

    @Test
    public void should_throw_exception_when_generated_number_was_out_range() {
        //given
        Set<Integer> numbersOutOfRange = Set.of(1000, 1, 2, 3, 4, 5);
        RandomNumberGenerable generator = new WinningNumberGeneratorTestImpl(numbersOutOfRange);
        WinningNumbersGeneratorFacade numbersGenerator = new WinningNumbersGeneratorFacade(generator);
        //when
        Exception exception =
                assertThrows(IllegalStateException.class, numbersGenerator::generateWinningNumbers);
        //then
        assertThat(exception.getMessage()).isEqualTo("dupa");
        // assercja catch throwable
    }

    @Test
    public void it_should_return_collection_of_unique_values() {
        //given
        RandomNumberGenerable generator = new WinningNumberGeneratorTestImpl();
        WinningNumbersGeneratorFacade numbersGenerator = new WinningNumbersGeneratorFacade(generator);
        //when
        WinningNumbersDto generatedNumbers = numbersGenerator.generateWinningNumbers();
        //then
        int generatedNumbersSize = new HashSet<>(generatedNumbers.getWinningNumbers()).size();
        assertThat(generatedNumbersSize).isEqualTo(6);
    }
}


