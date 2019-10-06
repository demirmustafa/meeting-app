package io.github.demirmustafa.meetingapp.service;

import io.github.demirmustafa.meetingapp.api.model.request.CreateSpeakerRequest;
import io.github.demirmustafa.meetingapp.api.model.resource.SpeakerResource;
import io.github.demirmustafa.meetingapp.api.model.response.CreateSpeakerResponse;
import io.github.demirmustafa.meetingapp.domain.entity.Speaker;
import io.github.demirmustafa.meetingapp.mapper.SpeakerResponseMapper;
import io.github.demirmustafa.meetingapp.repository.SpeakerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class SpeakerService {

    private final SpeakerResponseMapper speakerResponseMapper;
    private final SpeakerRepository speakerRepository;

    public CreateSpeakerResponse create(CreateSpeakerRequest request) {
        Speaker speaker = new Speaker(request);
        Optional<Speaker> speakerOptional = speakerRepository.findByFullName(speaker.getFullName());
        if (speakerOptional.isPresent()) {
            log.error("Speaker already exist with fullName: {}", speaker.getFullName());
            //TODO throw new Ex
        }
        Speaker saved = speakerRepository.save(speaker);
        return speakerResponseMapper.entity2CreateResponse(saved);
    }

    public List<SpeakerResource> getAll() {
        return speakerRepository.findAll()
                .stream()
                .map(speakerResponseMapper::entity2Resource)
                .collect(Collectors.toList());
    }
}
