package com.example.esport.dto;

import com.example.esport.model.Customer;
import com.example.esport.model.Event;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MultipassDto {
    private Customer buyer;
    private Event event;
    private float price;
    private String nameUser;
}

