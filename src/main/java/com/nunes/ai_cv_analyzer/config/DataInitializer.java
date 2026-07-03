package com.nunes.ai_cv_analyzer.config;

import com.nunes.ai_cv_analyzer.entity.Role;
import com.nunes.ai_cv_analyzer.entity.User;
import com.nunes.ai_cv_analyzer.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public DataInitializer(UserRepository userRepository,
                           PasswordEncoder passwordEncoder) {

        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {

        if (userRepository.existsByRole(Role.ADMIN)) {
            return;
        }

        User admin = new User();

        admin.setUsername("admin");
        admin.setEmail("admin@aicvanalyzer.com");
        admin.setPassword(passwordEncoder.encode("admin123"));
        admin.setRole(Role.ADMIN);

        userRepository.save(admin);

        System.out.println("----------------------------------------");
        System.out.println("DEFAULT ADMIN CREATED");
        System.out.println("Username: admin");
        System.out.println("Password: admin123");
        System.out.println("----------------------------------------");
    }
}