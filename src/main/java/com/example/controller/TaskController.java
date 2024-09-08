package com.example.controller;

import com.example.model.TaskDTO;
import com.example.service.TaskService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@AllArgsConstructor
@RequestMapping("/tasks")
public class TaskController {
    private final TaskService taskService;
    @PreAuthorize("hasAuthority('ROLE_USER') or hasAuthority('ROLE_MANAGER')")
    @GetMapping
    public Flux<TaskDTO> getAllTasks() {
        return taskService.findAll();
    }

    @PreAuthorize("hasAuthority('ROLE_USER') or hasAuthority('ROLE_MANAGER')")
    @GetMapping("/{id}")
    public Mono<TaskDTO> getTaskById(@PathVariable String id) {
        return taskService.findById(id);
    }


    @PreAuthorize("hasAuthority('ROLE_MANAGER')")
    @PostMapping
    public Mono<TaskDTO> createTask(@RequestBody TaskDTO taskDTO) {
        return taskService.create(taskDTO);
    }

    @PreAuthorize("hasAuthority('ROLE_MANAGER')")
    @PutMapping("/{id}")
    public Mono<TaskDTO> updateTask(@PathVariable String id, @RequestBody TaskDTO taskDTO) {
        return taskService.update(id, taskDTO);
    }

    @PreAuthorize("hasAuthority('ROLE_USER') or hasAuthority('ROLE_MANAGER')")
    @PostMapping("/{id}/addObserver/{observerId}")
    public Mono<TaskDTO> addObserver(@PathVariable String id, @PathVariable String observerId) {
        return taskService.addObserver(id, observerId);
    }
    @PreAuthorize("hasAuthority('ROLE_MANAGER')")
    @DeleteMapping("/{id}")
    public Mono<Void> deleteTask(@PathVariable String id) {
        return taskService.deleteById(id);
    }
}


