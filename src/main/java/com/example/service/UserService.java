package com.example.service;

import com.example.model.UserDTO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UserService {
    Flux<UserDTO> findAll();
    Mono<UserDTO> findById(String id);
    Mono<UserDTO> create(UserDTO userDTO);
    Mono<UserDTO> update(String id, UserDTO userDTO);
    Mono<Void> deleteById(String id);
}
