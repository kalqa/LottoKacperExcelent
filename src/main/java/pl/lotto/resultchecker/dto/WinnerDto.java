package pl.lotto.resultchecker.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.Set;

@Builder
@Data
public class WinnerDto {

    private String hash;
    private Set<Integer> numbers;
    private LocalDate drawDate;
}
