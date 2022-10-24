package pl.lotto.resultchecker;

import pl.lotto.resultchecker.dto.ResultCheckerResponseDto;

import java.util.HashSet;
import java.util.Set;

public class ResultCheckerFacade {
    public ResultCheckerResponseDto generateResult(Set<Integer> inputNumbers, Set<Integer> winningNumbers) {
        Set<Integer> matchingNumbers = new HashSet<>();
        getMatchingNumbers(inputNumbers, winningNumbers, matchingNumbers);
        if (!inputNumbers.equals(winningNumbers)) return ResultCheckerResponseDto.TRY_AGAIN;
        return ResultCheckerResponseDto.WINNING_NUMBERS;
    }

    private static void getMatchingNumbers(Set<Integer> inputNumbers, Set<Integer> winningNumbers, Set<Integer> matchingNumbers) {
        for (Integer number : winningNumbers) {
            if(inputNumbers.contains(number)) {
                matchingNumbers.add(number);
            }
        }
    }
}
