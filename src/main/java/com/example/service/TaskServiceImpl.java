package com.example.service;

import com.example.model.Task;
import com.example.model.TaskDTO;
import com.example.model.TaskMapper;
import com.example.repository.TaskRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Set;
import java.util.stream.Collectors;

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
        return getCurrentUserRoles()
                .flatMap(roles -> {
                    if (roles.contains("ROLE_MANAGER")) {
                        return getCurrentUserId()
                                .flatMap(userId -> {
                                    Task task = taskMapper.toEntity(taskDTO);
                                    task.setAuthorId(userId);
                                    return taskRepository.save(task)
                                            .map(taskMapper::toDTO);
                                });
                    } else {
                        return Mono.error(new SecurityException("Access Denied: Insufficient permissions"));
                    }
                });
    }



    @Override
    public Mono<TaskDTO> update(String id, TaskDTO taskDTO) {
        return getCurrentUserRoles()
                .flatMap(roles -> {
                    if (roles.contains("ROLE_MANAGER")) {
                        return taskRepository.findById(id)
                                .flatMap(existingTask -> {
                                    Task taskToUpdate = taskMapper.toEntity(taskDTO);
                                    taskToUpdate.setId(id);
                                    return taskRepository.save(taskToUpdate);
                                })
                                .map(taskMapper::toDTO);
                    } else {
                        return Mono.error(new SecurityException("Access Denied: Insufficient permissions"));
                    }
                });
    }


    @Override
    public Mono<TaskDTO> addObserver(String taskId, String observerId) {
        return getCurrentUserRoles()
                .flatMap(roles -> {
                    if (roles.contains("ROLE_USER") || roles.contains("ROLE_MANAGER")) {
                        return taskRepository.findById(taskId)
                                .flatMap(task -> {
                                    task.getObserverIds().add(observerId);
                                    return taskRepository.save(task);
                                })
                                .map(taskMapper::toDTO);
                    } else {
                        return Mono.error(new SecurityException("Access Denied: Insufficient permissions"));
                    }
                });
    }

    @Override
    public Mono<Void> deleteById(String id) {
        return getCurrentUserRoles()
                .flatMap(roles -> {
                    if (roles.contains("ROLE_MANAGER")) {
                        return taskRepository.deleteById(id);
                    } else {
                        return Mono.error(new SecurityException("Access Denied: Insufficient permissions"));
                    }
                });
    }


    private Mono<Set<String>> getCurrentUserRoles() {
        return ReactiveSecurityContextHolder.getContext()
                .map(securityContext -> {
                    Authentication authentication = securityContext.getAuthentication();
                    if (authentication != null && authentication.getPrincipal() instanceof UserDetails userDetails) {
                        return userDetails.getAuthorities().stream()
                                .map(GrantedAuthority::getAuthority)
                                .collect(Collectors.toSet());
                    } else {
                        throw new SecurityException("Unauthorized");
                    }
                });
    }

    private Mono<String> getCurrentUserId() {
        return ReactiveSecurityContextHolder.getContext()
                .map(securityContext -> {
                    Authentication authentication = securityContext.getAuthentication();
                    if (authentication != null && authentication.getPrincipal() instanceof UserDetails userDetails) {
                        return userDetails.getUsername();
                    } else {
                        throw new SecurityException("Unauthorized");
                    }
                });
    }
}
