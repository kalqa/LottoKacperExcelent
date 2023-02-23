package pl.lotto.infrastructure.controller.numberreceiver;


import javax.validation.Valid;
import lombok.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import pl.lotto.numberreceiver.*;
import pl.lotto.numberreceiver.dto.*;


@RestController
@AllArgsConstructor
public class NumberReceiverRestController {

    private final NumberReceiverFacade numberReceiverFacade;

    @PostMapping("/inputNumbers")
    public ResponseEntity<NumberReceiverResponseDto> inputNumbers(@RequestBody @Valid NumberReceiverRequestDto requestDto) {
        NumberReceiverResponseDto numberReceiverResponseDto = numberReceiverFacade.inputNumbers(requestDto.inputNumbers());
        return ResponseEntity.ok(numberReceiverResponseDto);
    }
}
