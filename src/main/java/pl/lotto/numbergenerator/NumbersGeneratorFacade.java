package pl.lotto.numbergenerator;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class NumbersGeneratorFacade {
    public Set<Integer> generateWinningNumbers() {
        Set<Integer> winningNumbers = new HashSet<>();
        while(winningNumbers.size() < 6) {
            Random random = new Random();
            int randomNumber = random.nextInt(1, 100);
            winningNumbers.add(randomNumber);
        }
        return winningNumbers;
    }
}
