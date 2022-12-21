package pl.lotto.resultchecker;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Set;

@Data
@Builder
class Player {

    private String hash;
    private Set<Integer> numbers;
    private Set<Integer> hitNumbers;
    private LocalDateTime drawDate;
    private boolean isWinner;

    public boolean isWinner(Set<Integer> hitNumbers) {
        if (hitNumbers.size() >= 3) {
            isWinner = true;
        }
        return false;
    }
}
