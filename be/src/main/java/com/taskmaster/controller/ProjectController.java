package com.taskmaster.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.taskmaster.entity.Project;
import com.taskmaster.model.ProjectIdResponse;
import com.taskmaster.service.ProjectService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
public class ProjectController {

    @Autowired
    ProjectService projectService;


    @GetMapping("projects/all")
    public ResponseEntity<List<Project>> findAll() {
        List<Project> all = projectService.findAll();

        return ResponseEntity.ok(all);
    }

    @GetMapping("projects/{id}")
    public ResponseEntity<Project> findById(@PathVariable String id) {
        Optional<Project> project = projectService.findById(id);
        if (project.isPresent()) {
            return ResponseEntity.ok(project.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping(value = "/projects", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProjectIdResponse> save(@RequestBody @Valid Project project) throws JsonProcessingException {
        Project saved = projectService.save(project);

        ProjectIdResponse response = new ProjectIdResponse();
        String id = saved.getId();
        response.setId(id);

        return ResponseEntity.created(URI.create("/" + id)).body(response);
    }

    @PutMapping(value = "/projects", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Project> update(@RequestBody Project Project) throws JsonProcessingException {
        String id = Project.getId();
        if (id != null) {
            if (projectService.existsById(id)) {

                projectService.deleteById(id);
                Project saved = projectService.save(Project);
                return ResponseEntity.ok(saved);
            }
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping(value = "/projects/{id}")
    public ResponseEntity<Project> delete(@PathVariable String id) {
        if (projectService.existsById(id)) {
            projectService.deleteById(id);
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();
    }

}
