package io.github.demirmustafa.meetingapp.validation;

import io.github.demirmustafa.meetingapp.validation.contract.IValidator;
import io.github.demirmustafa.meetingapp.validation.exception.FieldNotValidException;
import io.github.demirmustafa.meetingapp.validation.exception.ModelNotValidException;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseValidator<T> implements IValidator<T> {

    @Getter
    private List<FieldNotValidException> exceptions = new ArrayList<>();

    protected void fieldNotValid(String fieldName, String code, String message) {
        exceptions.add(new FieldNotValidException(fieldName, code, message));
    }

    protected void fieldNotValid(String code, String message) {
        exceptions.add(new FieldNotValidException(code, message));
    }

    @Override
    public boolean isValid() {
        if (!exceptions.isEmpty())
            throw new ModelNotValidException(exceptions);

        return true;
    }

    protected boolean isNullOrEmpty(String str) {
        return str == null || str.isEmpty();
    }
}
