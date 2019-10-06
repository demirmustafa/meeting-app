package io.github.demirmustafa.meetingapp.domain.entity;

import io.github.demirmustafa.meetingapp.api.model.request.CreateNetworkingEventRequest;
import io.github.demirmustafa.meetingapp.commons.DateOperations;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "networking_events")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class NetworkingEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime start;

    @Column(nullable = false)
    private LocalDateTime end;

    public NetworkingEvent(CreateNetworkingEventRequest request) {
        this.start = DateOperations.convertDateTime(request.getStart());
        this.end = DateOperations.convertDateTime(request.getEnd());
    }
}
