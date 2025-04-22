package com.example.esport.datamapper.model;

import com.example.esport.dto.MultipassDto;
import com.example.esport.fixture.TicketFixture;
import com.example.esport.model.Ticket;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
@ExtendWith(MockitoExtension.class)
class TicketModelMapperTest {
    @InjectMocks
    private TicketModelMapper mapper;
    @Test
    void convertToMultipassDto() {
        //GIVEN
        Ticket entity = TicketFixture.ticketFixture();
        MultipassDto dto = TicketFixture.multipassDtoFixture();
        //WHEN
        ModelMapper modelMapper = mapper.convertToMultipassDto();
        MultipassDto result = modelMapper.map(entity, MultipassDto.class);
        //THEN
        assertEquals(result, dto);
    }

    @Test
    void convertToTicketEntity() {
        //GIVEN
        Ticket entity = TicketFixture.ticketMultipassFixture();
        MultipassDto dto = TicketFixture.multipassDtoFixture();
        //WHEN
        ModelMapper modelMapper = mapper.convertMultipassToTicketEntity();
        Ticket result = modelMapper.map(dto, Ticket.class);
        //THEN
        assertEquals(result, entity);
    }
}