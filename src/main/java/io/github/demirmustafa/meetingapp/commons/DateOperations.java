package io.github.demirmustafa.meetingapp.commons;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class DateOperations {

    private static final String PATTERN = "yyyy-MM-ddTHH:mm:ss.Sss";
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(PATTERN, Locale.getDefault());

    public static LocalDateTime convertDateTime(String s) {
        return LocalDateTime.parse(s, formatter);
    }
}
