package com.example.esport.datamapper.model;

import com.example.esport.dto.MultipassDto;
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
    public ModelMapper convertToTicketEntity() {
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
}
