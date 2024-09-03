package com.example.model;

import java.time.Instant;
import java.util.LinkedHashSet;
import java.util.Set;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-09-03T20:13:35+0300",
    comments = "version: 1.6.0, compiler: javac, environment: Java 17.0.12 (Oracle Corporation)"
)
@Component
public class TaskMapperImpl implements TaskMapper {

    @Override
    public TaskDTO toDTO(Task task) {
        if ( task == null ) {
            return null;
        }

        String id = null;
        String name = null;
        String description = null;
        Instant createdAt = null;
        Instant updatedAt = null;
        TaskStatus status = null;
        UserDTO author = null;
        UserDTO assignee = null;
        Set<UserDTO> observers = null;

        id = task.getId();
        name = task.getName();
        description = task.getDescription();
        createdAt = task.getCreatedAt();
        updatedAt = task.getUpdatedAt();
        status = task.getStatus();
        author = userToUserDTO( task.getAuthor() );
        assignee = userToUserDTO( task.getAssignee() );
        observers = userSetToUserDTOSet( task.getObservers() );

        TaskDTO taskDTO = new TaskDTO( id, name, description, createdAt, updatedAt, status, author, assignee, observers );

        return taskDTO;
    }

    @Override
    public Task toEntity(TaskDTO taskDTO) {
        if ( taskDTO == null ) {
            return null;
        }

        Task task = new Task();

        task.setId( taskDTO.getId() );
        task.setName( taskDTO.getName() );
        task.setDescription( taskDTO.getDescription() );
        task.setCreatedAt( taskDTO.getCreatedAt() );
        task.setUpdatedAt( taskDTO.getUpdatedAt() );
        task.setStatus( taskDTO.getStatus() );
        task.setAuthor( userDTOToUser( taskDTO.getAuthor() ) );
        task.setAssignee( userDTOToUser( taskDTO.getAssignee() ) );
        task.setObservers( userDTOSetToUserSet( taskDTO.getObservers() ) );

        return task;
    }

    protected UserDTO userToUserDTO(User user) {
        if ( user == null ) {
            return null;
        }

        UserDTO userDTO = new UserDTO();

        userDTO.setId( user.getId() );
        userDTO.setUsername( user.getUsername() );
        userDTO.setEmail( user.getEmail() );

        return userDTO;
    }

    protected Set<UserDTO> userSetToUserDTOSet(Set<User> set) {
        if ( set == null ) {
            return null;
        }

        Set<UserDTO> set1 = new LinkedHashSet<UserDTO>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( User user : set ) {
            set1.add( userToUserDTO( user ) );
        }

        return set1;
    }

    protected User userDTOToUser(UserDTO userDTO) {
        if ( userDTO == null ) {
            return null;
        }

        User user = new User();

        user.setId( userDTO.getId() );
        user.setUsername( userDTO.getUsername() );
        user.setEmail( userDTO.getEmail() );

        return user;
    }

    protected Set<User> userDTOSetToUserSet(Set<UserDTO> set) {
        if ( set == null ) {
            return null;
        }

        Set<User> set1 = new LinkedHashSet<User>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( UserDTO userDTO : set ) {
            set1.add( userDTOToUser( userDTO ) );
        }

        return set1;
    }
}
