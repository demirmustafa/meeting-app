package io.github.demirmustafa.meetingapp.service;

import io.github.demirmustafa.meetingapp.api.model.request.CreatePresentationRequest;
import io.github.demirmustafa.meetingapp.domain.entity.Presentation;
import io.github.demirmustafa.meetingapp.domain.entity.Speaker;
import io.github.demirmustafa.meetingapp.domain.enums.PresentationTimeType;
import io.github.demirmustafa.meetingapp.exception.BusinessException;
import io.github.demirmustafa.meetingapp.exception.FaultCode;
import io.github.demirmustafa.meetingapp.mapper.PresentationResponseMapper;
import io.github.demirmustafa.meetingapp.repository.PresentationRepository;
import io.github.demirmustafa.meetingapp.repository.SpeakerRepository;
import org.junit.After;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class PresentationServiceTest {

    @Mock
    private PresentationRepository presentationRepository;

    @Mock
    private SpeakerRepository speakerRepository;

    @Mock
    private PresentationResponseMapper presentationResponseMapper;

    @InjectMocks
    private PresentationService presentationService;

    @Captor
    private ArgumentCaptor<Long> speakerIdArgumentCaptor;

    @Captor
    private ArgumentCaptor<Presentation> presentationArgumentCaptor;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();


    @After
    public void tearDown() {
        verifyNoMoreInteractions(presentationRepository, speakerRepository, presentationResponseMapper);
    }

    @Test
    public void Should_throw_BusinessException_with_PRESENTATION_SPEAKER_NOT_FOUND_fault_code_when_no_speaker_found_by_id() {
        //given
        CreatePresentationRequest request = CreatePresentationRequest.builder()
                .speakerId(1L)
                .build();
        doReturn(Optional.empty()).when(speakerRepository).findById(anyLong());
        expectedException.expect(BusinessException.class);
        expectedException.expectMessage(FaultCode.PRESENTATION_SPEAKER_NOT_FOUND.getCode());

        //when
        try {
            presentationService.create(request);
        }

        //then
        finally {
            verify(speakerRepository).findById(speakerIdArgumentCaptor.capture());
            assertEquals(request.getSpeakerId(), speakerIdArgumentCaptor.getValue());
        }
    }

    @Test
    public void Should_create_presentation() {
        //given
        CreatePresentationRequest request = CreatePresentationRequest.builder()
                .name("test_presentation")
                .timeType(PresentationTimeType.MINUTE)
                .minutes(30)
                .speakerId(1L)
                .build();

        Speaker speaker = new Speaker();
        Presentation presentation = new Presentation();

        doReturn(Optional.of(speaker)).when(speakerRepository).findById(anyLong());
        doReturn(presentation).when(presentationRepository).save(any(Presentation.class));

        //when
        presentationService.create(request);

        //then
        verify(speakerRepository).findById(speakerIdArgumentCaptor.capture());
        assertEquals(request.getSpeakerId(), speakerIdArgumentCaptor.getValue());

        verify(presentationRepository).save(presentationArgumentCaptor.capture());
        assertEquals(request.getName(), presentationArgumentCaptor.getValue().getName());
        assertEquals(request.getTimeType(), presentationArgumentCaptor.getValue().getPresentationTimeType());
        assertEquals(request.getMinutes(), presentationArgumentCaptor.getValue().getMinutes());
        assertEquals(speaker, presentationArgumentCaptor.getValue().getSpeaker());
        assertNull(presentationArgumentCaptor.getValue().getId());

        verify(presentationResponseMapper).entity2CreateResponse(any(Presentation.class));
    }

}