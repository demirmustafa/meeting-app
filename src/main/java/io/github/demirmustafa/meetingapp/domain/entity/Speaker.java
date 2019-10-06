package io.github.demirmustafa.meetingapp.domain.entity;

import io.github.demirmustafa.meetingapp.api.model.request.CreateSpeakerRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "speakers", indexes = {
        @Index(name = "speaker_full_name_index", columnList = "full_name")
})
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Speaker {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String surname;

    @Column(nullable = false, name = "full_name")
    private String fullName;

    @Column
    private String company;

    @Column
    private String position;

    @Column
    private String bio;

    public Speaker(CreateSpeakerRequest request) {
        this.name = request.getName();
        this.surname = request.getSurname();
        this.fullName = name + " " + surname;
        this.company = request.getCompany();
        this.position = request.getPosition();
        this.bio = request.getBio();
    }
}
