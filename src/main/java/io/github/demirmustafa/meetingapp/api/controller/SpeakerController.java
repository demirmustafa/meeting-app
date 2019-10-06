package io.github.demirmustafa.meetingapp.api.controller;

import io.github.demirmustafa.meetingapp.api.MeetingApi;
import io.github.demirmustafa.meetingapp.api.model.request.CreateSpeakerRequest;
import io.github.demirmustafa.meetingapp.api.model.resource.SpeakerResource;
import io.github.demirmustafa.meetingapp.api.model.response.CreateSpeakerResponse;
import io.github.demirmustafa.meetingapp.api.validator.CreateSpeakerRequestValidator;
import io.github.demirmustafa.meetingapp.service.SpeakerService;
import io.github.demirmustafa.meetingapp.validation.annotation.Valid;
import io.github.demirmustafa.meetingapp.validation.annotation.Validator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

@MeetingApi
@RequiredArgsConstructor
public class SpeakerController {

    private final SpeakerService speakerService;

    @PostMapping("/speakers")
    @Validator(validator = CreateSpeakerRequestValidator.class)
    @ResponseStatus(HttpStatus.CREATED)
    public CreateSpeakerResponse create(@Valid @RequestBody CreateSpeakerRequest request) {
        return speakerService.create(request);
    }

    @GetMapping("/speakers")
    public List<SpeakerResource> getAll() {
        return speakerService.getAll();
    }
}
