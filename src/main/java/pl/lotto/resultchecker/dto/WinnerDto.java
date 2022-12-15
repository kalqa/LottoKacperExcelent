package pl.lotto.resultchecker.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Set;

@Builder
@Data
public class WinnerDto {

    private String hash;
    private Set<Integer> numbers;
    private LocalDateTime drawDate;
}
