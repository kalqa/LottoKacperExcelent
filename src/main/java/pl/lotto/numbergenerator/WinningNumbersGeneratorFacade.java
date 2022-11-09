package pl.lotto.numbergenerator;

import java.util.Set;
import pl.lotto.numbergenerator.dto.WinningNumbersDto;

public class WinningNumbersGeneratorFacade {
    RandomNumberGenerable winningNumberGenerator;

    WinningNumbersGeneratorFacade(RandomNumberGenerable winningNumberGenerator) {
        this.winningNumberGenerator = winningNumberGenerator;
    }

    public WinningNumbersDto generateWinningNumbers() {
        Set<Integer> winningNumbers = winningNumberGenerator.generateRandomWinningNumbers();
//        if(outOfRange){
//           throw new IllegalStateException()
//        }
        return WinningNumbersDto.builder()
                .winningNumbers(winningNumbers)
                .build();
    }
}
