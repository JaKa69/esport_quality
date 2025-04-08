package com.example.esport.controller;

import com.example.esport.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tickets")
public class TicketController {

    @Autowired
    private TicketService ticketService;

    @GetMapping("/generate-qr")
    public String generateQrTicket(@RequestParam Long eventId, @RequestParam Long customerId) {
        String qrUrl = ticketService.createTicketAndGenerateQr(eventId, customerId);
        return "<html><body>"
                + "<p>QR Code du billet :</p>"
                + "<img src='" + qrUrl + "' alt='QR Code' />"
                + "</body></html>";
    }
}
