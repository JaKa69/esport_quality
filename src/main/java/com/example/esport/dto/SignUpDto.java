package com.example.esport.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignUpDto {
    String firstName;
    String lastName;
    String login;
    String mail;
    String password;
}
