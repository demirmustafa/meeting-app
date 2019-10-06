package io.github.demirmustafa.meetingapp.mapper;

import io.github.demirmustafa.meetingapp.api.model.resource.SpeakerResource;
import io.github.demirmustafa.meetingapp.api.model.response.CreateSpeakerResponse;
import io.github.demirmustafa.meetingapp.domain.entity.Speaker;
import org.springframework.stereotype.Component;

@Component
public class SpeakerResponseMapper {

    public SpeakerResource entity2Resource(Speaker entity) {
        return SpeakerResource.builder()
                .id(entity.getId())
                .name(entity.getName())
                .surname(entity.getSurname())
                .fullName(entity.getFullName())
                .company(entity.getCompany())
                .position(entity.getPosition())
                .bio(entity.getBio())
                .build();
    }

    public CreateSpeakerResponse entity2CreateResponse(Speaker entity) {
        return CreateSpeakerResponse.builder()
                .id(entity.getId())
                .build();
    }
}
