package com.taskmaster.controller;

import com.taskmaster.entity.Project;
import com.taskmaster.entity.Task;
import com.taskmaster.service.ProjectService;
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

    @Autowired
    ProjectService projectService;

    @GetMapping("/project/{projectId}")
    public ResponseEntity<List<Task>> findTasks(@PathVariable String projectId) {
        List<Task> tasks = taskService.findTasksByProjectId(projectId);

        return ResponseEntity.ok(tasks);
    }

    @GetMapping("/{taskId}")
    public ResponseEntity<Task> findTask(@PathVariable Long taskId) {
        Optional<Task> task = taskService.find(taskId);
        if (!task.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(task.get());
    }

    @PostMapping("/{projectId}")
    public ResponseEntity<Task> saveTask(@PathVariable String projectId, @RequestBody Task task) {
        Optional<Project> project = projectService.findById(projectId);
        if (!project.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        Project p = project.get();
        task.setProject(p);
        Task saved = taskService.save(task);

//        return ResponseEntity.created(URI.create("/api/tasks/" + saved.getId())).build();
        return ResponseEntity.ok(saved);
    }

    @PutMapping("/{projectId}/{taskId}")
    public ResponseEntity<Task> updateTask(@PathVariable String projectId, @PathVariable Long taskId, @RequestBody Task task) {
        if ((long) taskId != task.getId()) {
            return ResponseEntity.badRequest().build();
        }
        Optional<Project> project = projectService.findById(projectId);
        if (!project.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        Project p = project.get();

        if (!taskService.existsById(taskId)) {
            return ResponseEntity.notFound().build();
        }
        task.setProject(p);
        taskService.save(task);

        return ResponseEntity.ok(task);
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
