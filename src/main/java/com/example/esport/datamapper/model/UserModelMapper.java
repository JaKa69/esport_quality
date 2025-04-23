package com.example.esport.datamapper.model;

import com.example.esport.datamapper.converter.PasswordEncoderConverter;
import com.example.esport.dto.SignUpDto;
import com.example.esport.dto.UserDto;
import com.example.esport.model.Customer;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserModelMapper {
    private final PasswordEncoderConverter passwordEncoderConverter;

    public UserModelMapper(PasswordEncoderConverter passwordEncoderConverter) {
        this.passwordEncoderConverter = passwordEncoderConverter;
    }

    @Bean("customerEntityToUserDto")
    public ModelMapper convertToDto() {
        ModelMapper modelMapper = new ModelMapper();
        TypeMap<Customer, UserDto> typeMap = modelMapper.createTypeMap(Customer.class, UserDto.class);
        typeMap.addMappings(mapper -> {
            mapper.map(Customer::getId, UserDto::setId);
            mapper.map(Customer::getMail, UserDto::setMail);
            mapper.map(Customer::getUsername, UserDto::setUsername);
            mapper.map(Customer::getFirstName, UserDto::setFirstName);
            mapper.map(Customer::getLastName, UserDto::setLastName);

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
            mapper.using(passwordEncoderConverter).map(SignUpDto::getPassword, Customer::setPassword);
            mapper.map(SignUpDto::getFirstName, Customer::setFirstName);
            mapper.map(SignUpDto::getLastName, Customer::setLastName);
        });
        return modelMapper;
    }
}
