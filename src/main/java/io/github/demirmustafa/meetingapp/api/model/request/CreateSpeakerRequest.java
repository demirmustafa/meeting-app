package io.github.demirmustafa.meetingapp.api.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateSpeakerRequest {

    private String name;
    private String surname;
    private String company;
    private String position;
    private String bio;
}
