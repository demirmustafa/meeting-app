package io.github.demirmustafa.meetingapp.api.validator;

import io.github.demirmustafa.meetingapp.Messages;
import io.github.demirmustafa.meetingapp.api.model.request.CreateSpeakerRequest;
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
public class CreateSpeakerRequestValidatorTest {

    @Mock
    private Messages messages;

    @InjectMocks
    private CreateSpeakerRequestValidator createSpeakerRequestValidator;

    @Captor
    private ArgumentCaptor<String> errorMessageCodeArgumentCaptor;

    @After
    public void tearDown() {
        verifyNoMoreInteractions(messages);
    }

    @Test(expected = ModelNotValidException.class)
    public void Should_add_invalid_request_exception_when_request_is_empty() {
        //given
        CreateSpeakerRequest request = null;
        doReturn("test_error_message").when(messages).getMessage(anyString());

        //when
        createSpeakerRequestValidator.validate(request);

        //then
        try {
            createSpeakerRequestValidator.isValid();
        } finally {
            verify(messages).getMessage(errorMessageCodeArgumentCaptor.capture());
            assertEquals("err.request.invalid", errorMessageCodeArgumentCaptor.getValue());
            assertEquals(1, createSpeakerRequestValidator.getExceptions().size());
            FieldNotValidException occurred = createSpeakerRequestValidator.getExceptions().get(0);
            assertEquals("err.request.invalid", occurred.getCode());
            assertEquals("test_error_message", occurred.getMessage());
        }
    }

    @Test(expected = ModelNotValidException.class)
    public void Should_add_FieldNotValidException_for_name_property_and_throw_ModelNotValid_exception_when_name_is_empty() {
        //given
        CreateSpeakerRequest request = CreateSpeakerRequest.builder()
                .surname("test_surname")
                .build();
        doReturn("test_error_message").when(messages).getMessage(anyString());

        //when
        createSpeakerRequestValidator.validate(request);

        //then
        try {
            createSpeakerRequestValidator.isValid();
        } finally {
            verify(messages).getMessage(errorMessageCodeArgumentCaptor.capture());
            assertEquals("err.speaker.name.empty", errorMessageCodeArgumentCaptor.getValue());
            assertEquals(1, createSpeakerRequestValidator.getExceptions().size());
            FieldNotValidException occurred = createSpeakerRequestValidator.getExceptions().get(0);
            assertEquals("err.speaker.name.empty", occurred.getCode());
            assertEquals("name", occurred.getFieldName());
            assertEquals("test_error_message", occurred.getMessage());
        }
    }

    @Test(expected = ModelNotValidException.class)
    public void Should_add_FieldNotValidException_for_surname_property_and_throw_ModelNotValid_exception_when_surname_is_empty() {
        //given
        CreateSpeakerRequest request = CreateSpeakerRequest.builder()
                .name("test_name")
                .build();
        doReturn("test_error_message").when(messages).getMessage(anyString());

        //when
        createSpeakerRequestValidator.validate(request);

        //then
        try {
            createSpeakerRequestValidator.isValid();
        } finally {
            verify(messages).getMessage(errorMessageCodeArgumentCaptor.capture());
            assertEquals("err.speaker.surname.empty", errorMessageCodeArgumentCaptor.getValue());
            assertEquals(1, createSpeakerRequestValidator.getExceptions().size());
            FieldNotValidException occurred = createSpeakerRequestValidator.getExceptions().get(0);
            assertEquals("err.speaker.surname.empty", occurred.getCode());
            assertEquals("surname", occurred.getFieldName());
            assertEquals("test_error_message", occurred.getMessage());
        }
    }

    @Test(expected = ModelNotValidException.class)
    public void Should_add_FieldNotValidException_for_all_invalid_fields_and_throw_ModelNotValidException() {
        //given
        CreateSpeakerRequest request = CreateSpeakerRequest.builder().build();
        doReturn("name_empty_error_message").when(messages).getMessage("err.speaker.name.empty");
        doReturn("surname_empty_error_message").when(messages).getMessage("err.speaker.surname.empty");

        //when
        createSpeakerRequestValidator.validate(request);

        //then
        try {
            createSpeakerRequestValidator.isValid();
        } finally {
            verify(messages, times(2)).getMessage(errorMessageCodeArgumentCaptor.capture());
            assertEquals("err.speaker.name.empty", errorMessageCodeArgumentCaptor.getAllValues().get(0));
            assertEquals("err.speaker.surname.empty", errorMessageCodeArgumentCaptor.getAllValues().get(1));
            assertEquals(2, createSpeakerRequestValidator.getExceptions().size());
            FieldNotValidException nameNotValidException = createSpeakerRequestValidator.getExceptions().get(0);
            assertEquals("err.speaker.name.empty", nameNotValidException.getCode());
            assertEquals("name_empty_error_message", nameNotValidException.getMessage());

            FieldNotValidException surnameNotValidException = createSpeakerRequestValidator.getExceptions().get(1);
            assertEquals("err.speaker.surname.empty", surnameNotValidException.getCode());
            assertEquals("surname_empty_error_message", surnameNotValidException.getMessage());
        }
    }

    @Test
    public void Should_valid() {
        //given
        CreateSpeakerRequest request = CreateSpeakerRequest.builder()
                .name("test_name")
                .surname("test_surname")
                .build();

        //when
        createSpeakerRequestValidator.validate(request);

        assertTrue(createSpeakerRequestValidator.isValid());
        assertEquals(0, createSpeakerRequestValidator.getExceptions().size());
    }
}