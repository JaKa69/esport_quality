package com.example.esport.service;

import com.example.esport.fixture.CustomerFixture;
import com.example.esport.fixture.EventFixture;
import com.example.esport.fixture.TicketFixture;
import com.example.esport.model.Customer;
import com.example.esport.model.Event;
import com.example.esport.model.Ticket;
import com.example.esport.repository.CustomerRepository;
import com.example.esport.repository.EventRepository;
import com.example.esport.repository.TicketRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
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
    public void testTicketIsCreatedAndSaved() throws IOException {
        // Given
        Event event = EventFixture.createEvent();
        Customer customer = CustomerFixture.customerFixture();
        when(eventRepository.findById(1L)).thenReturn(Optional.of(event));
        when(customerRepository.findById(1L)).thenReturn(Optional.of(customer));

        Ticket savedTicket = new Ticket(39.99f, false, LocalDate.now(), "Gérard", event, customer);
        savedTicket.setId(42);
        when(ticketRepository.save(any(Ticket.class))).thenReturn(savedTicket);

        // When
        byte[] result = ticketService.createTicketAndGenerateQr(1L, 1L);

        // Then
        assertNotNull(result);
        assertTrue(result.length > 0, "L'image générée ne doit pas être vide");

        ArgumentCaptor<Ticket> captor = ArgumentCaptor.forClass(Ticket.class);
        verify(ticketRepository).save(captor.capture());

        Ticket ticket = captor.getValue();
        assertEquals("Gérard", ticket.getNameUser());
        assertEquals(39.99f, ticket.getPrice());
        assertFalse(ticket.isMultipass());
        assertEquals(event, ticket.getEvent());
        assertEquals(customer, ticket.getBuyer());
    }
}
