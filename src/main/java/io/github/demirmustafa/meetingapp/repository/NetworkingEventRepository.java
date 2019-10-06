package io.github.demirmustafa.meetingapp.repository;

import io.github.demirmustafa.meetingapp.domain.entity.NetworkingEvent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NetworkingEventRepository extends JpaRepository<NetworkingEvent, Long> {
}
