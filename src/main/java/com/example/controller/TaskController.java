package com.example.controller;

import com.example.model.TaskDTO;
import com.example.service.TaskService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@AllArgsConstructor
@RequestMapping("/tasks")
@Tag(name = "Task Management", description = "Operations for managing tasks")
public class TaskController {
    private final TaskService taskService;

    @PreAuthorize("hasAuthority('ROLE_USER') or hasAuthority('ROLE_MANAGER')")
    @Operation(summary = "Get all tasks", description = "Returns a list of all tasks")
    @ApiResponse(responseCode = "200", description = "Successful operation", content = @Content(array = @ArraySchema(schema = @Schema(implementation = TaskDTO.class))))
    @GetMapping
    public Flux<TaskDTO> getAllTasks() {
        return taskService.findAll();
    }

    @PreAuthorize("hasAuthority('ROLE_USER') or hasAuthority('ROLE_MANAGER')")
    @Operation(summary = "Get a task by ID", description = "Find a task by its unique ID")
    @ApiResponse(responseCode = "200", description = "Task found", content = @Content(schema = @Schema(implementation = TaskDTO.class)))
    @ApiResponse(responseCode = "404", description = "Task not found")
    @GetMapping("/{id}")
    public Mono<TaskDTO> getTaskById(@PathVariable String id) {
        return taskService.findById(id);
    }

    @PreAuthorize("hasAuthority('ROLE_MANAGER')")
    @Operation(summary = "Create a new task", description = "Create a new task with the provided details")
    @ApiResponse(responseCode = "201", description = "Task created successfully", content = @Content(schema = @Schema(implementation = TaskDTO.class)))
    @ApiResponse(responseCode = "400", description = "Invalid input")
    @PostMapping
    public Mono<TaskDTO> createTask(@RequestBody TaskDTO taskDTO) {
        return taskService.create(taskDTO);
    }

    @PreAuthorize("hasAuthority('ROLE_MANAGER')")
    @Operation(summary = "Update an existing task", description = "Update task details by their ID")
    @ApiResponse(responseCode = "200", description = "Task updated successfully", content = @Content(schema = @Schema(implementation = TaskDTO.class)))
    @ApiResponse(responseCode = "404", description = "Task not found")
    @PutMapping("/{id}")
    public Mono<TaskDTO> updateTask(@PathVariable String id, @RequestBody TaskDTO taskDTO) {
        return taskService.update(id, taskDTO);
    }

    @PreAuthorize("hasAuthority('ROLE_USER') or hasAuthority('ROLE_MANAGER')")
    @Operation(summary = "Add observer to task", description = "Add an observer to a task")
    @ApiResponse(responseCode = "200", description = "Observer added successfully", content = @Content(schema = @Schema(implementation = TaskDTO.class)))
    @ApiResponse(responseCode = "404", description = "Task or observer not found")
    @PostMapping("/{id}/addObserver/{observerId}")
    public Mono<TaskDTO> addObserver(@PathVariable String id, @PathVariable String observerId) {
        return taskService.addObserver(id, observerId);
    }

    @PreAuthorize("hasAuthority('ROLE_MANAGER')")
    @Operation(summary = "Delete a task", description = "Delete a task by its ID")
    @ApiResponse(responseCode = "200", description = "Task deleted successfully")
    @ApiResponse(responseCode = "404", description = "Task not found")
    @DeleteMapping("/{id}")
    public Mono<Void> deleteTask(@PathVariable String id) {
        return taskService.deleteById(id);
    }
}
