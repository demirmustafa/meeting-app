package io.github.demirmustafa.meetingapp.api.controller;

import io.github.demirmustafa.meetingapp.api.MeetingApi;
import io.github.demirmustafa.meetingapp.api.model.request.CreatePresentationRequest;
import io.github.demirmustafa.meetingapp.api.model.response.CreatePresentationResponse;
import io.github.demirmustafa.meetingapp.service.PresentationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@MeetingApi
@RequiredArgsConstructor
public class PresentationController {

    private final PresentationService presentationService;

    @PostMapping("/presentations")
    public CreatePresentationResponse create(@RequestBody CreatePresentationRequest request) {
        return presentationService.create(request);
    }
}
