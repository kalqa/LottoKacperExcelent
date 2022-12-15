package pl.lotto.resultchecker;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Set;
@Builder
@Data
class Winner {

    private String hash;
    private Set<Integer> numbers;
    private LocalDateTime drawDate;
}
