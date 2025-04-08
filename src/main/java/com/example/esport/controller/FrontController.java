package com.example.esport.controller;

import com.example.esport.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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

    @GetMapping
    public byte[] generateTicket() throws Exception {
        // Appeler le service pour générer l'image
        return ticketService.createTicketAndGenerateQr(1L, 1L); // Utilise les IDs appropriés ici
    }
}
