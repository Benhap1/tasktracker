package com.example.model;

import lombok.Data;

import java.util.Set;

@Data
public class UserDTO {
    private String id;
    private String username;
    private String email;

    private String password;

    private Set<RoleType> roles;

    public UserDTO() {}


    public UserDTO(String id, String username, String email, String password, Set<RoleType> roles) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.roles = roles;
    }
}