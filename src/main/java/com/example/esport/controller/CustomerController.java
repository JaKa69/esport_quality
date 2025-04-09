package com.example.esport.controller;

import com.example.esport.datamapper.mapper.UserMapper;
import com.example.esport.dto.CredentialsDto;
import com.example.esport.dto.SignUpDto;
import com.example.esport.dto.UserDto;
import com.example.esport.service.CustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerController {
    private final CustomerService customerService;
    private final UserMapper userMapper;

    public CustomerController(CustomerService customerService, UserMapper userMapper) {
        this.customerService = customerService;
        this.userMapper = userMapper;
    }


    @PostMapping("/register")
    public ResponseEntity<UserDto> register(@RequestBody SignUpDto user) {
        if (customerService.usernameExists(user.getLogin())) {
            return ResponseEntity.badRequest().body(null);
        }
        return ResponseEntity.ok(
            userMapper.convertToDto(
                customerService.registerUser(
                    userMapper.convertSignUpToEntity(
                        user
                    )
                )
            )
        );
    }

    @PostMapping("/api/login")
    public ResponseEntity<UserDto> login(@RequestBody CredentialsDto user) throws Exception {
        return ResponseEntity.ok(
            userMapper.convertToDto(
                customerService.login(
                    user
                )
            )
        );
    }
}
