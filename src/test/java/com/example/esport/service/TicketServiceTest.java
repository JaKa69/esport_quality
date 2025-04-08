package com.example.esport.service;


import com.example.esport.fixture.CustomerFixture;
import com.example.esport.fixture.EventFixture;
import com.example.esport.model.Customer;
import com.example.esport.model.Event;
import com.example.esport.model.Ticket;
import com.example.esport.repository.CustomerRepository;
import com.example.esport.repository.EventRepository;
import com.example.esport.repository.TicketRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TicketServiceTest {

    @InjectMocks
    private TicketService ticketService;

    @Mock
    private TicketRepository ticketRepository;

    @Mock
    private EventRepository eventRepository;

    @Mock
    private CustomerRepository customerRepository;



    @Test
    public void testTicketIsCreatedAndSaved() {
        // Given
        when(eventRepository.findById(1L)).thenReturn(Optional.of(EventFixture.createEvent()));
        when(customerRepository.findById(1L)).thenReturn(Optional.of(CustomerFixture.createCustomer()));

        Ticket ticketWithId = new Ticket();
        ticketWithId.setId(99); // simulate saved ticket with ID
        when(ticketRepository.save(any(Ticket.class))).thenReturn(ticketWithId);

        // When
        String result = ticketService.createTicketAndGenerateQr(1L, 1L);

        // Then
        ArgumentCaptor<Ticket> ticketCaptor = ArgumentCaptor.forClass(Ticket.class);
        verify(ticketRepository, times(1)).save(ticketCaptor.capture());

        Ticket savedTicket = ticketCaptor.getValue();
        assertEquals(39.99f, savedTicket.getPrice());
        assertEquals(false, savedTicket.isMultipass());
        assertEquals("Gérard", savedTicket.getNameUser());
        assertEquals(EventFixture.createEvent(), savedTicket.getEvent());
        assertEquals(CustomerFixture.createCustomer(), savedTicket.getBuyer());
    }

    @Test
    public void testQrCodeUrlIsGeneratedCorrectly() {
        // Given
        when(eventRepository.findById(1L)).thenReturn(Optional.of(EventFixture.createEvent()));
        when(customerRepository.findById(1L)).thenReturn(Optional.of(CustomerFixture.createCustomer()));

        Ticket ticketWithId = new Ticket();
        ticketWithId.setId(42); // simulate saved ticket with ID
        when(ticketRepository.save(any(Ticket.class))).thenReturn(ticketWithId);

        // When
        String result = ticketService.createTicketAndGenerateQr(1L, 1L);

        // Then
        assertTrue(result.contains("https://api.qrserver.com/v1/create-qr-code/"));
        assertTrue(result.contains("ticket%3D42"));
    }
}
