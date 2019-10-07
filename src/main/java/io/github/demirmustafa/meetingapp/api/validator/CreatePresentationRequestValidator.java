package io.github.demirmustafa.meetingapp.api.validator;

import io.github.demirmustafa.meetingapp.Messages;
import io.github.demirmustafa.meetingapp.api.model.request.CreatePresentationRequest;
import io.github.demirmustafa.meetingapp.domain.enums.PresentationTimeType;
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
public class CreatePresentationRequestValidator extends BaseValidator<CreatePresentationRequest> {

    private final Messages messages;

    @Override
    public void validate(CreatePresentationRequest model) {
        if (Objects.isNull(model)) {
            fieldNotValid("err.request.invalid", messages.getMessage("err.request.invalid"));
            return;
        }

        if (isNullOrEmpty(model.getName())) {
            fieldNotValid("name", "err.presentation.name.empty", messages.getMessage("err.presentation.name.empty"));
        }

        if (model.getTimeType() == null) {
            fieldNotValid("timeType", "err.presentation.time.type.empty", messages.getMessage("err.presentation.time.type.empty"));
        }

        if (PresentationTimeType.MINUTE.equals(model.getTimeType()) && (model.getMinutes() == null || 0L == model.getMinutes())) {
            fieldNotValid("minutes", "err.presentation.time.empty", messages.getMessage("err.presentation.time.empty"));
        }

        if (model.getSpeakerId() == null || 0L == model.getSpeakerId()) {
            fieldNotValid("speakerId", "err.presentation.speaker.empty", messages.getMessage("err.presentation.speaker.empty"));
        }
    }
}
