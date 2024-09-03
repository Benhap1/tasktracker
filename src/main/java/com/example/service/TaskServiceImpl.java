package com.example.service;

import com.example.model.Task;
import com.example.model.TaskDTO;
import com.example.model.TaskMapper;
import com.example.repository.TaskRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@AllArgsConstructor
@Service
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;

    @Override
    public Flux<TaskDTO> findAll() {
        return taskRepository.findAll()
                .map(taskMapper::toDTO);
    }

    @Override
    public Mono<TaskDTO> findById(String id) {
        return taskRepository.findById(id)
                .map(taskMapper::toDTO);
    }

    @Override
    public Mono<TaskDTO> create(TaskDTO taskDTO) {
        Task task = taskMapper.toEntity(taskDTO);
        return taskRepository.save(task)
                .map(taskMapper::toDTO);
    }

    @Override
    public Mono<TaskDTO> update(String id, TaskDTO taskDTO) {
        return taskRepository.findById(id)
                .flatMap(existingTask -> {
                    Task taskToUpdate = taskMapper.toEntity(taskDTO);
                    taskToUpdate.setId(id);
                    return taskRepository.save(taskToUpdate);
                })
                .map(taskMapper::toDTO);
    }

    @Override
    public Mono<TaskDTO> addObserver(String taskId, String observerId) {
        return taskRepository.findById(taskId)
                .flatMap(task -> {
                    task.getObserverIds().add(observerId);
                    return taskRepository.save(task);
                })
                .map(taskMapper::toDTO);
    }

    @Override
    public Mono<Void> deleteById(String id) {
        return taskRepository.deleteById(id);
    }
}