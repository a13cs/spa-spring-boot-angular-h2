package com.taskmaster.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.taskmaster.model.ProjectDTO;
import com.taskmaster.model.ProjectIdResponse;
import com.taskmaster.service.ProjectService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController("projects")
public class ProjectController {

    @Autowired
    ProjectService projectService;


    @GetMapping("projects/all")
    public ResponseEntity<List<ProjectDTO>> findAll() throws JsonProcessingException {
        List<ProjectDTO> all = projectService.findAll();

        return ResponseEntity.ok(all);
    }

    @GetMapping("projects/all/{name}")
    public ResponseEntity<List<ProjectDTO>> findAllByName(@PathVariable String name) throws JsonProcessingException {
        List<ProjectDTO> p = projectService.findAllByName(name);

        return ResponseEntity.ok(p);
    }

    @PostMapping(value = "/projects", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProjectIdResponse> save(@RequestBody @Valid ProjectDTO project) throws JsonProcessingException {
        ProjectDTO saved = projectService.save(project);

        ProjectIdResponse response = new ProjectIdResponse();
        response.setId(saved.getId());

        return ResponseEntity.created(URI.create("")).body(response);
    }

    @PutMapping(value = "/projects", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProjectDTO> update(@RequestBody ProjectDTO projectDTO) throws JsonProcessingException {
        String id = projectDTO.getId();
        if (id != null) {
            if (projectService.existsById(id)) {

                projectService.deleteById(id);
                ProjectDTO saved = projectService.save(projectDTO);
                return ResponseEntity.ok(saved);
            }
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping(value = "/projects/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProjectDTO> delete(@PathVariable String id) {
        if (projectService.existsById(id)) {
            projectService.deleteById(id);
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping(value = "projects/all/{name}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProjectDTO> deleteAll(@PathVariable String name) throws JsonProcessingException {
        List<ProjectDTO> allByName = projectService.findAllByName(name);

        if (allByName.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        for (ProjectDTO projectDTO : allByName) {
            String id = projectDTO.getId();
            projectService.deleteById(id);
        }
        return ResponseEntity.noContent().build();
    }
}
