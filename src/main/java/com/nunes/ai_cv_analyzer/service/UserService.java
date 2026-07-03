package com.nunes.ai_cv_analyzer.service;

import com.nunes.ai_cv_analyzer.dto.RegisterRequestDTO;
import com.nunes.ai_cv_analyzer.entity.User;
import com.nunes.ai_cv_analyzer.repository.UserRepository;
import jakarta.persistence.EntityExistsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder) {

        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User register(RegisterRequestDTO request) {

        if (userRepository.existsByUsername(request.getUsername())) {
            throw new EntityExistsException("Username already exists.");
        }

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new EntityExistsException("Email already exists.");
        }

        User user = new User();

        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());

        user.setPassword(
                passwordEncoder.encode(request.getPassword())
        );

        user.setRole(request.getRole());

        return userRepository.save(user);
    }

}