package com.example.esport.datamapper.model;

import com.example.esport.dto.SignUpDto;
import com.example.esport.dto.UserDto;
import com.example.esport.model.Customer;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserModelMapper {

    @Bean("customerEntityToUserDto")
    public ModelMapper convertToDto() {
        ModelMapper modelMapper = new ModelMapper();
        TypeMap<Customer, UserDto> typeMap = modelMapper.createTypeMap(Customer.class, UserDto.class);
        typeMap.addMappings(mapper -> {
            mapper.map(Customer::getId, UserDto::setId);
            mapper.map(Customer::getMail, UserDto::setMail);
            mapper.map(Customer::getUsername, UserDto::setUsername);
        });
        return modelMapper;
    }
    @Bean("signUpDtoToCustomerEntity")
    public ModelMapper convertSignUpToEntity() {
        ModelMapper modelMapper = new ModelMapper();
        TypeMap<SignUpDto, Customer> typeMap = modelMapper.createTypeMap(SignUpDto.class, Customer.class);
        typeMap.addMappings(mapper -> {
            mapper.map(SignUpDto::getMail, Customer::setMail);
            mapper.map(SignUpDto::getLogin, Customer::setUsername);
            mapper.map(SignUpDto::getPassword, Customer::setPassword);
            mapper.map(SignUpDto::getFirstName, Customer::setFirstname);
            mapper.map(SignUpDto::getLastName, Customer::setLastName);
        });
        return modelMapper;
    }
}
