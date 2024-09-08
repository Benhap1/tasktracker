package com.example.config;

import com.example.model.RoleType;
import com.example.model.User;
import com.example.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import java.util.Set;

@Component
public class UserInitializer {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserInitializer(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostConstruct
    public void init() {

        User manager = new User();
        manager.setUsername("testmanager");
        manager.setEmail("testmanager1@example.com");
        manager.setPassword(passwordEncoder.encode("password"));
        manager.setRoles(Set.of(RoleType.ROLE_MANAGER));
        userRepository.findByUsername(manager.getUsername())
                .switchIfEmpty(userRepository.save(manager))
                .subscribe();

        User user = new User();
        user.setUsername("testuser");
        user.setEmail("testuser@example.com");
        user.setPassword(passwordEncoder.encode("password"));
        user.setRoles(Set.of(RoleType.ROLE_USER));
        userRepository.findByUsername(user.getUsername())
                .switchIfEmpty(userRepository.save(user))
                .subscribe();
    }
}


