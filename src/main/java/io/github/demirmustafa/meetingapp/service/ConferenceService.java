package io.github.demirmustafa.meetingapp.service;

import io.github.demirmustafa.meetingapp.api.model.resource.*;
import io.github.demirmustafa.meetingapp.domain.entity.Presentation;
import io.github.demirmustafa.meetingapp.domain.enums.PresentationTimeType;
import io.github.demirmustafa.meetingapp.domain.model.Track;
import io.github.demirmustafa.meetingapp.mapper.PresentationResponseMapper;
import io.github.demirmustafa.meetingapp.mapper.SpeakerResponseMapper;
import io.github.demirmustafa.meetingapp.repository.PresentationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ConferenceService {

    private final PresentationRepository presentationRepository;
    private final SpeakerResponseMapper speakerResponseMapper;
    private final PresentationResponseMapper presentationResponseMapper;

    public ConferenceResource get() {
        List<Presentation> presentations = presentationRepository.findAllByPresentationTimeTypeIs(PresentationTimeType.MINUTE);
        if (presentations.isEmpty()) {
            return buildResource(new ArrayList<>());
        }
        List<Track> tracks = new ArrayList<>();

        Track track = new Track();
        tracks.add(track);
        for (Presentation presentation : presentations) {
            if (!track.isBookableAfterLaunch(presentation)) { //if presentation is not bookable, then create new track
                track = new Track();
                tracks.add(track);
            }
            addPresentationToTrack(track, presentation);
        }
        return buildResource(tracks);
    }

    void addPresentationToTrack(Track track, Presentation presentation) {
        if (track.isBookableBeforeLaunch(presentation)) {
            log.info("booked before launch: {}", presentation.getName());
            track.bookBeforeLaunch(presentation);
        } else {
            log.info("booked after launch: {}", presentation.getName());
            track.bookAfterLaunch(presentation);
        }
    }

    ConferenceResource buildResource(List<Track> tracks) {
        return ConferenceResource.builder()
                .tracks(
                        tracks.stream()
                                .map(this::buildTrackResource)
                                .collect(Collectors.toList())
                ).build();
    }

    TrackPresentationResource buildTrackPresentationResource(Map.Entry<Pair<LocalTime, LocalTime>, Presentation> entry) {
        final LocalTime start = entry.getKey().getKey();
        final LocalTime end = entry.getKey().getValue();
        final Presentation presentation = entry.getValue();

        final SpeakerResource speakerResource = speakerResponseMapper.entity2Resource(presentation.getSpeaker());
        final PresentationResource presentationResource = presentationResponseMapper.entity2Resource(presentation, speakerResource);
        return TrackPresentationResource.builder()
                .start(start)
                .end(end)
                .presentationResource(presentationResource)
                .build();
    }

    TrackResource buildTrackResource(Track track) {
        List<TrackPresentationResource> presentations = track.getPresentations().entrySet()
                .stream()
                .map(this::buildTrackPresentationResource)
                .sorted(Comparator.comparing(TrackPresentationResource::getStart))
                .collect(Collectors.toList());
        return TrackResource.builder()
                .presentations(presentations)
                .build();
    }
}
