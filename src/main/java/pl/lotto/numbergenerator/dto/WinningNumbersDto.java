package pl.lotto.numbergenerator.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@Builder
public class WinningNumbersDto {

    private Set<Integer> winningNumbers;
}
