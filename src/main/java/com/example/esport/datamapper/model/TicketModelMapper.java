package com.example.esport.datamapper.model;

import com.example.esport.dto.MultipassDto;
import com.example.esport.dto.TicketDto;
import com.example.esport.model.Ticket;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TicketModelMapper {

    @Bean("ticketEntityToMultipassDto")
    public ModelMapper convertToMultipassDto() {
        ModelMapper modelMapper = new ModelMapper();
        TypeMap<Ticket, MultipassDto> typeMap = modelMapper.createTypeMap(Ticket.class, MultipassDto.class);
        typeMap.addMappings(mapper -> {
            mapper.map(Ticket::getId, MultipassDto::setId);
            mapper.map(Ticket::getPrice, MultipassDto::setPrice);
            mapper.map(Ticket::getBuyer, MultipassDto::setBuyer);
            mapper.map(Ticket::getEvent, MultipassDto::setEvent);
            mapper.map(Ticket::getNameUser, MultipassDto::setNameUser);
        });
        return modelMapper;
    }
    @Bean("multipassDtoToTicketEntity")
    public ModelMapper convertMultipassToTicketEntity() {
        ModelMapper modelMapper = new ModelMapper();
        TypeMap<MultipassDto, Ticket> typeMap = modelMapper.createTypeMap(MultipassDto.class, Ticket.class);
        typeMap.addMappings(mapper -> {
            mapper.map(MultipassDto::getId, Ticket::setId);
            mapper.map(MultipassDto::getPrice, Ticket::setPrice);
            mapper.map(MultipassDto::getBuyer, Ticket::setBuyer);
            mapper.map(MultipassDto::getEvent, Ticket::setEvent);
            mapper.map(MultipassDto::getNameUser, Ticket::setNameUser);
            mapper.map(src -> true, Ticket::setMultipass);
        });
        return modelMapper;
    }
    @Bean("ticketEntityToTicketDto")
    public ModelMapper convertToDto() {
        ModelMapper modelMapper = new ModelMapper();
        TypeMap<Ticket, TicketDto> typeMap = modelMapper.createTypeMap(Ticket.class, TicketDto.class);
        typeMap.addMappings(mapper -> {
            mapper.map(Ticket::getId, TicketDto::setId);
            mapper.map(Ticket::getPrice, TicketDto::setPrice);
            mapper.map(Ticket::getBuyer, TicketDto::setBuyer);
            mapper.map(Ticket::getEvent, TicketDto::setEvent);
            mapper.map(Ticket::getNameUser, TicketDto::setNameUser);
            mapper.map(Ticket::getReservationDate, TicketDto::setDteEvent);
        });
        return modelMapper;
    }
    @Bean("ticketDtoToTicketEntity")
    public ModelMapper convertTicketDtoToEntity() {
        ModelMapper modelMapper = new ModelMapper();
        TypeMap<TicketDto, Ticket> typeMap = modelMapper.createTypeMap(TicketDto.class, Ticket.class);
        typeMap.addMappings(mapper -> {
            mapper.map(TicketDto::getId, Ticket::setId);
            mapper.map(TicketDto::getPrice, Ticket::setPrice);
            mapper.map(TicketDto::getBuyer, Ticket::setBuyer);
            mapper.map(TicketDto::getEvent, Ticket::setEvent);
            mapper.map(TicketDto::getNameUser, Ticket::setNameUser);
            mapper.map(TicketDto::getDteEvent, Ticket::setReservationDate);
            mapper.map(src -> false, Ticket::setMultipass);
        });
        return modelMapper;
    }
}
