package pl.lotto.numbergenerator;

import java.util.Set;
import lombok.AllArgsConstructor;
import pl.lotto.numbergenerator.dto.WinningNumbersDto;

@AllArgsConstructor
public class WinningNumbersGeneratorFacade {

    RandomNumberGenerable generator;
    WinningNumberValidator validator;

    public WinningNumbersDto generateWinningNumbers() {
        Set<Integer> winningNumbers = generator.generateSixRandomNumbers();
        validator.validate(winningNumbers);
        return WinningNumbersDto.builder()
                .winningNumbers(winningNumbers)
                .build();
    }
}
