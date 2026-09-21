package com.taskmaster.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.taskmaster.entity.Project;
import com.taskmaster.repository.ProjectRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProjectService.class);

    @Autowired
    ProjectRepository projectRepository;

    private static final ObjectMapper OM = new ObjectMapper();
    static {
        OM.configure(SerializationFeature.INDENT_OUTPUT, true);
    }


    public List<Project> findAll() {
        return projectRepository.findAll();
    }

    public Optional<Project> findById(String id) {
        return projectRepository.findById(id);
    }

    public boolean existsById(String id) {
        return projectRepository.existsById(id);
    }

    @Transactional
    public Project save(Project project) throws JsonProcessingException {
        String id = project.getId();
        Optional<Project> p = findById(id);
        var message = "";
        if (p.isPresent()) {
            message = "Update";
            deleteById(id);
        } else {
            message = "Save";
        }

        Project saved = projectRepository.saveAndFlush(project);

        String json = OM.writeValueAsString(saved);
        LOGGER.info("{} project {}", message, json);

        return saved;
    }

    public void deleteById(String id) {
        projectRepository.deleteById(id);
    }

}
