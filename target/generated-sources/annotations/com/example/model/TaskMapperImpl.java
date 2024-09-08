package com.example.model;

import java.time.Instant;
import java.util.Set;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-09-08T20:10:36+0300",
    comments = "version: 1.6.0, compiler: javac, environment: Java 17.0.12 (Oracle Corporation)"
)
@Component
public class TaskMapperImpl implements TaskMapper {

    @Autowired
    private UserMapper userMapper;

    @Override
    public TaskDTO toDTO(Task task) {
        if ( task == null ) {
            return null;
        }

        UserDTO author = null;
        UserDTO assignee = null;
        Set<UserDTO> observers = null;
        String id = null;
        String name = null;
        String description = null;
        Instant createdAt = null;
        Instant updatedAt = null;
        TaskStatus status = null;

        author = userMapper.toDTO( task.getAuthor() );
        assignee = userMapper.toDTO( task.getAssignee() );
        observers = userMapper.toDTO( task.getObservers() );
        id = task.getId();
        name = task.getName();
        description = task.getDescription();
        createdAt = task.getCreatedAt();
        updatedAt = task.getUpdatedAt();
        status = task.getStatus();

        TaskDTO taskDTO = new TaskDTO( id, name, description, createdAt, updatedAt, status, author, assignee, observers );

        return taskDTO;
    }

    @Override
    public Task toEntity(TaskDTO taskDTO) {
        if ( taskDTO == null ) {
            return null;
        }

        Task task = new Task();

        task.setAuthor( userMapper.toEntity( taskDTO.getAuthor() ) );
        task.setAssignee( userMapper.toEntity( taskDTO.getAssignee() ) );
        task.setObservers( userMapper.toEntity( taskDTO.getObservers() ) );
        task.setId( taskDTO.getId() );
        task.setName( taskDTO.getName() );
        task.setDescription( taskDTO.getDescription() );
        task.setCreatedAt( taskDTO.getCreatedAt() );
        task.setUpdatedAt( taskDTO.getUpdatedAt() );
        task.setStatus( taskDTO.getStatus() );

        return task;
    }
}
