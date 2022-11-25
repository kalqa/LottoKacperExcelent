package pl.lotto.numbergenerator;

import lombok.AllArgsConstructor;
import pl.lotto.numbergenerator.dto.WinningNumbersDto;

import java.util.Set;

@AllArgsConstructor
public class WinningNumbersGeneratorFacade {

    RandomNumberGenerable winningNumberGenerator;
    WinningNumberValidator winningNumberValidator;

    public WinningNumbersDto generateWinningNumbers() {
        Set<Integer> winningNumbers = winningNumberGenerator.generateSixRandomNumbers();
        winningNumberValidator.validate(winningNumbers);
        return WinningNumbersDto.builder()
                .winningNumbers(winningNumbers)
                .build();
    }
}
