package io.github.demirmustafa.meetingapp.repository;

import io.github.demirmustafa.meetingapp.domain.entity.Presentation;
import io.github.demirmustafa.meetingapp.domain.enums.PresentationTimeType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PresentationRepository extends JpaRepository<Presentation, Long> {

    List<Presentation> findAllByPresentationTimeTypeIs(PresentationTimeType type);
}
