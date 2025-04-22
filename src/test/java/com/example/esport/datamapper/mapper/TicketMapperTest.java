package com.example.esport.datamapper.mapper;

import com.example.esport.dto.MultipassDto;
import com.example.esport.dto.TicketDto;
import com.example.esport.fixture.TicketFixture;
import com.example.esport.model.Ticket;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TicketMapperTest {
    @InjectMocks
    private TicketMapper mapper;
    @Mock
    private ModelMapper convertTicketToMultipassDto;
    @Mock
    private ModelMapper convertMultipassDtoToTicketEntity;
    @Mock
    private ModelMapper convertTicketToTicketDto;
    @Mock
    private ModelMapper convertTicketDtoToTicketEntity;
    @Test
    void convertToMultipassDto() {
        // Given
        Ticket ticket = TicketFixture.ticketMultipassFixture();
        when(convertTicketToMultipassDto.map(ticket, MultipassDto.class))
                .thenReturn(TicketFixture.multipassDtoFixture());

        // When
        MultipassDto result = mapper.convertToMultipassDto(ticket);

        // Then
        assertThat(result).isEqualTo(TicketFixture.multipassDtoFixture());
    }

    @Test
    void convertToDto() {
        // Given
        Ticket entity = TicketFixture.ticketFixture();
        when(convertTicketToTicketDto.map(entity, TicketDto.class))
                .thenReturn(TicketFixture.ticketDtoFixture());

        // When
        TicketDto result = mapper.convertToTicketDto(entity);

        // Then
        assertThat(result).isEqualTo(TicketFixture.ticketDtoFixture());
    }
    @Test
    void convertDtoToTicketEntity() {
        // Given
        TicketDto dto = TicketFixture.ticketDtoFixture();
        when(convertTicketDtoToTicketEntity.map(dto, Ticket.class))
                .thenReturn(TicketFixture.ticketFixture());

        // When
        Ticket result = mapper.convertTicketDtoToTicketEntity(dto);

        // Then
        assertThat(result).isEqualTo(TicketFixture.ticketFixture());
    }
    @Test
    void convertToTicketEntity() {
        // Given
        MultipassDto dto = TicketFixture.multipassDtoFixture();
        when(convertMultipassDtoToTicketEntity.map(dto, Ticket.class))
                .thenReturn(TicketFixture.ticketMultipassFixture());

        // When
        Ticket result = mapper.convertMultipassToTicketEntity(dto);

        // Then
        assertThat(result).isEqualTo(TicketFixture.ticketMultipassFixture());
    }
}