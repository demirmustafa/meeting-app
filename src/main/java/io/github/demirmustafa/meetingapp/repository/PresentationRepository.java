package io.github.demirmustafa.meetingapp.repository;

import io.github.demirmustafa.meetingapp.domain.entity.Presentation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PresentationRepository extends JpaRepository<Presentation, Long> {
}
