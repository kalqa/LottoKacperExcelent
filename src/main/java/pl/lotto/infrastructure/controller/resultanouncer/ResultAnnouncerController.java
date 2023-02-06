package pl.lotto.infrastructure.controller.resultanouncer;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.lotto.resultannouncer.ResultAnnouncerFacade;
import pl.lotto.resultannouncer.dto.ResultAnnouncerResponseDto;

@RestController
@AllArgsConstructor
public class ResultAnnouncerController {

    ResultAnnouncerFacade resultAnnouncerFacade;


    @GetMapping("/results/id")
    public ResponseEntity<ResultAnnouncerResponseDto> f(@RequestParam String id) {
        ResultAnnouncerResponseDto resultAnnouncerResponseDto = resultAnnouncerFacade.checkResult(id);
        return ResponseEntity.ok(resultAnnouncerResponseDto);
    }
}
