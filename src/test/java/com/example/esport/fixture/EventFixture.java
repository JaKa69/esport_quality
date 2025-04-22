package com.example.esport.fixture;

import com.example.esport.dto.EventDto;
import com.example.esport.model.Event;

import java.time.LocalDate;
import java.util.List;

public class EventFixture {
    public static Event eventFixture() {
        return new Event(
            1L,
            "Test Event",
            LocalDate.of(2023, 10, 1),
            LocalDate.of(2023, 10, 2),
            null
        );
    }
    public static List<Event> eventListFixture() {
        return List.of(
                eventFixture()
        );
    }
    public static EventDto eventDtoFixture() {
        return new EventDto(
            1L,
            "Test Event",
            LocalDate.of(2023, 10, 1),
            LocalDate.of(2023, 10, 2)
        );
    }
    public static List<EventDto> eventDtoListFixture() {
        return List.of(
                eventDtoFixture()
        );
    }
}
