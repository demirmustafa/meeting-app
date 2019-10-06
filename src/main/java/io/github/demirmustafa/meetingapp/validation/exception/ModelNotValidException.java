package io.github.demirmustafa.meetingapp.validation.exception;

import lombok.Getter;

import java.util.List;

@Getter
public class ModelNotValidException extends RuntimeException {

    private List<FieldNotValidException> fieldNotValidExceptions;

    public ModelNotValidException(String message) {
        super(message);
    }

    public ModelNotValidException(List<FieldNotValidException> fieldNotValidExceptions) {
        super(fieldNotValidExceptions.toString());
        this.fieldNotValidExceptions = fieldNotValidExceptions;
    }
}
