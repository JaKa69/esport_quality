package com.example.esport.service;

import com.example.esport.dto.CredentialsDto;
import com.example.esport.fixture.CredentialsFixture;
import com.example.esport.fixture.CustomerFixture;
import com.example.esport.model.Customer;
import com.example.esport.repository.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.nio.CharBuffer;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CustomerServiceTest {
    @InjectMocks
    private CustomerService service;
    @Mock
    private CustomerRepository repository;
    @Mock
    private PasswordEncoder passwordEncoder;
    @Test
    void usernameExists() {
        //GIVEN
        String username = "toto";
        when(repository.findByUsername(username)).
            thenReturn(Optional.of(CustomerFixture.customerFixture()));
        //WHEN
        boolean result = service.usernameExists(username);
        //THEN
        assertTrue(result);
    }
    @Test
    void usernameDoesntExists() {
        //GIVEN
        String username = "toto";
        when(repository.findByUsername(username)).
            thenReturn(Optional.empty());
        //WHEN
        boolean result = service.usernameExists(username);
        //THEN
        assertFalse(result);
    }

    @Test
    void registerUser() {
        //GIVEN
        Customer customer=CustomerFixture.customerFixture();
        when(repository.save(customer)).
                thenReturn((CustomerFixture.customerFixture()));
        //WHEN
        Customer result = service.registerUser(customer);
        //THEN
        assertThat(result).isEqualTo(CustomerFixture.customerFixture());
    }

    @Test
    void login() throws Exception {
        //GIVEN
        CredentialsDto credentials= CredentialsFixture.credentialsDtoFixture();
        Customer customer = CustomerFixture.customerFixture();
        when(repository.findByUsername(credentials.getLogin())).
                thenReturn(Optional.of(customer));
        when(passwordEncoder.matches(CharBuffer.wrap(credentials.getPassword()), customer.getPassword()))
                .thenReturn(true);
        //WHEN
        Customer result = service.login(credentials);
        //THEN
        assertThat(result).isEqualTo(CustomerFixture.customerFixture());
    }
    @Test
    void loginUnknown() {
        //GIVEN
        CredentialsDto credentials= CredentialsFixture.unknowncredentialsDtoFixture();
        when(repository.findByUsername("unknown"))
                .thenReturn(Optional.empty());

        // When + Then
        Exception exception = assertThrows(Exception.class, () -> service.login(credentials));

        assertEquals("Unknown user", exception.getMessage());
    }
    @Test
    void loginthrow() {
        //GIVEN
        CredentialsDto credentials= CredentialsFixture.credentialsDtoFixture();
        Customer customer = CustomerFixture.customerFixture();
        when(repository.findByUsername(credentials.getLogin())).
                thenReturn(Optional.of(customer));
        when(passwordEncoder.matches(CharBuffer.wrap(credentials.getPassword()), customer.getPassword()))
                .thenReturn(false);
        //WHEN
        Exception exception = assertThrows(Exception.class, () -> service.login(credentials));

        assertEquals("Invalid password", exception.getMessage());
    }
}