package pl.lotto.numbergenerator;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class NumbersGeneratorFacade {

    public static final int LOWER_BAND_OF_RANGE = 1;
    public static final int UPPER_BAND_OF_RANGE = 100;

    public Set<Integer> generateWinningNumbers() {
        Set<Integer> winningNumbers = new HashSet<>();
        while(winningNumbers.size() < 6) {
            Random random = new Random();
            int randomNumber = random.nextInt(LOWER_BAND_OF_RANGE, UPPER_BAND_OF_RANGE);
            winningNumbers.add(randomNumber);
        }
        return winningNumbers;
    }
}
