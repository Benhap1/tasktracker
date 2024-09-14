package com.example.model;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserDTO toDTO(User user);
    User toEntity(UserDTO userDTO);


    default Set<UserDTO> toDTO(Set<User> users) {
        return users == null ? Collections.emptySet() : users.stream()
                .map(this::toDTO)
                .collect(Collectors.toSet());
    }


    default Set<User> toEntity(Set<UserDTO> userDTOs) {
        return userDTOs == null ? Collections.emptySet() : userDTOs.stream()
                .map(this::toEntity)
                .collect(Collectors.toSet());
    }
}