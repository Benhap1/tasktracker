package com.example.service;

import com.example.model.User;
import com.example.model.UserDTO;
import com.example.repository.UserRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Flux<UserDTO> findAll() {
        return userRepository.findAll()
                .map(this::toDTO);
    }

    @Override
    public Mono<UserDTO> findById(String id) {
        return userRepository.findById(id)
                .map(this::toDTO);
    }

    @Override
    public Mono<UserDTO> create(UserDTO userDTO) {
        return userRepository.save(toEntity(userDTO))
                .map(this::toDTO);
    }

    @Override
    public Mono<UserDTO> update(String id, UserDTO userDTO) {
        return userRepository.findById(id)
                .flatMap(existingUser -> {
                    existingUser.setUsername(userDTO.getUsername());
                    existingUser.setEmail(userDTO.getEmail());
                    return userRepository.save(existingUser);
                })
                .map(this::toDTO);
    }

    @Override
    public Mono<Void> deleteById(String id) {
        return userRepository.deleteById(id);
    }

    private UserDTO toDTO(User user) {
        return new UserDTO(user.getId(), user.getUsername(), user.getEmail());
    }

    private User toEntity(UserDTO userDTO) {
        User user = new User();
        user.setId(userDTO.getId());
        user.setUsername(userDTO.getUsername());
        user.setEmail(userDTO.getEmail());
        return user;
    }
}
