package io.github.demirmustafa.meetingapp.api.model.resource;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NetworkingEventResource {

    private LocalDateTime start;
    private LocalDateTime end;
}
