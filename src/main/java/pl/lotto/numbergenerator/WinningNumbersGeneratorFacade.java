package pl.lotto.numbergenerator;

import pl.lotto.numbergenerator.dto.WinningNumbersDto;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class WinningNumbersGeneratorFacade {

    //TODO: Extract finals to config ??
    private final int LOWER_BAND = 1;
    private final int UPPER_BAND = 99;

    private final int RANDOM_NUMBER_BOUND = (UPPER_BAND-LOWER_BAND) +1;


    public WinningNumbersDto generateWinningNumbers() {
        Set<Integer> winningNumbers = new HashSet<>();
        while(isAmountOfNumbersLowerThanSix(winningNumbers)) {
            Random random = new Random();
            int randomNumber = random.nextInt(RANDOM_NUMBER_BOUND);
            winningNumbers.add(randomNumber);
        }
        return WinningNumbersDto.builder()
                .winningNumbers(winningNumbers)
                .drawDate(LocalDate.now())
                .build();
    }

    private static boolean isAmountOfNumbersLowerThanSix(Set<Integer> winningNumbers) {
        return winningNumbers.size() < 6;
    }
}
