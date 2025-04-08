package com.example.esport.fixture;

import com.example.esport.dto.UserDto;
import com.example.esport.model.Customer;

public class CustomerFixture {
    public static Customer customerFixture() {
        return new Customer(
            1L,
            "username",
            "firstname",
            "lastname",
            "password",
            "mail",
            null
        );
    }
    public static UserDto userDtoFixture() {
        return new UserDto(
                1L,
                "username",
                "username.toto@toto.com",
                "password"
        );
    }
}
