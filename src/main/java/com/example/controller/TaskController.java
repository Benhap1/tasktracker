package com.example.controller;

import com.example.model.TaskDTO;
import com.example.service.TaskService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@AllArgsConstructor
@RequestMapping("/tasks")
public class TaskController {
    private final TaskService taskService;

    @GetMapping
    public Flux<TaskDTO> getAllTasks() {
        return taskService.findAll();
    }

    @GetMapping("/{id}")
    public Mono<TaskDTO> getTaskById(@PathVariable String id) {
        return taskService.findById(id);
    }



    @PostMapping
    public Mono<TaskDTO> createTask(@RequestBody TaskDTO taskDTO) {
        return taskService.create(taskDTO);
    }

    @PutMapping("/{id}")
    public Mono<TaskDTO> updateTask(@PathVariable String id, @RequestBody TaskDTO taskDTO) {
        return taskService.update(id, taskDTO);
    }

    @PostMapping("/{id}/addObserver/{observerId}")
    public Mono<TaskDTO> addObserver(@PathVariable String id, @PathVariable String observerId) {
        return taskService.addObserver(id, observerId);
    }

    @DeleteMapping("/{id}")
    public Mono<Void> deleteTask(@PathVariable String id) {
        return taskService.deleteById(id);
    }
}


