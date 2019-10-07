package io.github.demirmustafa.meetingapp.api.validator;

import io.github.demirmustafa.meetingapp.Messages;
import io.github.demirmustafa.meetingapp.api.model.request.CreatePresentationRequest;
import io.github.demirmustafa.meetingapp.domain.enums.PresentationTimeType;
import io.github.demirmustafa.meetingapp.validation.exception.FieldNotValidException;
import io.github.demirmustafa.meetingapp.validation.exception.ModelNotValidException;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class CreatePresentationRequestValidatorTest {

    @Mock
    private Messages messages;

    @InjectMocks
    private CreatePresentationRequestValidator createPresentationRequestValidator;

    @Captor
    private ArgumentCaptor<String> errorMessageCodeArgumentCaptor;

    @After
    public void tearDown() {
        verifyNoMoreInteractions(messages);
    }

    @Test(expected = ModelNotValidException.class)
    public void Should_add_invalid_request_exception_when_request_is_empty() {
        //given
        CreatePresentationRequest request = null;
        doReturn("test_error_message").when(messages).getMessage("err.request.invalid");

        //when
        createPresentationRequestValidator.validate(request);

        //then
        try {
            createPresentationRequestValidator.isValid();
        } finally {
            verify(messages).getMessage(errorMessageCodeArgumentCaptor.capture());
            assertEquals("err.request.invalid", errorMessageCodeArgumentCaptor.getValue());
            assertEquals(1, createPresentationRequestValidator.getExceptions().size());
            FieldNotValidException occurred = createPresentationRequestValidator.getExceptions().get(0);
            assertEquals("err.request.invalid", occurred.getCode());
            assertEquals("test_error_message", occurred.getMessage());
        }
    }

    @Test(expected = ModelNotValidException.class)
    public void Should_add_FieldNotValidException_and_throw_ModelNotValidException_when_timeType_property_is_empty() {
        //given
        CreatePresentationRequest request = CreatePresentationRequest.builder()
                .name("test_name")
                .minutes(30)
                .speakerId(1L)
                .build();

        doReturn("test_error_message").when(messages).getMessage("err.presentation.time.type.empty");

        //when
        createPresentationRequestValidator.validate(request);

        //then
        try {
            createPresentationRequestValidator.isValid();
        } finally {
            verify(messages).getMessage(errorMessageCodeArgumentCaptor.capture());
            assertEquals("err.presentation.time.type.empty", errorMessageCodeArgumentCaptor.getValue());
            assertEquals(1, createPresentationRequestValidator.getExceptions().size());
            FieldNotValidException occurred = createPresentationRequestValidator.getExceptions().get(0);
            assertEquals("err.presentation.time.type.empty", occurred.getCode());
            assertEquals("test_error_message", occurred.getMessage());
            assertEquals("timeType", occurred.getFieldName());
        }
    }

    @Test(expected = ModelNotValidException.class)
    public void Should_add_FieldNotValidException_and_throw_ModelNotValidException_when_minutes_property_is_empty() {
        //given
        CreatePresentationRequest request = CreatePresentationRequest.builder()
                .name("test_presentation")
                .speakerId(1L)
                .timeType(PresentationTimeType.MINUTE)
                .build();

        doReturn("test_error_message").when(messages).getMessage("err.presentation.time.empty");

        //when
        createPresentationRequestValidator.validate(request);

        //then
        try {
            createPresentationRequestValidator.isValid();
        } finally {
            verify(messages).getMessage(errorMessageCodeArgumentCaptor.capture());
            assertEquals("err.presentation.time.empty", errorMessageCodeArgumentCaptor.getValue());
            assertEquals(1, createPresentationRequestValidator.getExceptions().size());
            FieldNotValidException occurred = createPresentationRequestValidator.getExceptions().get(0);
            assertEquals("err.presentation.time.empty", occurred.getCode());
            assertEquals("test_error_message", occurred.getMessage());
            assertEquals("minutes", occurred.getFieldName());
        }
    }

    @Test(expected = ModelNotValidException.class)
    public void Should_add_FieldNotValidException_and_throw_ModelNotValidException_when_speakerId_property_is_empty_or_zero() {
        //given
        CreatePresentationRequest request = CreatePresentationRequest.builder()
                .name("test_presentation")
                .timeType(PresentationTimeType.MINUTE)
                .minutes(30)
                .speakerId(0L)
                .build();

        doReturn("test_error_message").when(messages).getMessage("err.presentation.speaker.empty");

        //when
        createPresentationRequestValidator.validate(request);

        //then
        try {
            createPresentationRequestValidator.isValid();
        } finally {
            verify(messages).getMessage(errorMessageCodeArgumentCaptor.capture());
            assertEquals("err.presentation.speaker.empty", errorMessageCodeArgumentCaptor.getValue());
            assertEquals(1, createPresentationRequestValidator.getExceptions().size());
            FieldNotValidException occurred = createPresentationRequestValidator.getExceptions().get(0);
            assertEquals("err.presentation.speaker.empty", occurred.getCode());
            assertEquals("test_error_message", occurred.getMessage());
            assertEquals("speakerId", occurred.getFieldName());
        }
    }

    @Test(expected = ModelNotValidException.class)
    public void Should_add_FieldNotValidException_for_all_invalid_fields_an_throw_ModelNotValidException() {
        //given
        CreatePresentationRequest request = CreatePresentationRequest.builder()
                .timeType(PresentationTimeType.MINUTE)
                .build();

        doReturn("test_name_invalid_error_message").when(messages).getMessage("err.presentation.name.empty");
        doReturn("test_minutes_invalid_error_message").when(messages).getMessage("err.presentation.time.empty");
        doReturn("test_speakerId_invalid_error_message").when(messages).getMessage("err.presentation.speaker.empty");

        //when
        createPresentationRequestValidator.validate(request);

        //then
        try {
            createPresentationRequestValidator.isValid();
        } finally {
            verify(messages, times(3)).getMessage(errorMessageCodeArgumentCaptor.capture());
            assertEquals("err.presentation.name.empty", errorMessageCodeArgumentCaptor.getAllValues().get(0));
            assertEquals("err.presentation.time.empty", errorMessageCodeArgumentCaptor.getAllValues().get(1));
            assertEquals("err.presentation.speaker.empty", errorMessageCodeArgumentCaptor.getAllValues().get(2));
            assertEquals(3, createPresentationRequestValidator.getExceptions().size());

            FieldNotValidException nameInvalidException = createPresentationRequestValidator.getExceptions().get(0);
            assertEquals("err.presentation.name.empty", nameInvalidException.getCode());
            assertEquals("test_name_invalid_error_message", nameInvalidException.getMessage());
            assertEquals("name", nameInvalidException.getFieldName());


            FieldNotValidException minutesNotValidException = createPresentationRequestValidator.getExceptions().get(1);
            assertEquals("err.presentation.time.empty", minutesNotValidException.getCode());
            assertEquals("test_minutes_invalid_error_message", minutesNotValidException.getMessage());
            assertEquals("minutes", minutesNotValidException.getFieldName());


            FieldNotValidException speakerNotValidException = createPresentationRequestValidator.getExceptions().get(2);
            assertEquals("err.presentation.speaker.empty", speakerNotValidException.getCode());
            assertEquals("test_speakerId_invalid_error_message", speakerNotValidException.getMessage());
            assertEquals("speakerId", speakerNotValidException.getFieldName());
        }
    }

    @Test
    public void Should_be_valid() {
        //given
        CreatePresentationRequest request = CreatePresentationRequest.builder()
                .name("test_presentation")
                .timeType(PresentationTimeType.MINUTE)
                .minutes(60)
                .speakerId(1L)
                .build();

        //when
        createPresentationRequestValidator.validate(request);

        //then
        boolean valid = createPresentationRequestValidator.isValid();
        assertTrue(valid);
        assertEquals(0, createPresentationRequestValidator.getExceptions().size());
    }
}