package com.example.esport.fixture;

import com.example.esport.dto.CredentialsDto;
import com.example.esport.dto.SignUpDto;

public class CredentialsFixture {
    public static CredentialsDto credentialsDtoFixture() {
        return new CredentialsDto(
            "toto",
            "password"
        );
    }
    public static CredentialsDto unknowncredentialsDtoFixture() {
        return new CredentialsDto(
            "unknown",
            "none"
        );
    }
    public static SignUpDto signUpDtoFixture() {
        return new SignUpDto(
                "firstname",
                "lastname",
                "username",
                "firstname.lastname@toto.com",
                "password"
        );
    }
}
