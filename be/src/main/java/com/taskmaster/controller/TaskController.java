package com.taskmaster.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.taskmaster.model.TaskDTO;
import com.taskmaster.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("tasks")
public class TaskController {

    @Autowired
    TaskService taskService;

    @GetMapping("/tasks/{projectId}")
    public ResponseEntity<List<TaskDTO>> findTasks(@PathVariable String projectId) throws JsonProcessingException {
        List<TaskDTO> tasks = taskService.findTasksByProjectId(projectId);

        return ResponseEntity.ok(tasks);
    }

}
