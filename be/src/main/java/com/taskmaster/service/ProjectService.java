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
    public Project save(String id, Project project) throws JsonProcessingException {
        if (id != null && !id.equals(project.getId())) {
            return null;
        }

        Project saved = projectRepository.save(project);

        String json = OM.writeValueAsString(saved);
        LOGGER.info("{} project {}", id == null ? "save" : "update", json);

        return saved;
    }

    public void deleteById(String id) {
        projectRepository.deleteById(id);
    }

}
