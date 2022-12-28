package pl.lotto.resultannouncer.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Set;

@Builder
@Data
public class ResponseDto {

    private String hash;
    private Set<Integer> numbers;
    private Set<Integer> hitNumbers;
    private LocalDateTime drawDate;
    private boolean isWinner;
}
