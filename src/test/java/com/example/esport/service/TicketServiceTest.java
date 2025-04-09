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
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
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

    private Customer buyer;
    private Event event;

    @BeforeEach
    void setUp() {
        buyer = new Customer();
        event = new Event();
    }

    @Test
    public void testTicketIsCreatedAndSaved() throws IOException {
        // Given
        Event event = EventFixture.eventFixture();
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

    @Test
    void buyMultipass_OK() {
        // GIVEN
        Customer buyer = new Customer(); // Assurez-vous de créer un client valide
        Event event = new Event(); // Assurez-vous de créer un événement valide

        when(ticketRepository.findByBuyerAndIsMultipassTrue(buyer)).thenReturn(Optional.empty());
        when(ticketRepository.save(any())).thenAnswer(invocation -> invocation.getArgument(0));

        // WHEN
        Ticket ticket = ticketService.buyMultipass(buyer, event, 59.99f, "Jean Dupont");

        // THEN
        assertNotNull(ticket);
        assertTrue(ticket.isMultipass());  // Vérifie que le ticket est bien un multipass
        assertEquals("Jean Dupont", ticket.getNameUser());  // Vérifie que le nom est correct
        verify(ticketRepository).save(any(Ticket.class));  // Vérifie que la méthode save() a été appelée
    }


    @Test
    void buyMultipass_incompleteData() {
        // Test de l'argument null pour l'événement
        assertThrows(IllegalArgumentException.class, () ->
                ticketService.buyMultipass(buyer, null, 59.99f, "Jean Dupont"));

        // Test de l'argument null pour le nom de l'utilisateur
        assertThrows(IllegalArgumentException.class, () ->
                ticketService.buyMultipass(buyer, event, 59.99f, null));
    }






    @Test
    void buyMultipass_alreadyExists() {
        // GIVEN : le client a déjà un multipass
        when(ticketRepository.findByBuyerAndIsMultipassTrue(buyer))
                .thenReturn(Optional.of(new Ticket()));

        // WHEN + THEN
        assertThrows(IllegalStateException.class, () ->
                ticketService.buyMultipass(buyer, event, 59.99f, "Jean Dupont"));
    }

    @Test
    void buyMultipass_withoutConnection() {
        assertThrows(IllegalArgumentException.class, () ->
                ticketService.buyMultipass(null, event, 59.99f, "Jean Dupont"));
    }

}