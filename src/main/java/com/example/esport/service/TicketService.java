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

    public Ticket buyTicket(Ticket ticket, boolean isMultipass) {
        Customer buyer = Optional.ofNullable(ticket.getBuyer())
                .orElseThrow(() -> new IllegalArgumentException("Buyer cannot be null"));

        if (ticket.getEvent() == null)
            throw new IllegalArgumentException("Event cannot be null");

        if (ticket.getNameUser() == null || ticket.getNameUser().isBlank())
            throw new IllegalArgumentException("Nom d'utilisateur invalide");

        boolean ticketExists = isMultipass
                ? ticketRepository.findByBuyerAndIsMultipassTrue(buyer).isPresent()
                : ticketRepository.findByBuyerAndIsMultipassFalse(buyer).isPresent();

        if (ticketExists)
            throw new IllegalStateException("Le client possède déjà un ticket/multipass.");

        return ticketRepository.save(ticket);
    }
}
