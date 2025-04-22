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
            mapper.map(Ticket::getPrice, MultipassDto::setPrice);
            mapper.map(Ticket::getBuyer, MultipassDto::setBuyer);
            mapper.map(Ticket::getEvent, MultipassDto::setEvent);
            mapper.map(Ticket::getNameUser, MultipassDto::setNameUser);
        });
        return modelMapper;
    }
}
