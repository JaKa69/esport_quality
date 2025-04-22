package com.example.esport.service;

import com.example.esport.model.Event;
import com.example.esport.model.Customer;
import com.example.esport.model.Ticket;
import com.example.esport.repository.EventRepository;
import com.example.esport.repository.CustomerRepository;
import com.example.esport.repository.TicketRepository;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Optional;

@Service
public class TicketService {

    private final TicketRepository ticketRepository;
    private final EventRepository eventRepository;
    private final CustomerRepository customerRepository;

    public TicketService(TicketRepository ticketRepository, EventRepository eventRepository, CustomerRepository customerRepository) {
        this.ticketRepository = ticketRepository;
        this.eventRepository = eventRepository;
        this.customerRepository = customerRepository;
    }

    public byte[] createTicketAndGenerateQr(Long eventId, Long customerId) throws IOException {
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new RuntimeException("Event not found"));
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        Ticket ticket = new Ticket(39.99f, false, LocalDate.now(), "Gérard", event, customer);
        Ticket savedTicket = ticketRepository.save(ticket);

        return TicketImageService.generateTicketImage(
                savedTicket.getNameUser(),
                savedTicket.isMultipass(),
                savedTicket.getReservationDate().toString(),
                savedTicket.getPrice(),
                savedTicket.getId()
        );
    }

    public Ticket buyMultipass(Customer buyer, Event event, float price, String nameUser) {
        if (buyer == null) {
            throw new IllegalArgumentException("Buyer cannot be null");
        }
        if (event == null) {
            throw new IllegalArgumentException("Event cannot be null");
        }
        if (nameUser == null) {
            throw new IllegalArgumentException("Nom d'utilisateur invalide");
        }
        if (nameUser.isBlank()) {
            throw new IllegalArgumentException("Nom d'utilisateur invalide");
        }

        Optional<Ticket> existingTicket = ticketRepository.findByBuyerAndIsMultipassTrue(buyer);
        if (existingTicket.isPresent()) {
            throw new IllegalStateException("Le client possède déjà un multipass.");
        }
        Ticket ticket = new Ticket(price, true, LocalDate.now(), nameUser, event, buyer);

        return ticketRepository.save(ticket);

    }
}
