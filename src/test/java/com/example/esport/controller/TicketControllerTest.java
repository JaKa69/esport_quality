package com.example.esport.controller;

import com.example.esport.datamapper.mapper.TicketMapper;
import com.example.esport.dto.MultipassDto;
import com.example.esport.dto.TicketDto;
import com.example.esport.fixture.TicketFixture;
import com.example.esport.model.Ticket;
import com.example.esport.service.TicketService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(TicketController.class)
@WithMockUser
class TicketControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TicketService ticketService;

    @MockBean
    private TicketMapper ticketMapper;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldGenerateTicket() throws Exception {
        Long customerId = 1L;
        Long eventId = 1L;
        byte[] fakeQrCode = "QR_CODE".getBytes();

        when(ticketService.createTicketAndGenerateQr(eventId, customerId)).thenReturn(fakeQrCode);

        mockMvc.perform(get("/tickets/{customerId}/{eventId}", customerId, eventId))
                .andExpect(status().isOk())
                .andExpect(content().bytes(fakeQrCode));
    }

    @Test
    void shouldBuyMultipassSuccessfully() throws Exception {
        MultipassDto dto = TicketFixture.multipassDtoFixture();
        Ticket ticket = TicketFixture.ticketFixture();

        when(ticketMapper.convertMultipassToTicketEntity(dto)).thenReturn(ticket);
        when(ticketService.buyTicket(ticket, true)).thenReturn(ticket);
        when(ticketMapper.convertToMultipassDto(ticket)).thenReturn(dto);

        mockMvc.perform(post("/tickets/multipass")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto))
                        .with(csrf()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.event.id").value(dto.getEvent().getId()))
                .andExpect(jsonPath("$.buyer.id").value(dto.getBuyer().getId()));
    }

    @Test
    void shouldReturnBadRequestIfBuyMultipassFails() throws Exception {
        MultipassDto dto = TicketFixture.multipassDtoFixture();

        when(ticketMapper.convertMultipassToTicketEntity(dto)).thenThrow(new IllegalArgumentException("Invalid ticket"));

        mockMvc.perform(post("/tickets/multipass")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto))
                        .with(csrf()))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Invalid ticket"));
    }
    @Test
    void shouldBuyTicketSuccessfully() throws Exception {
        TicketDto dto = TicketFixture.ticketDtoFixture();
        Ticket ticket = TicketFixture.ticketFixture();

        when(ticketMapper.convertTicketDtoToTicketEntity(dto))
                .thenReturn(ticket);
        when(ticketService.buyTicket(ticket, false))
                .thenReturn(ticket);
        when(ticketMapper.convertToTicketDto(ticket))
                .thenReturn(dto);

        mockMvc.perform(post("/tickets/ticket")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto))
                        .with(csrf()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.event.id").value(dto.getEvent().getId()))
                .andExpect(jsonPath("$.buyer.id").value(dto.getBuyer().getId()));
    }

    @Test
    void shouldReturnBadRequestIfBuyTicketFails() throws Exception {
        TicketDto dto = TicketFixture.ticketDtoFixture();

        when(ticketMapper.convertTicketDtoToTicketEntity(dto)).thenThrow(new IllegalArgumentException("Invalid ticket"));

        mockMvc.perform(post("/tickets/ticket")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto))
                        .with(csrf()))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Invalid ticket"));
    }
}