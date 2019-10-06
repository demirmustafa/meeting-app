package io.github.demirmustafa.meetingapp.api.model.resource;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SpeakerResource {

    private Long id;
    private String name;
    private String surname;
    private String fullName;
    private String company;
    private String position;
    private String bio;
}
