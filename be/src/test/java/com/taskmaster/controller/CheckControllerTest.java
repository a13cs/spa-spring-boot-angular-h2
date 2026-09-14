package com.taskmaster.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class CheckControllerTest {

    @InjectMocks
    CheckController testController;

    @Test
    public void pingTest() {
        ResponseEntity<String> response = testController.ping();
        String ok = response.getBody();

        assertEquals("ok", ok);
    }

}