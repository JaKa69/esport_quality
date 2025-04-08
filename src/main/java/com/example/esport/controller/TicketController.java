package com.example.esport.controller;

import com.example.esport.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/tickets")
public class TicketController {

    private final TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    /*@GetMapping("/generate-qr")
    public String generateQrTicket(@RequestParam Long eventId, @RequestParam Long customerId) throws IOException {
        String qrUrl = ticketService.createTicketAndGenerateQr(eventId, customerId);
        return "<html><body>"
                + "<p>QR Code du billet :</p>"
                + "<img src='" + qrUrl + "' alt='QR Code' />"
                + "</body></html>";
    }*/
}

