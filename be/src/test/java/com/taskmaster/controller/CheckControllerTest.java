package com.taskmaster.controller;

import com.taskmaster.entity.Project;
import com.taskmaster.service.ProjectService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
class CheckControllerTest {

    @Mock
    ProjectService projectService;

    @InjectMocks
    ProjectController projectController;

    @Test
    public void findAllTest() {
        ResponseEntity<List<Project>> response = projectController.findAll();

        List<Project> body = response.getBody();
        assertTrue(body != null && body.isEmpty());
    }

}