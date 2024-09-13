package com.example.controller;

import com.example.model.UserDTO;
import com.example.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@AllArgsConstructor
@RequestMapping("/users")
@Tag(name = "User Management", description = "Operations for managing users")
public class UserController {
    private final UserService userService;

    @PreAuthorize("hasAuthority('ROLE_USER') or hasAuthority('ROLE_MANAGER')")
    @Operation(summary = "Get all users", description = "Returns a list of all registered users")
    @ApiResponse(responseCode = "200", description = "Successful operation", content = @Content(array = @ArraySchema(schema = @Schema(implementation = UserDTO.class))))
    @GetMapping
    public Flux<UserDTO> getAllUsers() {
        return userService.findAll();
    }

    @PreAuthorize("hasAuthority('ROLE_USER') or hasAuthority('ROLE_MANAGER')")
    @Operation(summary = "Get a user by ID", description = "Find a user by their unique ID")
    @ApiResponse(responseCode = "200", description = "User found", content = @Content(schema = @Schema(implementation = UserDTO.class)))
    @ApiResponse(responseCode = "404", description = "User not found")
    @GetMapping("/{id}")
    public Mono<UserDTO> getUserById(@PathVariable String id) {
        return userService.findById(id);
    }

    @PreAuthorize("hasAuthority('ROLE_MANAGER')")
    @Operation(summary = "Create a new user", description = "Create a new user with the provided details")
    @ApiResponse(responseCode = "201", description = "User created successfully", content = @Content(schema = @Schema(implementation = UserDTO.class)))
    @ApiResponse(responseCode = "400", description = "Invalid input")
    @PostMapping
    public Mono<UserDTO> createUser(@RequestBody UserDTO userDTO) {
        return userService.create(userDTO);
    }

    @PreAuthorize("hasAuthority('ROLE_MANAGER')")
    @Operation(summary = "Update an existing user", description = "Update user details by their ID")
    @ApiResponse(responseCode = "200", description = "User updated successfully", content = @Content(schema = @Schema(implementation = UserDTO.class)))
    @ApiResponse(responseCode = "404", description = "User not found")
    @PutMapping("/{id}")
    public Mono<UserDTO> updateUser(@PathVariable String id, @RequestBody UserDTO userDTO) {
        return userService.update(id, userDTO);
    }

    @PreAuthorize("hasAuthority('ROLE_MANAGER')")
    @Operation(summary = "Delete a user", description = "Delete a user by their ID")
    @ApiResponse(responseCode = "200", description = "User deleted successfully")
    @ApiResponse(responseCode = "404", description = "User not found")
    @DeleteMapping("/{id}")
    public Mono<Void> deleteUser(@PathVariable String id) {
        return userService.deleteById(id);
    }
}
