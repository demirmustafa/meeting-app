package io.github.demirmustafa.meetingapp.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.demirmustafa.meetingapp.Messages;
import io.github.demirmustafa.meetingapp.api.model.request.CreatePresentationRequest;
import io.github.demirmustafa.meetingapp.api.model.resource.PresentationResource;
import io.github.demirmustafa.meetingapp.api.model.resource.SpeakerResource;
import io.github.demirmustafa.meetingapp.api.model.response.CreatePresentationResponse;
import io.github.demirmustafa.meetingapp.domain.enums.PresentationTimeType;
import io.github.demirmustafa.meetingapp.exception.BusinessException;
import io.github.demirmustafa.meetingapp.exception.FaultCode;
import io.github.demirmustafa.meetingapp.service.PresentationService;
import lombok.SneakyThrows;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = PresentationController.class)
public class PresentationControllerTest {

    @MockBean
    private PresentationService presentationService;

    @MockBean
    private Messages messages;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @SneakyThrows
    public void Should_get_all_presentations() {
        //given
        List<PresentationResource> presentations = Collections.singletonList(
                PresentationResource.builder()
                        .id(1L)
                        .name("Test Presentation")
                        .minutes(30)
                        .type(PresentationTimeType.MINUTE)
                        .speaker(
                                SpeakerResource.builder()
                                        .id(1L)
                                        .name("name")
                                        .surname("surname")
                                        .build()
                        ).build()
        );

        doReturn(presentations).when(presentationService).getAll();

        //when
        ResultActions resultActions = mockMvc.perform(
                get("/presentations")
                        .header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
        ).andDo(print());

        //then
        resultActions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].id").exists())
                .andExpect(jsonPath("$.[0].name").exists())
                .andExpect(jsonPath("$.[0].minutes").exists())
                .andExpect(jsonPath("$.[0].type").exists())
                .andExpect(jsonPath("$.[0].speaker").exists());
    }

    @Test
    @SneakyThrows
    public void Should_create_presentation() {
        //given
        CreatePresentationRequest request = CreatePresentationRequest.builder()
                .name("Test Presentation")
                .minutes(30)
                .timeType(PresentationTimeType.MINUTE)
                .speakerId(1L)
                .build();

        doReturn(
                CreatePresentationResponse.builder()
                        .id(1L)
                        .build()
        ).when(presentationService)
                .create(any(CreatePresentationRequest.class));

        //when
        ResultActions resultActions = mockMvc.perform(
                post("/presentations")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(request))
        ).andDo(print());

        //then
        resultActions
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").exists());
    }

    @Test
    @SneakyThrows
    public void Should_return_error_response_when_thrown_BusinessException() {
        //given
        CreatePresentationRequest request = CreatePresentationRequest.builder()
                .name("Test Presentation")
                .minutes(30)
                .timeType(PresentationTimeType.MINUTE)
                .speakerId(1L)
                .build();

        doReturn("test-error-message").when(messages).getMessage(FaultCode.PRESENTATION_SPEAKER_NOT_FOUND.getCode());
        doThrow(new BusinessException(FaultCode.PRESENTATION_SPEAKER_NOT_FOUND)).when(presentationService).create(any(CreatePresentationRequest.class));

        //when
        ResultActions resultActions = mockMvc.perform(
                post("/presentations")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(request))
        ).andDo(print());

        //then
        resultActions
                .andExpect(status().isUnprocessableEntity())
                .andExpect(jsonPath("$.status").value(422))
                .andExpect(jsonPath("$.message").exists());
    }
}