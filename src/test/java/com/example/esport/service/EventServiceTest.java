package com.example.esport.service;

import com.example.esport.fixture.EventFixture;
import com.example.esport.model.Event;
import com.example.esport.repository.EventRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.verify;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EventServiceTest {
    @InjectMocks
    private EventService service;
    @Mock
    private EventRepository repository;
    @Test
    void getAllEvent() {
        //GIVEN
        when(repository.findAll())
            .thenReturn(EventFixture.eventListFixture());
        //WHEN
        List<Event> result = service.getAllEvent();
        //THEN
        assertThat(result).isEqualTo(EventFixture.eventListFixture());
    }

    @Test
    void findEventById() {
        //GIVEN
        Long eventId = 1L;
        when(repository.findById(eventId))
                .thenReturn(Optional.of(EventFixture.eventFixture()));
        //WHEN
        Optional<Event> result = service.findEventById(eventId);
        //THEN
        assertThat(result).isEqualTo(Optional.of(EventFixture.eventFixture()));
    }
    @Test
    void saveEvent() {
        // GIVEN
        Event eventToSave = EventFixture.eventFixture();
        when(repository.save(eventToSave)).thenReturn(eventToSave);

        // WHEN
        Event result = service.saveEvent(eventToSave);

        // THEN
        assertThat(result).isEqualTo(eventToSave);
    }
    @Test
    void deleteEventById() {
        // GIVEN
        Long eventId = 1L;

        // WHEN
        service.deleteEventById(eventId);

        // THEN
        verify(repository).deleteById(eventId);
    }
}