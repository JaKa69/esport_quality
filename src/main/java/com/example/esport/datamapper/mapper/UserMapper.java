package com.example.esport.datamapper.mapper;

import com.example.esport.dto.SignUpDto;
import com.example.esport.dto.UserDto;
import com.example.esport.model.Customer;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    @Autowired
    @Qualifier("customerEntityToUserDto")
    private ModelMapper convertCustomerToUserDto;
    @Autowired
    @Qualifier("signUpDtoToCustomerEntity")
    private ModelMapper convertSignUpDtoToCustomer;
    public UserDto convertToDto(Customer entity) {
        return convertCustomerToUserDto.map(entity, UserDto.class);
    }
    public Customer convertSignUpToEntity(SignUpDto dto) {
        return convertSignUpDtoToCustomer.map(dto, Customer.class);
    }
}
