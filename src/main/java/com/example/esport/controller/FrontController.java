package com.example.esport.controller;

import com.example.esport.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayInputStream;

@RestController
@RequestMapping("/generate-ticket")
public class FrontController {


    private final TicketService ticketService;

    public FrontController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @GetMapping("/{customerId}/{eventId}")
    public byte[] generateTicket(@PathVariable Long customerId, @PathVariable Long eventId) throws Exception {
        // Appeler le service pour générer l'imagge
        return ticketService.createTicketAndGenerateQr(eventId, customerId); // Utilise les IDs appropriés ici
    }
}
