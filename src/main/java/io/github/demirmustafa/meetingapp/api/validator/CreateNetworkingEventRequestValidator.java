package io.github.demirmustafa.meetingapp.api.validator;

import io.github.demirmustafa.meetingapp.Messages;
import io.github.demirmustafa.meetingapp.api.model.request.CreateNetworkingEventRequest;
import io.github.demirmustafa.meetingapp.commons.DateOperations;
import io.github.demirmustafa.meetingapp.validation.BaseValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDateTime;
import java.util.Objects;

@Component
@Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
@RequiredArgsConstructor
public class CreateNetworkingEventRequestValidator extends BaseValidator<CreateNetworkingEventRequest> {

    private final Messages messages;

    @Override
    public void validate(CreateNetworkingEventRequest model) {
        validateStartTime(model.getStart());
        validateEndTime(model.getEnd());
    }

    private void validateStartTime(String start) {
        LocalDateTime startTime = null;
        try {
            startTime = DateOperations.convertDateTime(start);
        } catch (Exception e) {
            fieldNotValid("start", "err.event.networking.time.parse", messages.getMessage("err.event.networking.time.parse"));
        }

        if (Objects.nonNull(startTime)) {
            int hour = startTime.getHour();

            if (hour >= 17) {
                fieldNotValid("start", "err.event.networking.start.late", messages.getMessage("err.event.networking.start.late"));
                return;
            }

            if (hour < 16) {
                fieldNotValid("start", "err.event.networking.start.early", messages.getMessage("err.event.networking.start.early"));
            }
        }
    }

    private void validateEndTime(String end) {
        LocalDateTime endTime = null;
        try {
            endTime = DateOperations.convertDateTime(end);
        } catch (Exception e) {
            fieldNotValid("end", "err.event.networking.time.parse", messages.getMessage("err.event.networking.time.parse"));
        }

        if (Objects.nonNull(endTime)) {
            int hour = endTime.getHour();
            int min = endTime.getMinute();

            if (hour > 17 || (hour == 17 && min > 0)) {
                fieldNotValid("end", "err.event.networking.end.late", messages.getMessage("err.event.networking.start.late"));
                return;
            }

            if (hour < 16 || (hour == 16 && min <= 0)) {
                fieldNotValid("end", "err.event.networking.end.early", messages.getMessage("err.event.networking.start.early"));
            }
        }
    }
}
