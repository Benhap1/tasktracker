package com.example.model;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", uses = UserMapper.class)
public interface TaskMapper {
    TaskMapper INSTANCE = Mappers.getMapper(TaskMapper.class);


    @Mappings({
            @Mapping(source = "author", target = "author"),
            @Mapping(source = "assignee", target = "assignee"),
            @Mapping(source = "observers", target = "observers")
    })
    TaskDTO toDTO(Task task);

    @Mappings({
            @Mapping(source = "author", target = "author"),
            @Mapping(source = "assignee", target = "assignee"),
            @Mapping(source = "observers", target = "observers")
    })
    Task toEntity(TaskDTO taskDTO);


    default Set<TaskDTO> toDTO(Set<Task> tasks) {
        return tasks.stream()
                .map(this::toDTO)
                .collect(Collectors.toSet());
    }

    default Set<Task> toEntity(Set<TaskDTO> taskDTOs) {
        return taskDTOs.stream()
                .map(this::toEntity)
                .collect(Collectors.toSet());
    }
}