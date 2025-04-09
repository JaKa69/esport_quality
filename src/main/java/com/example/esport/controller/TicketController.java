package com.example.esport.controller;

import com.example.esport.dto.MultipassDto;
import com.example.esport.model.Ticket;
import com.example.esport.service.TicketService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tickets")
public class TicketController {

    private final TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @GetMapping("/{customerId}/{eventId}")
    public byte[] generateTicket(@PathVariable Long customerId, @PathVariable Long eventId) throws Exception {
        // Appeler le service pour générer l'imagge
        return ticketService.createTicketAndGenerateQr(eventId, customerId); // Utilise les IDs appropriés ici
    }

    @PostMapping("/multipass")
    public ResponseEntity<?> buyMultipass(@RequestBody MultipassDto request) {
        try {
            Ticket ticket = ticketService.buyMultipass(
                    request.getBuyer(),
                    request.getEvent(),
                    request.getPrice(),
                    request.getNameUser()
            );
            return ResponseEntity.ok(ticket);
        } catch (IllegalArgumentException | IllegalStateException | SecurityException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
