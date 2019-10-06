package io.github.demirmustafa.meetingapp.mapper;

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
}
