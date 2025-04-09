package com.example.esport.datamapper.model;

import com.example.esport.datamapper.converter.PasswordEncoderConverter;
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
import org.modelmapper.spi.MappingContext;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserModelMapperTest {
    @InjectMocks
    private UserModelMapper mapper;
    @Mock
    private PasswordEncoderConverter passwordEncoderConverter;

    @Test
    void testConvertSignUpDtoToCustomer() {
        // Given: Création d'un SignUpDto avec des données de test
        SignUpDto signUpDto = CredentialsFixture.signUpDtoFixture();
        String encodedPassword = "encoded_password_123";

        when(passwordEncoderConverter.convert(any(MappingContext.class)))
                .thenReturn(encodedPassword);
        // When: Conversion du DTO en entité Customer
        ModelMapper modelMapper = mapper.convertSignUpToEntity();
        Customer result = modelMapper.map(signUpDto, Customer.class);

        // Then: Vérifier que les propriétés sont correctement mappées
        assertThat(result).usingRecursiveComparison().ignoringFields("password", "id").isEqualTo(CustomerFixture.customerFixture());
    }

    @Test
    void testConvertCustomerToUserDto() {
        // Given: Création d'un Customer avec des données de test
        Customer customer = CustomerFixture.customerFixture();

        // When: Conversion de l'entité Customer en UserDto
        ModelMapper modelMapper = mapper.convertToDto();
        UserDto result = modelMapper.map(customer, UserDto.class);

        // Then: Vérifier que les propriétés sont correctement mappées
        assertThat(result).isEqualTo(CustomerFixture.userDtoFixture());
    }
}