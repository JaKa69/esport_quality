package com.example.esport.datamapper.mapper;

import com.example.esport.dto.MultipassDto;
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
    public MultipassDto convertToMultipassDto(Ticket entity) {
        return convertTicketToMultipassDto.map(entity, MultipassDto.class);
    }
}
