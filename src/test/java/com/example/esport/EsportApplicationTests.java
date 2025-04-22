package com.example.esport;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@ActiveProfiles("test")
@TestPropertySource(locations = "classpath:application-test.properties")
public class EsportApplicationTests {

    @Test
    void contextLoads() {
        // Test pour valider le chargement du contexte Spring
    }
}
