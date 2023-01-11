package pl.lotto.infrastructure.controller;


import lombok.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import pl.lotto.numberreceiver.*;
import pl.lotto.numberreceiver.dto.*;

import java.util.*;


@RestController
@AllArgsConstructor
public class NumberReceiverRestController {

    private final NumberReceiverFacade numberReceiverFacade;

    @PostMapping("/inputNumbers")
    public ResponseEntity<NumberReceiverResponseDto> f() {
        NumberReceiverResponseDto numberReceiverResponseDto = numberReceiverFacade.inputNumbers(Set.of(1, 2, 3, 4, 5, 6));
        return ResponseEntity.ok(numberReceiverResponseDto);
    }
}
