package io.github.demirmustafa.meetingapp.service;

import io.github.demirmustafa.meetingapp.api.model.request.CreateNetworkingEventRequest;
import io.github.demirmustafa.meetingapp.api.model.response.CreateNetworkingEventResponse;
import io.github.demirmustafa.meetingapp.domain.entity.NetworkingEvent;
import io.github.demirmustafa.meetingapp.mapper.NetworkingEventResponseMapper;
import io.github.demirmustafa.meetingapp.repository.NetworkingEventRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class NetworkingEventService {

    private final NetworkingEventRepository networkingEventRepository;
    private final NetworkingEventResponseMapper networkingEventResponseMapper;

    public CreateNetworkingEventResponse create(CreateNetworkingEventRequest request) {
        NetworkingEvent event = new NetworkingEvent(request);
        NetworkingEvent saved = networkingEventRepository.save(event);
        return networkingEventResponseMapper.entity2CreateResponse(saved);
    }
}
