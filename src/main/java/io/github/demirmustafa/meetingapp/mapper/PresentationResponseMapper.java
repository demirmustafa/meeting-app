package io.github.demirmustafa.meetingapp.mapper;

import io.github.demirmustafa.meetingapp.api.model.resource.PresentationResource;
import io.github.demirmustafa.meetingapp.api.model.resource.SpeakerResource;
import io.github.demirmustafa.meetingapp.api.model.response.CreatePresentationResponse;
import io.github.demirmustafa.meetingapp.domain.entity.Presentation;
import org.springframework.stereotype.Component;

@Component
public class PresentationResponseMapper {

    public CreatePresentationResponse entity2CreateResponse(Presentation entity) {
        return CreatePresentationResponse.builder()
                .id(entity.getId())
                .build();
    }

    public PresentationResource entity2Resource(Presentation entity, SpeakerResource speaker) {
        return PresentationResource.builder()
                .name(entity.getName())
                .minutes(entity.getMinutes())
                .type(entity.getPresentationTimeType())
                .speaker(speaker)
                .build();
    }
}
