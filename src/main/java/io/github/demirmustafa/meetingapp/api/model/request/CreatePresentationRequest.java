package io.github.demirmustafa.meetingapp.api.model.request;

import io.github.demirmustafa.meetingapp.domain.enums.PresentationTimeType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreatePresentationRequest {

    private String name;

    private Integer minutes;

    private PresentationTimeType timeType;

    private Long speakerId;
}
