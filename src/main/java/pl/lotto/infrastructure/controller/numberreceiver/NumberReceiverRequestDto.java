package pl.lotto.infrastructure.controller.numberreceiver;

import java.util.Set;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


public record NumberReceiverRequestDto(
        @NotNull(message = "{inputNumbers.not.null}")
        @NotEmpty(message = "{inputNumbers.not.empty}")
        Set<Integer> inputNumbers
) {

}
