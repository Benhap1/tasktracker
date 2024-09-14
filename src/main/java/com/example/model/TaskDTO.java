package com.example.model;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

public class TaskDTO {
    private String id;
    private String name;
    private String description;
    private Instant createdAt;
    private Instant updatedAt;
    private TaskStatus status;
    private UserDTO author;
    private UserDTO assignee;
    private Set<UserDTO> observers = new HashSet<>();

    public TaskDTO(String id, String name, String description, Instant createdAt, Instant updatedAt, TaskStatus status, UserDTO author, UserDTO assignee, Set<UserDTO> observers) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.status = status;
        this.author = author;
        this.assignee = assignee;
        this.observers = observers;
    }

    public TaskDTO() {

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

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    public UserDTO getAuthor() {
        return author;
    }

    public void setAuthor(UserDTO author) {
        this.author = author;
    }

    public UserDTO getAssignee() {
        return assignee;
    }

    public void setAssignee(UserDTO assignee) {
        this.assignee = assignee;
    }

    public Set<UserDTO> getObservers() {
        return observers;
    }

    public void setObservers(Set<UserDTO> observers) {
        this.observers = observers;
    }
}
