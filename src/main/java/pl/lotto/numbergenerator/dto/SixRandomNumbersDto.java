package pl.lotto.numbergenerator.dto;

import java.util.Set;
import lombok.Builder;

@Builder
public record SixRandomNumbersDto(Set<Integer> numbers) {
}
