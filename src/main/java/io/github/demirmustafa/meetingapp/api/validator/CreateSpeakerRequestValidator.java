package io.github.demirmustafa.meetingapp.api.validator;

import io.github.demirmustafa.meetingapp.Messages;
import io.github.demirmustafa.meetingapp.api.model.request.CreateSpeakerRequest;
import io.github.demirmustafa.meetingapp.validation.BaseValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import java.util.Objects;

@Component
@Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
@RequiredArgsConstructor
public class CreateSpeakerRequestValidator extends BaseValidator<CreateSpeakerRequest> {

    private final Messages messages;

    @Override
    public void validate(CreateSpeakerRequest model) {
        if (Objects.isNull(model)) {
            fieldNotValid("request body", "err.request.invalid", messages.getMessage("err.request.invalid"));
        }

        if (isNullOrEmpty(model.getName())) {
            fieldNotValid("name", "err.speaker.name.empty", messages.getMessage("err.speaker.name.empty"));
        }

        if (isNullOrEmpty(model.getSurname())) {
            fieldNotValid("surname", "err.speaker.surname.empty", messages.getMessage("err.speaker.surname.empty"));
        }
    }
}
