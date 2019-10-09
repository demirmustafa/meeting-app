package io.github.demirmustafa.meetingapp.service;

import io.github.demirmustafa.meetingapp.api.model.request.CreatePresentationRequest;
import io.github.demirmustafa.meetingapp.api.model.resource.PresentationResource;
import io.github.demirmustafa.meetingapp.api.model.resource.SpeakerResource;
import io.github.demirmustafa.meetingapp.api.model.response.CreatePresentationResponse;
import io.github.demirmustafa.meetingapp.domain.entity.Presentation;
import io.github.demirmustafa.meetingapp.domain.entity.Speaker;
import io.github.demirmustafa.meetingapp.exception.BusinessException;
import io.github.demirmustafa.meetingapp.exception.FaultCode;
import io.github.demirmustafa.meetingapp.mapper.PresentationResponseMapper;
import io.github.demirmustafa.meetingapp.mapper.SpeakerResponseMapper;
import io.github.demirmustafa.meetingapp.repository.PresentationRepository;
import io.github.demirmustafa.meetingapp.repository.SpeakerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class PresentationService {

    private final PresentationRepository presentationRepository;
    private final SpeakerRepository speakerRepository;
    private final PresentationResponseMapper presentationResponseMapper;
    private final SpeakerResponseMapper speakerResponseMapper;

    public CreatePresentationResponse create(CreatePresentationRequest request) {
        Speaker speaker = speakerRepository.findById(request.getSpeakerId()).orElseThrow(() -> new BusinessException(FaultCode.PRESENTATION_SPEAKER_NOT_FOUND));
        Presentation presentation = new Presentation(request, speaker);
        Presentation saved = presentationRepository.save(presentation);
        return presentationResponseMapper.entity2CreateResponse(saved);
    }

    public List<PresentationResource> getAll() {
        Function<Speaker, SpeakerResource> mapToResource = speakerResponseMapper::entity2Resource;

        return presentationRepository.findAll()
                .stream()
                .map(presentation -> presentationResponseMapper.entity2Resource(presentation, mapToResource.apply(presentation.getSpeaker())))
                .collect(Collectors.toList());
    }
}
