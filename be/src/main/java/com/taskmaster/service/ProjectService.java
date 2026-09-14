package com.taskmaster.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.taskmaster.entity.Project;
import com.taskmaster.model.ProjectDTO;
import com.taskmaster.model.TaskDTO;
import com.taskmaster.repository.ProjectRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProjectService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProjectService.class);

    @Autowired
    ProjectRepository projectRepository;

    @Autowired
    TaskService taskService;

    private static final ObjectMapper OM = new ObjectMapper();
    static {
        OM.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        OM.configure(SerializationFeature.INDENT_OUTPUT, true);
    }


    public List<ProjectDTO> findAll() throws JsonProcessingException {
        List<Project> all = projectRepository.findAll();

        List<ProjectDTO> projectDTOs = new ArrayList<>();
        for (Project p : all) {
            projectDTOs.add(mapToDto(p));
        }
        return projectDTOs;
    }

    public List<ProjectDTO> findAllByName(String name) throws JsonProcessingException {
        Project e = new Project();
        e.setName(name);

        List<Project> all = projectRepository.findAll(Example.of(e));

        List<ProjectDTO> projectDTOs = new ArrayList<>();
        for (Project p : all) {
            projectDTOs.add(mapToDto(p));
        }
        return projectDTOs;
    }

    public Optional<ProjectDTO> findById(String id) throws JsonProcessingException {
        Optional<Project> p = projectRepository.findById(id);

        if (p.isPresent()) {
            return Optional.of(mapToDto(p.get()));
        }

        return Optional.empty();
    }

    public boolean existsById(String id) {
        return projectRepository.existsById(id);
    }

    @Transactional
    public ProjectDTO save(ProjectDTO projectDto) throws JsonProcessingException {
        Project p = mapToEntity(projectDto);

        String message = "Update";
        if (projectDto.getId() == null) {
            message = "Save";
            String id = UUID.randomUUID().toString();
            p.setId(id);
        }

        Project saved = projectRepository.saveAndFlush(p);
        List<TaskDTO> taskDTOS = taskService.saveAll(p, projectDto.getTasks());

        ProjectDTO savedDTO = mapToDto(saved);
        savedDTO.setTasks(taskDTOS);

        String json = OM.writeValueAsString(savedDTO);
        LOGGER.info("{} project {}", message, json);

        return savedDTO;
    }

    public void deleteById(String id) {
        projectRepository.deleteById(id);
    }


    private static ProjectDTO mapToDto(Project p) throws JsonProcessingException {
        String json = OM.writeValueAsString(p);

        return OM.readValue(json, new TypeReference<>(){});
    }

    private static Project mapToEntity(ProjectDTO p) throws JsonProcessingException {
        String json = OM.writeValueAsString(p);

        return OM.readValue(json, new TypeReference<>(){});
    }

}
