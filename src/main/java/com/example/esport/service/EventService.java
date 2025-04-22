package com.example.esport.service;

import com.example.esport.model.Event;
import com.example.esport.repository.EventRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventService {
    private final EventRepository eventRepository;

    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }
    public List<Event> getAllEvent() {
        return eventRepository.findAll();
    }
    public Optional<Event> findEventById(Long eventId) {
        return eventRepository.findById(eventId);
    }
}
