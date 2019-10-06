package io.github.demirmustafa.meetingapp.validation.exception;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class FieldNotValidException extends RuntimeException {

    private String fieldName;
    private String code;
    private String message;

    public FieldNotValidException(String code, String message) {
        super(code + '.' + message);
        this.message = message;
        this.code = code;
    }

    public FieldNotValidException(String fieldName, String code, String message) {
        super(fieldName + " : " + code + '.' + message);
        this.fieldName = fieldName;
        this.message = message;
        this.code = code;
    }
}
