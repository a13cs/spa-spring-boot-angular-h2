package com.taskmaster.controller;

import com.taskmaster.entity.Task;
import com.taskmaster.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins="http://localhost:4200", allowedHeaders = "*")
@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    @Autowired
    TaskService taskService;

    @GetMapping("/{projectId}")
    public ResponseEntity<List<Task>> findTasks(@PathVariable String projectId) {
        List<Task> tasks = taskService.findTasksByProjectId(projectId);

        return ResponseEntity.ok(tasks);
    }

    @DeleteMapping("/{taskId}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long taskId) {
        Optional<Task> task = taskService.find(taskId);
        if (task.isPresent()) {
            Task t = task.get();
            taskService.remove(t.getId());

            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.badRequest().build();
    }

}
