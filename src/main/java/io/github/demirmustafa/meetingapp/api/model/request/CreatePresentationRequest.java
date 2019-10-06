package io.github.demirmustafa.meetingapp.api.model.request;

import io.github.demirmustafa.meetingapp.domain.enums.PresentationTimeType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreatePresentationRequest {

    @NotNull
    private String name;

    @NotNull
    private Long time;

    @NotNull
    private PresentationTimeType timeType;

    @NotNull
    private Long speakerId;
}
