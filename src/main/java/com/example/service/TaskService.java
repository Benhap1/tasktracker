package com.example.service;

import com.example.model.TaskDTO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface TaskService {
    Flux<TaskDTO> findAll();
    Mono<TaskDTO> findById(String id);
    Mono<TaskDTO> create(TaskDTO taskDTO);
    Mono<TaskDTO> update(String id, TaskDTO taskDTO);
    Mono<TaskDTO> addObserver(String taskId, String observerId);
    Mono<Void> deleteById(String id);
}
