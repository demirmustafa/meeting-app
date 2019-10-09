package io.github.demirmustafa.meetingapp.service;

import io.github.demirmustafa.meetingapp.api.model.resource.ConferenceResource;
import io.github.demirmustafa.meetingapp.domain.enums.PresentationTimeType;
import io.github.demirmustafa.meetingapp.mapper.PresentationResponseMapper;
import io.github.demirmustafa.meetingapp.mapper.SpeakerResponseMapper;
import io.github.demirmustafa.meetingapp.repository.PresentationRepository;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ConferenceServiceTest {

    @Mock
    private PresentationRepository presentationRepository;

    @Mock
    private SpeakerResponseMapper speakerResponseMapper;

    @Mock
    private PresentationResponseMapper presentationResponseMapper;

    @InjectMocks
    private ConferenceService conferenceService;

    @Captor
    private ArgumentCaptor<PresentationTimeType> presentationTypeArgumentCaptor;

    @After
    public void tearDown() {
        verifyNoMoreInteractions(presentationRepository, presentationResponseMapper, speakerResponseMapper);
    }

    @Test
    public void Should_return_empty_response_when_no_presentation_found() {
        //given
        doReturn(new ArrayList<>()).when(presentationRepository).findAllByPresentationTimeTypeIs(any(PresentationTimeType.class));

        //when
        ConferenceResource actual = conferenceService.get();

        //then
        verify(presentationRepository).findAllByPresentationTimeTypeIs(presentationTypeArgumentCaptor.capture());
        assertEquals(PresentationTimeType.MINUTE, presentationTypeArgumentCaptor.getValue());
        assertNotNull(actual);
        assertEquals(0, actual.getTracks().size());
    }
}