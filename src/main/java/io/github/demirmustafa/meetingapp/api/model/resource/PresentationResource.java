package io.github.demirmustafa.meetingapp.api.model.resource;

import io.github.demirmustafa.meetingapp.domain.enums.PresentationTimeType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PresentationResource {

    private Long id;
    private String name;
    private Integer minutes;
    private PresentationTimeType type;
    private SpeakerResource speaker;
}
