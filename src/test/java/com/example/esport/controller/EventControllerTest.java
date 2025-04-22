package com.example.esport.controller;

import com.example.esport.datamapper.mapper.EventMapper;
import com.example.esport.dto.EventDto;
import com.example.esport.fixture.EventFixture;
import com.example.esport.model.Event;
import com.example.esport.service.EventService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(EventController.class)
@WithMockUser
class EventControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EventService eventService;

    @MockBean
    private EventMapper eventMapper;

    @Autowired
    private ObjectMapper objectMapper;
    @Test
    void shouldReturnAllEvents() throws Exception {
        List<Event> events = List.of(EventFixture.eventFixture());
        List<EventDto> eventDtos = List.of(EventFixture.eventDtoFixture());

        when(eventService.getAllEvent()).thenReturn(events);
        when(eventMapper.convertToDto(events.get(0))).thenReturn(eventDtos.get(0));

        mockMvc.perform(get("/event/all")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Test Event")); // adapte selon ton fixture
    }

    @Test
    void shouldReturnEventById() throws Exception {
        Event event = EventFixture.eventFixture();
        EventDto eventDto = EventFixture.eventDtoFixture();

        when(eventService.findEventById(1L)).thenReturn(Optional.of(event));
        when(eventMapper.convertToDto(event)).thenReturn(eventDto);

        mockMvc.perform(get("/event/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Test Event")); // adapte selon ton fixture
    }

    @Test
    void shouldReturn404IfEventNotFound() throws Exception {
        when(eventService.findEventById(99L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/event/99")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldCreateOrUpdateEvent() throws Exception {
        EventDto dto = EventFixture.eventDtoFixture();
        Event event = EventFixture.eventFixture();

        Mockito.when(eventMapper.convertToEntity(dto)).thenReturn(event);
        Mockito.when(eventService.saveEvent(event)).thenReturn(event);
        Mockito.when(eventMapper.convertToDto(event)).thenReturn(dto);

        mockMvc.perform(
            post("/event")
              .contentType(MediaType.APPLICATION_JSON)
              .content(objectMapper.writeValueAsString(dto))
              .with(csrf())
        ).andExpect(status().isCreated())
         .andExpect(jsonPath("$.id").value(1L));
    }

    @Test
    void shouldDeleteEvent() throws Exception {
        Long eventId = 1L;

        mockMvc.perform(delete("/event/{id}", eventId)
            .with(csrf())
        ).andExpect(status().isNoContent());

        Mockito.verify(eventService).deleteEventById(eventId);
    }

}