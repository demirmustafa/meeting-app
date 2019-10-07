package io.github.demirmustafa.meetingapp.api.model.resource;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TrackPresentationResource {

    private LocalTime start;
    private LocalTime end;
    private PresentationResource presentationResource;
}
