package com.taskmaster.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;

    @NotBlank
    String name;

    String description;

    @Min(0) @Max(100)
    Integer progress;

    @JsonManagedReference
    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL, orphanRemoval = true)
    List<Task> tasks = new ArrayList<>();

    public void addTask(Task task) {
        getTasks().add(task);
    }

    public void removeTask(Task task) {
        getTasks().remove(task);
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getProgress() {
        return progress;
    }

    public void setProgress(Integer progress) {
        this.progress = progress;
    }


    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Project project = (Project) o;
        return Objects.equals(id, project.id)
                && Objects.equals(name, project.name)
                && Objects.equals(description, project.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description);
    }
}
