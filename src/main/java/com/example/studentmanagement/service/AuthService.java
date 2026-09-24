package com.example.studentmanagement.service;

import com.example.studentmanagement.model.User;
import com.example.studentmanagement.repository.UserRepository;
import com.example.studentmanagement.security.JwtService;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final JwtService jwtService;

    public AuthService(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            JwtService jwtService) {

        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    // ========================================
    // REGISTER
    // ========================================

    public String register(User user) {

        String email =
                user.getEmail()
                        .trim()
                        .toLowerCase();

        if (userRepository.findByEmail(email).isPresent()) {

            throw new RuntimeException(
                    "Email already registered"
            );
        }

        user.setEmail(email);

        user.setPassword(
                passwordEncoder.encode(
                        user.getPassword()
                )
        );

        // Every newly registered user is USER
        user.setRole("USER");

        userRepository.save(user);

        return "User registered successfully";
    }

    // ========================================
    // LOGIN
    // ========================================

    public String login(
            String email,
            String password) {

        User user =
                userRepository
                        .findByEmail(
                                email
                                        .trim()
                                        .toLowerCase()
                        )
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Invalid email or password"
                                )
                        );

        if (!passwordEncoder.matches(
                password,
                user.getPassword())) {

            throw new RuntimeException(
                    "Invalid email or password"
            );
        }

        return jwtService.generateToken(
                user.getEmail()
        );
    }

    // ========================================
    // RESET PASSWORD
    // ========================================

    public void resetPassword(
            String email,
            String newPassword) {

        User user =
                userRepository
                        .findByEmail(
                                email
                                        .trim()
                                        .toLowerCase()
                        )
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "User not found"
                                )
                        );

        // Encrypt the new password using BCrypt
        user.setPassword(
                passwordEncoder.encode(newPassword)
        );

        userRepository.save(user);
    }
}