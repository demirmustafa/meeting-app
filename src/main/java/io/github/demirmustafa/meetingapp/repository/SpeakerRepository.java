package io.github.demirmustafa.meetingapp.repository;

import io.github.demirmustafa.meetingapp.domain.entity.Speaker;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpeakerRepository extends JpaRepository<Speaker, Long> {

    Optional<Speaker> findByFullName(String fullName);
}
