package com.example.esport.service;

import com.example.esport.model.Event;
import com.example.esport.model.Customer;
import com.example.esport.model.Ticket;
import com.example.esport.repository.EventRepository;
import com.example.esport.repository.CustomerRepository;
import com.example.esport.repository.TicketRepository;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
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
        // Récupère l'événement et le client
        System.out.println(eventId);
        Event event = eventRepository.findById(eventId).orElseThrow(() -> new RuntimeException("Event not found"));
        Customer customer = customerRepository.findById(customerId).orElseThrow(() -> new RuntimeException("Customer not found"));

        // Crée et sauvegarde le ticket
        Ticket ticket = new Ticket(39.99f, false, LocalDate.now(), "Gérard", event, customer);
        Ticket savedTicket = ticketRepository.save(ticket);

        // Génère le QR Code et l'image du ticket
        String qrUrl = generateQrUrl(savedTicket.getId());
        byte[] ticketImage = TicketImageService.generateTicketImage(
                savedTicket.getNameUser(),
                savedTicket.isMultipass(),
                savedTicket.getReservationDate().toString(),
                savedTicket.getPrice(),
                savedTicket.getId()
        );

        // Retourne l'URL du QR Code (image déjà générée dans le service d'image)
        return ticketImage;
    }

    private String generateQrUrl(int ticketId) throws IOException {
        String baseUrl = "https://tonapp.com/validate?ticket=" + ticketId;
        String encoded = URLEncoder.encode(baseUrl, StandardCharsets.UTF_8);
        System.out.println("Encoded URL: " + encoded);
        return "https://api.qrserver.com/v1/create-qr-code/?size=300x300&data=" + encoded;
    }
}
