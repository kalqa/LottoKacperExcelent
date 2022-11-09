package pl.lotto.numbergenerator;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

class WinningNumberGenerator implements RandomNumberGenerable {

    //TODO:  finals to config ??
    private final int LOWER_BAND = 1;
    private final int UPPER_BAND = 99;
    private final int RANDOM_NUMBER_BOUND = (UPPER_BAND - LOWER_BAND) + 1;

    @Override
    public Set<Integer> generateRandomWinningNumbers() {
        Set<Integer> winningNumbers = new HashSet<>();
        while (isAmountOfNumbersLowerThanSix(winningNumbers)) {
            Random random = new Random();
            int randomNumber = random.nextInt(RANDOM_NUMBER_BOUND);
            winningNumbers.add(randomNumber);
        }

        return winningNumbers;
    }

    private boolean isAmountOfNumbersLowerThanSix(Set<Integer> winningNumbers) {
        return winningNumbers.size() < 6;
    }
}
