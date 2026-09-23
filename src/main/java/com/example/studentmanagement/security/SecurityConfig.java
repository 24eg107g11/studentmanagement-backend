package com.example.studentmanagement.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import org.springframework.security.config.http.SessionCreationPolicy;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    public SecurityConfig(
            JwtAuthenticationFilter jwtAuthenticationFilter) {

        this.jwtAuthenticationFilter =
                jwtAuthenticationFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity http)
            throws Exception {

        http
                .csrf(csrf ->
                        csrf.disable()
                )

                .cors(cors -> {})

                .authorizeHttpRequests(auth ->
                        auth

                                // Public APIs
                                .requestMatchers(
                                        "/api/auth/**"
                                )
                                .permitAll()

                                // Admin only
                                .requestMatchers(
                                        org.springframework.http.HttpMethod.POST,
                                        "/api/students"
                                )
                                .hasRole("ADMIN")

                                .requestMatchers(
                                        org.springframework.http.HttpMethod.PUT,
                                        "/api/students/**"
                                )
                                .hasRole("ADMIN")

                                .requestMatchers(
                                        org.springframework.http.HttpMethod.DELETE,
                                        "/api/students/**"
                                )
                                .hasRole("ADMIN")

                                // Logged-in users
                                .requestMatchers(
                                        org.springframework.http.HttpMethod.GET,
                                        "/api/students/**"
                                )
                                .hasAnyRole(
                                        "USER",
                                        "ADMIN"
                                )

                                .anyRequest()
                                .authenticated()
                )

                .sessionManagement(session ->
                        session.sessionCreationPolicy(
                                SessionCreationPolicy.STATELESS
                        )
                )

                .addFilterBefore(
                        jwtAuthenticationFilter,
                        UsernamePasswordAuthenticationFilter.class
                );

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {

        return new BCryptPasswordEncoder();
    }
}