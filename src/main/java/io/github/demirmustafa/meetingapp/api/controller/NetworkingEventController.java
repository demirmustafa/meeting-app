package io.github.demirmustafa.meetingapp.api.controller;

import io.github.demirmustafa.meetingapp.api.model.request.CreateNetworkingEventRequest;
import io.github.demirmustafa.meetingapp.api.model.response.CreateNetworkingEventResponse;
import io.github.demirmustafa.meetingapp.service.NetworkingEventService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class NetworkingEventController {

    private final NetworkingEventService networkingEventService;

    @PostMapping("/events/networking")
    @ResponseStatus(HttpStatus.CREATED)
    public CreateNetworkingEventResponse create(@RequestBody CreateNetworkingEventRequest request) {
        return networkingEventService.create(request);
    }
}
