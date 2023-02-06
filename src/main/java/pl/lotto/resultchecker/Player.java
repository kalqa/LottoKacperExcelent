package pl.lotto.resultchecker;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Set;

@Data
@Builder
@Document
class Player {


    @Id
    private String hash;
    private Set<Integer> numbers;
    private Set<Integer> hitNumbers;
    private LocalDateTime drawDate;
    private boolean isWinner;

}
