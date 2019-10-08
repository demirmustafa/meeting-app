package io.github.demirmustafa.meetingapp.exception;

import lombok.Getter;

@Getter
public class BusinessException extends RuntimeException {

    private final FaultCode faultCode;

    public BusinessException(FaultCode faultCode) {
        super();
        this.faultCode = faultCode;
    }
}
