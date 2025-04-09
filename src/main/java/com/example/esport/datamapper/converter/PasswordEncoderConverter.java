package com.example.esport.datamapper.converter;

import org.modelmapper.Converter;
import org.modelmapper.spi.MappingContext;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class PasswordEncoderConverter implements Converter<String, String> {

    private final PasswordEncoder passwordEncoder;

    public PasswordEncoderConverter(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public String convert(MappingContext<String, String> context) {
        String rawPassword = context.getSource();
        return passwordEncoder.encode(rawPassword);
    }
}
