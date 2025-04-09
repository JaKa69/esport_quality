package com.example.esport.service;

import com.example.esport.dto.CredentialsDto;
import com.example.esport.model.Customer;
import com.example.esport.repository.CustomerRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.nio.CharBuffer;

@Service
public class CustomerService  {
    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;
    public CustomerService(CustomerRepository customerRepository, PasswordEncoder passwordEncoder) {
        this.customerRepository = customerRepository;
        this.passwordEncoder = passwordEncoder;
    }
    public boolean usernameExists(String username) {
        return customerRepository.findByUsername(username).isPresent();
    }

    public Customer registerUser(Customer entity) {
        return customerRepository.save(
            entity
        );
    }
    public Customer login(CredentialsDto entity) throws Exception {
        Customer user = customerRepository.findByUsername(entity.getLogin())
                .orElseThrow(() -> new Exception("Unknown user"));

        if (passwordEncoder.matches(CharBuffer.wrap(entity.getPassword()),
                user.getPassword())) {
            return user;
        }
        throw new Exception("Invalid password");
    }
}
