package pl.lotto.numbergenerator;

import org.junit.jupiter.api.Test;
import pl.lotto.numbergenerator.dto.WinningNumbersDto;

import java.util.Set;

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

    //TODO: Czy testuje się randomowe liczby jak tak to jak ?
}
