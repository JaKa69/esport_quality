package com.example.esport.controller;

import com.example.esport.dto.CredentialsDto;
import com.example.esport.dto.SignUpDto;
import com.example.esport.dto.UserDto;
import com.example.esport.fixture.CredentialsFixture;
import com.example.esport.fixture.CustomerFixture;
import com.example.esport.model.Customer;
import com.example.esport.service.CustomerService;
import com.example.esport.datamapper.mapper.UserMapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.beans.factory.annotation.Autowired;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CustomerController.class)
@WithMockUser
class CustomerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CustomerService customerService;

    @MockBean
    private UserMapper userMapper;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldRegisterSuccessfully() throws Exception {
        SignUpDto signUp = CredentialsFixture.signUpDtoFixture();
        Customer customer = CustomerFixture.customerFixture();
        UserDto userDto = CustomerFixture.userDtoFixture();

        Mockito.when(customerService.usernameExists(signUp.getLogin())).thenReturn(false);
        Mockito.when(userMapper.convertSignUpToEntity(signUp)).thenReturn(customer);
        Mockito.when(customerService.registerUser(customer)).thenReturn(customer);
        Mockito.when(userMapper.convertToDto(customer)).thenReturn(userDto);

        mockMvc.perform(post("/register").with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(signUp)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value("username"));
    }

    @Test
    void shouldLoginSuccessfully() throws Exception {
        CredentialsDto credentials = CredentialsFixture.credentialsDtoFixture();
        Customer customer = CustomerFixture.customerFixture();
        UserDto userDto = CustomerFixture.userDtoFixture();

        Mockito.when(customerService.login(credentials)).thenReturn(customer);
        Mockito.when(userMapper.convertToDto(customer)).thenReturn(userDto);

        mockMvc.perform(post("/api/login").with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(credentials)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value("username"));
    }

    @Test
    void shouldReturnBadRequestWhenUsernameExists() throws Exception {
        SignUpDto signUp = CredentialsFixture.signUpDtoFixture();

        Mockito.when(customerService.usernameExists(signUp.getLogin())).thenReturn(true);

        mockMvc.perform(post("/register").with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(signUp)))
                .andExpect(status().isBadRequest());
    }
}