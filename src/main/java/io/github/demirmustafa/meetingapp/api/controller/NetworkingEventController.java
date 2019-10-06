package io.github.demirmustafa.meetingapp.api.controller;

import io.github.demirmustafa.meetingapp.api.MeetingApi;
import io.github.demirmustafa.meetingapp.api.model.request.CreateNetworkingEventRequest;
import io.github.demirmustafa.meetingapp.api.model.response.CreateNetworkingEventResponse;
import io.github.demirmustafa.meetingapp.api.validator.CreateNetworkingEventRequestValidator;
import io.github.demirmustafa.meetingapp.service.NetworkingEventService;
import io.github.demirmustafa.meetingapp.validation.annotation.Valid;
import io.github.demirmustafa.meetingapp.validation.annotation.Validator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@MeetingApi
@RequiredArgsConstructor
public class NetworkingEventController {

    private final NetworkingEventService networkingEventService;

    @PostMapping("/events/networking")
    @ResponseStatus(HttpStatus.CREATED)
    @Validator(validator = CreateNetworkingEventRequestValidator.class)
    public CreateNetworkingEventResponse create(@Valid @RequestBody CreateNetworkingEventRequest request) {
        return networkingEventService.create(request);
    }
}
