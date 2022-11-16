package pl.lotto.numbergenerator;

import java.security.SecureRandom;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

class WinningNumberGenerator implements RandomNumberGenerable{

    private final int LOWER_BAND = 1;
    private final int UPPER_BAND = 99;

    private final int RANDOM_NUMBER_BOUND = (UPPER_BAND-LOWER_BAND) +1;


    @Override
    public Set<Integer> generateRandomWInningNumbers() {
        Set<Integer> winningNumbers = new HashSet<>();
        while(isAmountOfNumbersLowerThanSix(winningNumbers)) {
            Random random = new SecureRandom();
            int randomNumber = random.nextInt(RANDOM_NUMBER_BOUND);
            winningNumbers.add(randomNumber);
        }
        if(outOfRange(winningNumbers)) {
            throw new IllegalStateException("Number out of range!");
        }

        return winningNumbers;
    }

    private boolean isAmountOfNumbersLowerThanSix(Set<Integer> winningNumbers) {
        return winningNumbers.size() < 6;
    }
    private boolean outOfRange(Set<Integer> winningNumbers) {
        return winningNumbers.stream()
                .anyMatch(number -> number <= LOWER_BAND || number >= UPPER_BAND);
    }
}
