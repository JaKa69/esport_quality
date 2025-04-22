package com.example.esport.datamapper.model;

import com.example.esport.dto.EventDto;
import com.example.esport.model.Event;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EventModelMapper {
    @Bean("eventEntityToDto")
    public ModelMapper convertEventEntityToDto() {
        ModelMapper modelMapper = new ModelMapper();
        TypeMap<Event, EventDto> typeMap = modelMapper.createTypeMap(Event.class, EventDto.class);
        typeMap.addMappings(mapper -> {
            mapper.map(Event::getId, EventDto::setId);
            mapper.map(Event::getName, EventDto::setName);
            mapper.map(Event::getEndDate, EventDto::setEndDate);
            mapper.map(Event::getStartDate, EventDto::setStartDate);
        });
        return modelMapper;
    }
    @Bean("eventDtoToEntity")
    public ModelMapper convertEventDtoToEntity() {
        ModelMapper modelMapper = new ModelMapper();
        TypeMap<EventDto, Event> typeMap = modelMapper.createTypeMap(EventDto.class, Event.class);
        typeMap.addMappings(mapper -> {
            mapper.map(EventDto::getId, Event::setId);
            mapper.map(EventDto::getName, Event::setName);
            mapper.map(EventDto::getStartDate, Event::setStartDate);
            mapper.map(EventDto::getEndDate, Event::setEndDate);
        });
        return modelMapper;
    }
}
