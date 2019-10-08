package io.github.demirmustafa.meetingapp.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum FaultCode {

    PRESENTATION_SPEAKER_NOT_FOUND("err.presentation.speaker.notFound", HttpStatus.UNPROCESSABLE_ENTITY);

    private final String code;
    private final HttpStatus status;
}
