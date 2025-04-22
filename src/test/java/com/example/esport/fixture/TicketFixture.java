package com.example.esport.fixture;

import com.example.esport.model.Ticket;

import java.time.LocalDate;

public class TicketFixture {
    public static Ticket ticketFixture() {
        return new Ticket(1, EventFixture.eventFixture(), CustomerFixture.customerFixture(), 100.0f, false, LocalDate.parse("2025-04-09"), "John Doe");
    }
}
