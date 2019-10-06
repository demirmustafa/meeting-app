package io.github.demirmustafa.meetingapp.mapper;

import io.github.demirmustafa.meetingapp.api.model.response.CreateNetworkingEventResponse;
import io.github.demirmustafa.meetingapp.domain.entity.NetworkingEvent;
import org.springframework.stereotype.Component;

@Component
public class NetworkingEventResponseMapper {

    public CreateNetworkingEventResponse entity2CreateResponse(NetworkingEvent entity) {
        return CreateNetworkingEventResponse.builder()
                .id(entity.getId())
                .build();
    }
}
