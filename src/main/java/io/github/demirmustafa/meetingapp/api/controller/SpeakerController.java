package io.github.demirmustafa.meetingapp.api.controller;

import io.github.demirmustafa.meetingapp.api.model.request.CreateSpeakerRequest;
import io.github.demirmustafa.meetingapp.api.model.resource.SpeakerResource;
import io.github.demirmustafa.meetingapp.api.model.response.CreateSpeakerResponse;
import io.github.demirmustafa.meetingapp.service.SpeakerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class SpeakerController {

    private final SpeakerService speakerService;

    @PostMapping("/speakers")
    public CreateSpeakerResponse create(@RequestBody CreateSpeakerRequest request) {
        return speakerService.create(request);
    }

    @GetMapping("/speakers")
    public List<SpeakerResource> getAll() {
        return speakerService.getAll();
    }
}
