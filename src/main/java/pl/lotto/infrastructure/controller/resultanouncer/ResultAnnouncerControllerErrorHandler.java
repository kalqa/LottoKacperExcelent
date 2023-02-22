package pl.lotto.infrastructure.controller.resultanouncer;

import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import pl.lotto.resultchecker.PlayerResultNotFoundException;

@ControllerAdvice
@Log4j2
class ResultAnnouncerControllerErrorHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(PlayerResultNotFoundException.class)
    @ResponseBody
    public ResultAnnouncerErrorResponse offerNotFound(PlayerResultNotFoundException exception) {
        String message = exception.getMessage();
        log.warn(message);
        return new ResultAnnouncerErrorResponse(message, HttpStatus.NOT_FOUND);
    }

}
