package com.example.esport.fixture;

import com.example.esport.dto.MultipassDto;
import com.example.esport.dto.TicketDto;
import com.example.esport.model.Ticket;

import java.time.LocalDate;

public class TicketFixture {
    public static Ticket ticketFixture() {
        return new Ticket(
            1,
            EventFixture.eventFixture(),
            CustomerFixture.customerFixture(),
            100.0f,
            false,
            LocalDate.parse("2025-04-09"),
            "John Doe"
        );
    }
    public static Ticket ticketMultipassFixture() {
        return new Ticket(
            1,
            EventFixture.eventFixture(),
            CustomerFixture.customerFixture(),
            100.0f,
            true,
            null,
            "John Doe"
        );
    }
    public static Ticket ticketWithInvalidDatasBuyerFixture() {
        return new Ticket(
            1,
            EventFixture.eventFixture(),
            null,
            100.0f,
            false,
            LocalDate.parse("2025-04-09"),
            "John Doe"
        );
    }
    public static Ticket ticketWithInvalidDatasEventFixture() {
        return new Ticket(
            1,
            null,
            CustomerFixture.customerFixture(),
            100.0f,
            false,
            LocalDate.parse("2025-04-09"),
            "John Doe"
        );
    }
    public static Ticket ticketWithInvalidDatasNameUserFixture() {
        return new Ticket(
            1,
            EventFixture.eventFixture(),
            CustomerFixture.customerFixture(),
            100.0f,
            false,
            LocalDate.parse("2025-04-09"),
            ""
        );
    }
    public static MultipassDto multipassDtoFixture() {
        return new MultipassDto(
            1L,
            CustomerFixture.customerFixture(),
            EventFixture.eventFixture(),
            100.0f,
            "John Doe"
        );
    }
    public static TicketDto ticketDtoFixture() {
        return new TicketDto(
            1L,
            CustomerFixture.customerFixture(),
            EventFixture.eventFixture(),
            LocalDate.parse("2025-04-09"),
            100.0f,
            "John Doe"
        );
    }
}
