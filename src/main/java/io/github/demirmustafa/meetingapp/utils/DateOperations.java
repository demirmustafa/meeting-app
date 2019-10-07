package io.github.demirmustafa.meetingapp.utils;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

public class DateOperations {

    public static Pair<Integer, Integer> getMinutesAsTime(Integer minutes) {
        if (minutes >= 60) {
            int hour = minutes / 60;
            int mins = minutes % 60;
            return new ImmutablePair<>(hour, mins);
        }
        return new ImmutablePair<>(0, minutes);
    }
}
