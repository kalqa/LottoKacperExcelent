package pl.lotto.numbergenerator;

import lombok.AllArgsConstructor;
import pl.lotto.numbergenerator.dto.WinningNumbersDto;

import java.util.Set;

@AllArgsConstructor
public class WinningNumbersGeneratorFacade {

    RandomNumberGenerable winningNumberGenerator;

    public WinningNumbersDto generateWinningNumbers() {
        Set<Integer> winningNumbers = winningNumberGenerator.generateRandomWInningNumbers();
        return WinningNumbersDto.builder()
                .winningNumbers(winningNumbers)
                .build();
    }
}
