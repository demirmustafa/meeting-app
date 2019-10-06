package io.github.demirmustafa.meetingapp.domain.entity;

import io.github.demirmustafa.meetingapp.api.model.request.CreatePresentationRequest;
import io.github.demirmustafa.meetingapp.domain.enums.PresentationTimeType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "presentations", schema = "meeting_app")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Presentation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Long minutes;

    @Column(nullable = false)
    private PresentationTimeType presentationTimeType;

    @JoinColumn(name = "speaker_id", nullable = false)
    @ManyToOne
    private Speaker speaker;

    public Presentation(CreatePresentationRequest request, Speaker speaker) {
        this.name = request.getName();
        this.minutes = request.getTime();
        this.presentationTimeType = request.getTimeType();
        this.speaker = speaker;
    }
}
