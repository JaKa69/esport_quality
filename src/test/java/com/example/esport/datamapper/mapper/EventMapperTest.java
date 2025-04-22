package com.example.esport.datamapper.mapper;

import com.example.esport.dto.EventDto;
import com.example.esport.fixture.EventFixture;
import com.example.esport.model.Event;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EventMapperTest {
    @InjectMocks
    private EventMapper mapper;
    @Mock
    private ModelMapper convertEventToDto;
    @Mock
    private ModelMapper convertEventDtoToEntity;

    @Test
    void convertToDto() {
        // Given
        Event event = EventFixture.eventFixture();
        when(convertEventToDto.map(event, EventDto.class))
                .thenReturn(EventFixture.eventDtoFixture());

        // When
        EventDto result = mapper.convertToDto(event);

        // Then
        assertThat(result).isEqualTo(EventFixture.eventDtoFixture());
    }

    @Test
    void convertToEntity() {
        // Given
        EventDto dto = EventFixture.eventDtoFixture();
        when(convertEventDtoToEntity.map(dto, Event.class))
                .thenReturn(EventFixture.eventFixture());

        // When
        Event result = mapper.convertToEntity(dto);

        // Then
        assertThat(result).isEqualTo(EventFixture.eventFixture());
    }
}