package io.github.demirmustafa.meetingapp.api.advice;

import io.github.demirmustafa.meetingapp.api.MeetingApi;
import io.github.demirmustafa.meetingapp.validation.exception.ModelNotValidException;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice(annotations = {
        MeetingApi.class
})
public class ControllerAdvice {

    @ExceptionHandler(ModelNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handle(ModelNotValidException e) {
        log.error("[ControllerAdvice] handles ModelNotValidException", e);
        return anErrorResponse(HttpStatus.BAD_REQUEST, e);
    }

    private ErrorResponse anErrorResponse(HttpStatus status, Throwable e) {
        return ErrorResponse.builder()
                .status(status.value())
                .message(e.getMessage())
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
