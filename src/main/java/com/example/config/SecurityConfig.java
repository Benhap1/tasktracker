package com.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UserDetailsRepositoryReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;
import reactor.core.publisher.Mono;


@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

    private final ReactiveUserDetailsService userDetailsService;

    public SecurityConfig(ReactiveUserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
        return http
                .csrf(ServerHttpSecurity.CsrfSpec::disable)
                .authorizeExchange(exchange -> exchange
                        .pathMatchers("/swagger-ui.html", "/swagger-ui/**", "/v3/api-docs/**").permitAll()
                        .pathMatchers(HttpMethod.POST, "/users/**").hasAuthority("ROLE_MANAGER")
                        .pathMatchers("/users/**").hasAnyAuthority("ROLE_USER", "ROLE_MANAGER")
                        .pathMatchers(HttpMethod.GET, "/tasks/**").hasAnyAuthority("ROLE_USER", "ROLE_MANAGER")
                        .pathMatchers(HttpMethod.POST, "/tasks/**").hasAuthority("ROLE_MANAGER")
                        .pathMatchers(HttpMethod.PUT, "/tasks/**").hasAuthority("ROLE_MANAGER")
                        .pathMatchers(HttpMethod.DELETE, "/tasks/**").hasAuthority("ROLE_MANAGER")
                        .anyExchange().authenticated()
                )
                .httpBasic(Customizer.withDefaults())
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    public ReactiveAuthenticationManager reactiveAuthenticationManager() {
        return new UserDetailsRepositoryReactiveAuthenticationManager(userDetailsService) {
            @Override
            public Mono<Authentication> authenticate(Authentication authentication) {
                return userDetailsService.findByUsername(authentication.getName())
                        .flatMap(user -> {
                            if (passwordEncoder().matches(authentication.getCredentials().toString(), user.getPassword())) {
                                return Mono.just(new UsernamePasswordAuthenticationToken(user, user.getPassword(), user.getAuthorities()));
                            } else {
                                return Mono.empty();
                            }
                        });
            }
        };

    }
}


