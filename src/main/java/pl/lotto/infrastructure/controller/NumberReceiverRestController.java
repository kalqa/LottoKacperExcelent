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
    public ResponseEntity<NumberReceiverResponseDto> f(@RequestBody NumberReceiverRequestDto requestDto) {
        NumberReceiverResponseDto numberReceiverResponseDto = numberReceiverFacade.inputNumbers(requestDto.inputNumbers());
        return ResponseEntity.ok(numberReceiverResponseDto);
    }
}
