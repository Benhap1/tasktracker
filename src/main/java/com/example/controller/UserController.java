package com.example.controller;

import com.example.model.UserDTO;
import com.example.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@AllArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @PreAuthorize("hasAuthority('ROLE_USER') or hasAuthority('ROLE_MANAGER')")
    @GetMapping
    public Flux<UserDTO> getAllUsers() {
        return userService.findAll();
    }

    @PreAuthorize("hasAuthority('ROLE_USER') or hasAuthority('ROLE_MANAGER')")
    @GetMapping("/{id}")
    public Mono<UserDTO> getUserById(@PathVariable String id) {
        return userService.findById(id);
    }

    @PreAuthorize("hasAuthority('ROLE_MANAGER')")
    @PostMapping
    public Mono<UserDTO> createUser(@RequestBody UserDTO userDTO) {
        return userService.create(userDTO);
    }

    @PreAuthorize("hasAuthority('ROLE_MANAGER')")
    @PutMapping("/{id}")
    public Mono<UserDTO> updateUser(@PathVariable String id, @RequestBody UserDTO userDTO) {
        return userService.update(id, userDTO);
    }
    @PreAuthorize("hasAuthority('ROLE_MANAGER')")
    @DeleteMapping("/{id}")
    public Mono<Void> deleteUser(@PathVariable String id) {
        return userService.deleteById(id);
    }
}
