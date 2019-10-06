package io.github.demirmustafa.meetingapp;

import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
@RequiredArgsConstructor
public class Messages {

    private final MessageSource messageSource;

    public String getMessage(String messageCode) {
        return messageSource.getMessage(messageCode, null, Locale.getDefault());
    }
}
