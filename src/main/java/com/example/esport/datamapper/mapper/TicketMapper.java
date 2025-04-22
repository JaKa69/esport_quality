package com.example.esport.datamapper.mapper;

import com.example.esport.dto.MultipassDto;
import com.example.esport.dto.TicketDto;
import com.example.esport.model.Ticket;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class TicketMapper {
    @Autowired
    @Qualifier("ticketEntityToMultipassDto")
    private ModelMapper convertTicketToMultipassDto;
    @Autowired
    @Qualifier("ticketEntityToTicketDto")
    private ModelMapper convertTicketToTicketDto;
    @Autowired
    @Qualifier("multipassDtoToTicketEntity")
    private ModelMapper convertMultipassDtoToTicketEntity;
    @Autowired
    @Qualifier("ticketDtoToTicketEntity")
    private ModelMapper convertTicketDtoToTicketEntity;
    public MultipassDto convertToMultipassDto(Ticket entity) {
        return convertTicketToMultipassDto.map(entity, MultipassDto.class);
    }
    public TicketDto convertToTicketDto(Ticket entity) {
        return convertTicketToTicketDto.map(entity, TicketDto.class);

    }
    public Ticket convertMultipassToTicketEntity(MultipassDto entity) {
        return convertMultipassDtoToTicketEntity.map(entity, Ticket.class);
    }

    public Ticket convertTicketDtoToTicketEntity(TicketDto dto) {
        return convertTicketDtoToTicketEntity.map(dto, Ticket.class);

    }
}
