package com.example.esport.controller;

import com.example.esport.datamapper.mapper.TicketMapper;
import com.example.esport.dto.MultipassDto;
import com.example.esport.dto.TicketDto;
import com.example.esport.service.TicketService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tickets")
public class TicketController {

    private final TicketService ticketService;
    private final TicketMapper ticketMapper;

    public TicketController(TicketService ticketService, TicketMapper ticketMapper) {
        this.ticketService = ticketService;
        this.ticketMapper = ticketMapper;
    }

    @GetMapping("/{customerId}/{eventId}")
    public byte[] generateTicket(@PathVariable Long customerId, @PathVariable Long eventId) throws Exception {
        // Appeler le service pour générer l'imagge
        return ticketService.createTicketAndGenerateQr(eventId, customerId); // Utilise les IDs appropriés ici
    }

    @PostMapping("/multipass")
    public ResponseEntity<?> buyMultipass(@RequestBody MultipassDto request) {
        try {
            return ResponseEntity.ok(
                ticketMapper.convertToMultipassDto(
                    ticketService.buyTicket(
                        ticketMapper.convertMultipassToTicketEntity(request),
                        true
                    )
                )
            );
        } catch (IllegalArgumentException | IllegalStateException | SecurityException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @PostMapping("/ticket")
    public ResponseEntity<?> buySingleTicket(@RequestBody TicketDto request) {
        try {
            return ResponseEntity.ok(
                ticketMapper.convertToTicketDto(
                    ticketService.buyTicket(
                        ticketMapper.convertTicketDtoToTicketEntity(request),
                        false
                    )
                )
            );
        } catch (IllegalArgumentException | IllegalStateException | SecurityException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
