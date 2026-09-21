package com.taskmaster.controller;

import com.taskmaster.entity.Task;
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
    public ResponseEntity<List<Task>> findTasks(@PathVariable String projectId) {
        List<Task> tasks = taskService.findTasksByProjectId(projectId);

        return ResponseEntity.ok(tasks);
    }

}
