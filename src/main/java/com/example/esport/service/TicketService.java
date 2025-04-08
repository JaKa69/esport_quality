package com.example.esport.service;

import com.example.esport.model.Event;
import com.example.esport.model.Customer;
import com.example.esport.model.Ticket;
import com.example.esport.repository.EventRepository;
import com.example.esport.repository.CustomerRepository;
import com.example.esport.repository.TicketRepository;
import org.springframework.stereotype.Service;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.math.BigDecimal;

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

    public String createTicketAndGenerateQr(Long eventId, Long customerId) {

        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new RuntimeException("Event not found"));
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        // Créer et enregistrer le ticket
        Ticket ticket = new Ticket();
        ticket.setPrice(BigDecimal.valueOf(39.99));
        ticket.setMultipass(false);
        ticket.setReservationDate(LocalDate.now());
        ticket.setNameUser("Gérard");
        ticket.setEvent(event);
        ticket.setBuyer(customer);

        Ticket savedTicket = ticketRepository.save(ticket); // ID généré ici

        // Générer URL + QR Code
        String baseUrl = "https://tonapp.com/validate?ticket=" + savedTicket.getId();
        String encoded = URLEncoder.encode(baseUrl, StandardCharsets.UTF_8);
        String qrUrl = "https://api.qrserver.com/v1/create-qr-code/?size=300x300&data=" + encoded;

        return qrUrl;
    }
}
