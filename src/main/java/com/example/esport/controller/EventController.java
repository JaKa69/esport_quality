package com.example.esport.controller;

import com.example.esport.datamapper.mapper.EventMapper;
import com.example.esport.dto.EventDto;
import com.example.esport.service.EventService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
