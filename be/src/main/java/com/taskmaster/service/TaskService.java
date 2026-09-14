package com.taskmaster.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.taskmaster.entity.Project;
import com.taskmaster.entity.Task;
import com.taskmaster.model.TaskDTO;
import com.taskmaster.repository.TaskRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TaskService {

    @Autowired
    TaskRepository taskRepository;

    private static final ObjectMapper OM = new ObjectMapper();
    static {
        OM.findAndRegisterModules();

        OM.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        OM.configure(SerializationFeature.INDENT_OUTPUT, true);
    }

    @Transactional
    public TaskDTO save(Project project, TaskDTO dto) throws JsonProcessingException {
        Task task = mapToEntity(dto);
        task.setProject(project);
        Task saved = taskRepository.save(task);

        return mapToDto(saved);
    }

    @Transactional
    public List<TaskDTO> saveAll(Project project, List<TaskDTO> taskDTOs) throws JsonProcessingException {
        List<TaskDTO> saved = new ArrayList<>();
        for (TaskDTO dto : taskDTOs) {
            Task task = mapToEntity(dto);
            task.setProject(project);

            Task s = taskRepository.save(task);
            saved.add(mapToDto(s));
        }

        return saved;
    }

    private static TaskDTO mapToDto(Task p) throws JsonProcessingException {
        String json = OM.writeValueAsString(p);

        return OM.readValue(json, new TypeReference<>(){});
    }

    private static Task mapToEntity(TaskDTO p) throws JsonProcessingException {
        String json = OM.writeValueAsString(p);

        return OM.readValue(json, new TypeReference<>(){});
    }


    public List<TaskDTO> findTasksByProjectId(String projectId) throws JsonProcessingException {
        Project p = new Project();
        p.setId(projectId);

        Task t = new Task();
        t.setProject(p);
        List<Task> tasks = taskRepository.findAll(Example.of(t));

        List<TaskDTO> dtoList = new ArrayList<>();
        for (Task entity : tasks) {
            dtoList.add(mapToDto(entity));
        }

        return dtoList;
    }

}
