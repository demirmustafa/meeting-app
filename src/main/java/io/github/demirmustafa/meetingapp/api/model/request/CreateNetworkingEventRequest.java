package io.github.demirmustafa.meetingapp.api.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateNetworkingEventRequest {

    private String start;
    private String end;
}
