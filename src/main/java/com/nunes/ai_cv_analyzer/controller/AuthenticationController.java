package com.nunes.ai_cv_analyzer.controller;

import com.nunes.ai_cv_analyzer.dto.AuthenticationResponseDTO;
import com.nunes.ai_cv_analyzer.dto.LoginRequestDTO;
import com.nunes.ai_cv_analyzer.dto.RegisterRequestDTO;
import com.nunes.ai_cv_analyzer.dto.UserResponseDTO;
import com.nunes.ai_cv_analyzer.service.AuthenticationService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/register")
    public ResponseEntity<UserResponseDTO> register(
            @Valid @RequestBody RegisterRequestDTO request) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(authenticationService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponseDTO> login(
            @Valid @RequestBody LoginRequestDTO request) {

        return ResponseEntity.ok(
                authenticationService.login(request)
        );
    }

}