package io.github.demirmustafa.meetingapp.api.advice;

import io.github.demirmustafa.meetingapp.Messages;
import io.github.demirmustafa.meetingapp.api.MeetingApi;
import io.github.demirmustafa.meetingapp.exception.BusinessException;
import io.github.demirmustafa.meetingapp.validation.exception.ModelNotValidException;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice(annotations = {
        MeetingApi.class
})
@RequiredArgsConstructor
public class ControllerAdvice {

    private final Messages messages;

    @ExceptionHandler(ModelNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handle(ModelNotValidException e) {
        log.error("[ControllerAdvice] handles ModelNotValidException", e);
        return anErrorResponse(HttpStatus.BAD_REQUEST, e.getMessage());
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity handle(BusinessException e) {
        log.error("[ControllerAdvice] handles BusinessException", e);
        ErrorResponse response = anErrorResponse(e.getFaultCode().getStatus(), messages.getMessage(e.getFaultCode().getCode()));
        return new ResponseEntity(response, e.getFaultCode().getStatus());
    }

    private ErrorResponse anErrorResponse(HttpStatus status, String message) {
        return ErrorResponse.builder()
                .status(status.value())
                .message(message)
                .build();
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    private static class ErrorResponse {

        private Integer status;
        private String message;
    }
}
