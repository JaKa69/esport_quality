package com.example.esport.datamapper.mapper;

import com.example.esport.dto.SignUpDto;
import com.example.esport.dto.UserDto;
import com.example.esport.fixture.CredentialsFixture;
import com.example.esport.fixture.CustomerFixture;
import com.example.esport.model.Customer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserMapperTest {
    @InjectMocks
    private UserMapper mapper;
    @Mock
    private ModelMapper convertCustomerToUserDto;
    @Mock
    private ModelMapper convertSignUpDtoToCustomer;

    @Test
    void convertToDto() {
        // Given
        Customer user = CustomerFixture.customerFixture();
        when(convertCustomerToUserDto.map(user, UserDto.class))
                .thenReturn(CustomerFixture.userDtoFixture());

        // When
        UserDto result = mapper.convertToDto(user);

        // Then
        assertThat(result).isEqualTo(CustomerFixture.userDtoFixture());
    }

    @Test
    void convertSignUpToEntity() {
        // Given
        SignUpDto signup = CredentialsFixture.signUpDtoFixture();
        when(convertSignUpDtoToCustomer.map(signup, Customer.class))
                .thenReturn(CustomerFixture.customerFixture());
        // When
        Customer result = mapper.convertSignUpToEntity(signup);

        // Then
        assertThat(result).isEqualTo(CustomerFixture.customerFixture());
    }
}