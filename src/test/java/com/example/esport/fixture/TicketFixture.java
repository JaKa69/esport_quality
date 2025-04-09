package com.example.esport.fixture;

import com.example.esport.model.Event;
import com.example.esport.model.Ticket;

import java.time.LocalDate;

public class TicketFixture {
    public static Ticket createTicket() {
        return new Ticket(1, EventFixture.createEvent(), CustomerFixture.customerFixture(), 100.0f, false, LocalDate.parse("2025-04-09"), "John Doe");
    }
}
