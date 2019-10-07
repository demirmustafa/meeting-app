package io.github.demirmustafa.meetingapp.api.model.resource;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TrackResource {

    private List<TrackPresentationResource> presentations;
    private NetworkingEventResource networkingEvent;
}
