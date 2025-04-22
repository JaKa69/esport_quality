package com.example.esport;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EsportApplicationTest {

    @Test
    void main() {
        assertDoesNotThrow(() -> EsportApplication.main(new String[]{}));
    }
}