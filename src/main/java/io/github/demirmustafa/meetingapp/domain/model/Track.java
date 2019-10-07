package io.github.demirmustafa.meetingapp.domain.model;

import io.github.demirmustafa.meetingapp.utils.DateOperations;
import io.github.demirmustafa.meetingapp.domain.entity.Presentation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

import java.time.LocalTime;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Track {

    private LinkedList<Pair<LocalTime, LocalTime>> bookingBeforeLaunch = new LinkedList<>();
    private LinkedList<Pair<LocalTime, LocalTime>> bookingAfterLaunch = new LinkedList<>();
    private LinkedList<Pair<LocalTime, LocalTime>> bookable;

    private final LocalTime start = LocalTime.of(9, 0);
    private final LocalTime end = LocalTime.of(17, 0);
    private final LocalTime launchStart = LocalTime.of(12, 0);
    private final LocalTime launchEnd = LocalTime.of(13, 0);

    private Map<Pair<LocalTime, LocalTime>, Presentation> presentations = new HashMap<>();


    public void bookBeforeLaunch(Presentation presentation) {
        Pair<Integer, Integer> presentationTime = DateOperations.getMinutesAsTime(presentation.getMinutes());

        if (bookingBeforeLaunch.isEmpty()) {
            LocalTime presentationStart = LocalTime.of(start.getHour(), start.getMinute());
            LocalTime presentationEnd = LocalTime.of(start.getHour() + presentationTime.getLeft(), presentationTime.getRight());
            bookingBeforeLaunch.add(new ImmutablePair<>(presentationStart, presentationEnd));
            presentations.put(new ImmutablePair<>(presentationStart, presentationEnd), presentation);
        } else {
            LocalTime lastPresentationBeforeLaunch = bookingBeforeLaunch.getLast().getRight();
            LocalTime presentationStart = LocalTime.of(lastPresentationBeforeLaunch.getHour(), lastPresentationBeforeLaunch.getMinute());
            LocalTime presentationEnd = LocalTime.of(presentationStart.getHour(), presentationStart.getMinute()).plusHours(presentationTime.getLeft()).plusMinutes(presentationTime.getRight());
            bookingBeforeLaunch.add(new ImmutablePair<>(presentationStart, presentationEnd));
            presentations.put(new ImmutablePair<>(presentationStart, presentationEnd), presentation);
        }
    }

    public void bookAfterLaunch(Presentation presentation) {
        Pair<Integer, Integer> presentationTime = DateOperations.getMinutesAsTime(presentation.getMinutes());
        if (bookingAfterLaunch.isEmpty()) {
            LocalTime presentationStart = LocalTime.of(launchEnd.getHour(), launchEnd.getMinute());
            LocalTime presentationEnd = LocalTime.of(launchEnd.getHour() + presentationTime.getLeft(), launchEnd.getMinute() + presentationTime.getRight());
            bookingAfterLaunch.add(new ImmutablePair<>(presentationStart, presentationEnd));
            presentations.put(new ImmutablePair<>(presentationStart, presentationEnd), presentation);
        } else {
            LocalTime lastPresentationAfterLaunch = bookingAfterLaunch.getLast().getRight();
            LocalTime presentationStart = LocalTime.of(lastPresentationAfterLaunch.getHour(), lastPresentationAfterLaunch.getMinute());
            LocalTime presentationEnd = LocalTime.of(presentationStart.getHour(), presentationStart.getMinute()).plusHours(presentationTime.getLeft()).plusMinutes(presentationTime.getRight());
            bookingAfterLaunch.add(new ImmutablePair<>(presentationStart, presentationEnd));
            presentations.put(new ImmutablePair<>(presentationStart, presentationEnd), presentation);
        }
    }

    public boolean isBookableBeforeLaunch(Presentation presentation) {
        if (bookingBeforeLaunch.isEmpty()) {
            return true;
        }
        Pair<Integer, Integer> presentationTime = DateOperations.getMinutesAsTime(presentation.getMinutes());
        LocalTime lastEnd = bookingBeforeLaunch.getLast().getRight();
        if (lastEnd.getHour() < launchStart.getHour()) {
            LocalTime presentationEnd = LocalTime.of(lastEnd.getHour(), lastEnd.getMinute()).plusHours(presentationTime.getLeft()).plusMinutes(presentationTime.getRight());
            return launchStart.compareTo(presentationEnd) >= 0;
        }
        return false;
    }

    public boolean isBookableAfterLaunch(Presentation presentation) {
        if (bookingAfterLaunch.isEmpty()) {
            return true;
        }

        Pair<Integer, Integer> presentationTime = DateOperations.getMinutesAsTime(presentation.getMinutes());
        LocalTime lastEnd = bookingAfterLaunch.getLast().getRight();
        if (lastEnd.getHour() < end.getHour()) {
            LocalTime presentationEnd = LocalTime.of(lastEnd.getHour(), lastEnd.getMinute()).plusHours(presentationTime.getLeft()).plusMinutes(presentationTime.getRight());
            return end.compareTo(presentationEnd) >= 0;
        }
        return false;
    }
}
