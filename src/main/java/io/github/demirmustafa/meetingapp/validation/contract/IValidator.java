package io.github.demirmustafa.meetingapp.validation.contract;

public interface IValidator<T> {

    void validate(T model);

    boolean isValid();
}
