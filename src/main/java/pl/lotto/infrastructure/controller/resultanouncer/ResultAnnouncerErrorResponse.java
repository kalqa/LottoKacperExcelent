package pl.lotto.infrastructure.controller.resultanouncer;

import org.springframework.http.HttpStatus;

public record ResultAnnouncerErrorResponse(
        String message,
        HttpStatus status) {
}
