package com.example.esport.controller;

import com.example.esport.datamapper.mapper.EventMapper;
import com.example.esport.dto.EventDto;
import com.example.esport.service.EventService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/event")
public class EventController {
    private final EventService eventService;
    private final EventMapper eventMapper;
    public EventController(EventService eventService, EventMapper eventMapper) {
        this.eventService = eventService;
        this.eventMapper = eventMapper;
    }
    @GetMapping("/all")
    public ResponseEntity<List<EventDto>> getAllEvent() {
        return ResponseEntity.ok(
            eventService.getAllEvent()
                .stream().map(eventMapper::convertToDto)
                .toList()
        );
    }
    @GetMapping("/{eventId}")
    public ResponseEntity<EventDto> getEventById(@PathVariable("eventId") Long eventId) {
        return ResponseEntity.of(
            eventService.findEventById(eventId)
                .map(eventMapper::convertToDto)
        );
    }
    @PostMapping
    public ResponseEntity<EventDto> createOrUpdateEvent(@RequestBody EventDto eventDto) {
        return new ResponseEntity<>(
            eventMapper.convertToDto(
                eventService.saveEvent(
                    eventMapper.convertToEntity(
                        eventDto
                    )
                )
            ),
            HttpStatus.CREATED
        );
    }

    @DeleteMapping("/{eventId}")
    public ResponseEntity<Void> deleteEvent(@PathVariable Long eventId) {
        eventService.deleteEventById(eventId);
        return ResponseEntity.noContent().build();
    }
}
