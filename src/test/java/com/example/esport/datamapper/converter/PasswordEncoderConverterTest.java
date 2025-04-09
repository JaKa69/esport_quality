package com.example.esport.datamapper.converter;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.spi.MappingContext;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)
class PasswordEncoderConverterTest {
    @InjectMocks
    private PasswordEncoderConverter converter;
    @Mock
    private PasswordEncoder encoder;
    @Test
    void shouldEncodePassword() {
        // Given
        String rawPassword = "mySecret123";
        String encodedPassword = "encodedSecret123";

        @SuppressWarnings("unchecked")
        MappingContext<String, String> context = mock(MappingContext.class);
        when(context.getSource()).thenReturn(rawPassword);
        // When: Mock the encoder behavior
        when(encoder.encode(rawPassword)).thenReturn(encodedPassword);

        String result = converter.convert(context);
        // Then
        assertThat(result).isEqualTo(encodedPassword);
        verify(encoder, times(1)).encode(rawPassword);
    }
}