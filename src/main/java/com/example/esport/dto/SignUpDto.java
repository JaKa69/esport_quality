package com.example.esport.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignUpDto {
    private String login;
    private String mail;
    private String password;
    private String firstName;
    private String lastName;
}
