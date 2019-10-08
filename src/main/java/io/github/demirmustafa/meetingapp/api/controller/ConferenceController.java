package io.github.demirmustafa.meetingapp.api.controller;

import io.github.demirmustafa.meetingapp.api.model.resource.ConferenceResource;
import io.github.demirmustafa.meetingapp.service.ConferenceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ConferenceController {

    private final ConferenceService conferenceService;

    @GetMapping("/conferences")
    public ConferenceResource getConference() {
        return conferenceService.get();
    }
}
