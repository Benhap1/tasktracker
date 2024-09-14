package com.example.model;

import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-09-14T13:37:54+0300",
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

        TaskDTO taskDTO = new TaskDTO();

        taskDTO.setAuthor( userMapper.toDTO( task.getAuthor() ) );
        taskDTO.setAssignee( userMapper.toDTO( task.getAssignee() ) );
        taskDTO.setObservers( userMapper.toDTO( task.getObservers() ) );
        taskDTO.setId( task.getId() );
        taskDTO.setName( task.getName() );
        taskDTO.setDescription( task.getDescription() );
        taskDTO.setCreatedAt( task.getCreatedAt() );
        taskDTO.setUpdatedAt( task.getUpdatedAt() );
        taskDTO.setStatus( task.getStatus() );

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
