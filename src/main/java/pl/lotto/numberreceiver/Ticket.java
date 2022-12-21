package pl.lotto.numberreceiver;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Set;

@Data
@Builder
class Ticket {

    private String hash;
    private Set<Integer> numbers;
    private LocalDateTime drawDate;
}
