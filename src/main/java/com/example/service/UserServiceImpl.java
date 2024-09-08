package com.example.service;

import com.example.model.User;
import com.example.model.UserDTO;
import com.example.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


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
        User user = toEntity(userDTO);
        user.setPassword(passwordEncoder.encode(userDTO.getPassword())); // Шифруем пароль
        return userRepository.save(user)
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
        return new UserDTO(user.getId(), user.getUsername(), user.getEmail(),user.getPassword(), user.getRoles());
    }

    private User toEntity(UserDTO userDTO) {
        User user = new User();
        user.setId(userDTO.getId());
        user.setUsername(userDTO.getUsername());
        user.setEmail(userDTO.getEmail());
        user.setRoles(userDTO.getRoles());
        return user;
    }
}
