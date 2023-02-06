package pl.lotto.infrastructure.controller.numberreceiver;

import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Data;


public record NumberReceiverRequestDto(
        Set<Integer> inputNumbers) {

}
