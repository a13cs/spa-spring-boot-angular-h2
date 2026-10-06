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

@CrossOrigin(origins="http://localhost:4200", allowedHeaders = "*")
@RestController
@RequestMapping("/api/projects")
public class ProjectController {

    @Autowired
    ProjectService projectService;


    @GetMapping("/all")
    public ResponseEntity<List<Project>> findAll() {
        List<Project> all = projectService.findAll();

        return ResponseEntity.ok(all);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Project> findById(@PathVariable String id) {
        Optional<Project> project = projectService.findById(id);
        if (project.isPresent()) {
            return ResponseEntity.ok(project.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Project> save(@RequestBody @Valid Project project) throws JsonProcessingException {
        Project saved = projectService.save(null, project);

//        ProjectIdResponse response = new ProjectIdResponse();
//        String id = saved.getId();
//        response.setId(id);
//
//        return ResponseEntity.created(URI.create("/" + id)).body(response);
        return ResponseEntity.ok(saved);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Project> update(@PathVariable String id, @RequestBody @Valid Project project) throws JsonProcessingException {
        if (!id.equals(project.getId())) {
            return ResponseEntity.badRequest().build();
        }
        if (projectService.existsById(id)) {
            Project saved = projectService.save(id, project);
            return ResponseEntity.ok(saved);
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Project> delete(@PathVariable String id) {
        if (projectService.existsById(id)) {
            projectService.deleteById(id);
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();
    }

}
