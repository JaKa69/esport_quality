package com.example.esport.service;

import com.example.esport.fixture.CustomerFixture;
import com.example.esport.model.Customer;
import com.example.esport.repository.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserDetailServiceTest {
    @InjectMocks
    private UserDetailService userDetailService;
    @Mock
    private CustomerRepository customerRepository;
    @Test
    void loadUserByUsername_shouldReturnUserDetails_whenUserExists() {
        // Given
        Customer user = CustomerFixture.customerFixture();

        when(customerRepository.findByUsername("user123"))
                .thenReturn(Optional.of(user));

        // When
        UserDetails result = userDetailService.loadUserByUsername("user123");

        // Then
        assertEquals("username", result.getUsername());
    }

    @Test
    void loadUserByUsername_shouldThrowException_whenUserNotFound() {
        // Given
        when(customerRepository.findByUsername("unknown"))
                .thenReturn(Optional.empty());

        // When + Then
        assertThrows(UsernameNotFoundException.class, () -> userDetailService.loadUserByUsername("unknown"));
    }
}