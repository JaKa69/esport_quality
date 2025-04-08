package com.example.esport.fixture;

import com.example.esport.model.Event;

import java.time.LocalDate;

public class EventFixture {
    public static Event createEvent() {
        return new Event(1, "Test Event", LocalDate.of(2023, 10, 1), LocalDate.of(2023, 10, 2), null);
    }

}
