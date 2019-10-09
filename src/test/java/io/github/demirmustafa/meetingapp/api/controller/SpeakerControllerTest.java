package io.github.demirmustafa.meetingapp.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.demirmustafa.meetingapp.Messages;
import io.github.demirmustafa.meetingapp.api.model.request.CreateSpeakerRequest;
import io.github.demirmustafa.meetingapp.api.model.resource.SpeakerResource;
import io.github.demirmustafa.meetingapp.api.model.response.CreateSpeakerResponse;
import io.github.demirmustafa.meetingapp.service.SpeakerService;
import io.github.demirmustafa.meetingapp.validation.exception.ModelNotValidException;
import lombok.SneakyThrows;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.Arrays;
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
@WebMvcTest(controllers = SpeakerController.class)
public class SpeakerControllerTest {

    @MockBean
    private SpeakerService speakerService;

    @MockBean
    private Messages messages;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @SneakyThrows
    public void Should_get_all_speakers() {
        //given
        List<SpeakerResource> all = Arrays.asList(
                SpeakerResource.builder()
                        .id(1L)
                        .name("name")
                        .surname("surname")
                        .company("Other")
                        .position("Java Developer")
                        .bio("Lorem ipsum")
                        .build()
        );
        doReturn(all).when(speakerService).getAll();

        //when
        ResultActions resultActions = mockMvc.perform(
                get("/speakers")
        ).andDo(print());

        //then
        resultActions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].id").exists())
                .andExpect(jsonPath("$.[0].name").exists())
                .andExpect(jsonPath("$.[0].surname").exists())
                .andExpect(jsonPath("$.[0].company").exists())
                .andExpect(jsonPath("$.[0].position").exists())
                .andExpect(jsonPath("$.[0].bio").exists());
    }

    @Test
    @SneakyThrows
    public void Should_create_speaker() {
        //given
        CreateSpeakerRequest request = CreateSpeakerRequest.builder()
                .name("name")
                .surname("surname")
                .company("other")
                .position("other")
                .bio("other")
                .build();

        doReturn(
                CreateSpeakerResponse.builder()
                        .id(1L)
                        .build()
        ).when(speakerService)
                .create(any(CreateSpeakerRequest.class));

        //when
        ResultActions resultActions = mockMvc.perform(
                post("/speakers")
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
    public void should_return_error_response_when_name_and_surname_empty() {
        //given
        CreateSpeakerRequest request = CreateSpeakerRequest.builder()
                .build();

        doThrow(new ModelNotValidException("test ex")).when(speakerService).create(any(CreateSpeakerRequest.class));

        //when
        ResultActions resultActions = mockMvc.perform(
                post("/speakers")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(request))
        ).andDo(print());

        //then
        resultActions
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status").value(400))
                .andExpect(jsonPath("$.message").exists());
    }
}