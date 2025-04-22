package com.example.esport.datamapper.mapper;

import com.example.esport.dto.EventDto;
import com.example.esport.model.Event;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class EventMapper {
    @Autowired
    @Qualifier("eventEntityToDto")
    private ModelMapper convertEventToDto;
    @Autowired
    @Qualifier("eventDtoToEntity")
    private ModelMapper convertEventDtoToEntity;
    public EventDto convertToDto(Event entity) {
        return convertEventToDto.map(entity, EventDto.class);
    }
    public Event convertToEntity(EventDto entity) {
        return convertEventDtoToEntity.map(entity, Event.class);
    }
}
