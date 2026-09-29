package com.taskmaster.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.taskmaster.entity.Project;
import com.taskmaster.entity.Task;
import com.taskmaster.repository.TaskRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    @Autowired
    TaskRepository taskRepository;

    private static final ObjectMapper OM = new ObjectMapper();
    static {
        OM.configure(SerializationFeature.INDENT_OUTPUT, true);
    }

    @Transactional
    public Task save(Task task) {
        return taskRepository.save(task);
    }

    public boolean existsById(Long taskId) {
        return taskRepository.existsById(taskId);
    }

    public Optional<Task> find(Long id) {
        return taskRepository.findById(id);
    }

    public void remove(Long id) {
        taskRepository.deleteById(id);
    }

    @Transactional
    public List<Task> saveAll(Project project, List<Task> tasks) {
        List<Task> saved = new ArrayList<>();
        for (Task t : tasks) {
            t.setProject(project);
            Task s = taskRepository.save(t);
            saved.add(s);
        }

        return saved;
    }

    public List<Task> findTasksByProjectId(String projectId) {
        Project p = new Project();
        p.setId(projectId);
        Task t = new Task();
        t.setProject(p);

        return taskRepository.findAll(Example.of(t));
    }

}
