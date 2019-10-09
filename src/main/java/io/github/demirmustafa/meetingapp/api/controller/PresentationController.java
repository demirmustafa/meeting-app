package io.github.demirmustafa.meetingapp.api.controller;

import io.github.demirmustafa.meetingapp.api.MeetingApi;
import io.github.demirmustafa.meetingapp.api.model.request.CreatePresentationRequest;
import io.github.demirmustafa.meetingapp.api.model.resource.PresentationResource;
import io.github.demirmustafa.meetingapp.api.model.response.CreatePresentationResponse;
import io.github.demirmustafa.meetingapp.api.validator.CreatePresentationRequestValidator;
import io.github.demirmustafa.meetingapp.service.PresentationService;
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
public class PresentationController {

    private final PresentationService presentationService;

    @PostMapping("/presentations")
    @ResponseStatus(HttpStatus.CREATED)
    @Validator(validator = CreatePresentationRequestValidator.class)
    public CreatePresentationResponse create(@Valid @RequestBody CreatePresentationRequest request) {
        return presentationService.create(request);
    }

    @GetMapping("/presentations")
    public List<PresentationResource> getAll() {
        return presentationService.getAll();
    }
}
