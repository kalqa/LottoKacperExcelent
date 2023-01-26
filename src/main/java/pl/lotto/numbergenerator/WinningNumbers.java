package pl.lotto.numbergenerator;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Set;

@Builder
@Data
@Document
public class WinningNumbers {

    @Id
    private String id;
    private Set<Integer> winningNumbers;
    private LocalDateTime date;
}
