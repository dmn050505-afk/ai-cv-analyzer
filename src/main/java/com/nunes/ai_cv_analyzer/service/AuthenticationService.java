package com.nunes.ai_cv_analyzer.service;

import com.nunes.ai_cv_analyzer.dto.AuthenticationResponseDTO;
import com.nunes.ai_cv_analyzer.dto.LoginRequestDTO;
import com.nunes.ai_cv_analyzer.dto.RegisterRequestDTO;
import com.nunes.ai_cv_analyzer.dto.UserResponseDTO;
import com.nunes.ai_cv_analyzer.entity.User;
import com.nunes.ai_cv_analyzer.mapper.UserMapper;
import com.nunes.ai_cv_analyzer.security.CustomUserDetails;
import com.nunes.ai_cv_analyzer.security.JwtService;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class AuthenticationService {

    private final UserService userService;
    private final UserMapper userMapper;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public AuthenticationService(UserService userService,
                                 UserMapper userMapper,
                                 AuthenticationManager authenticationManager,
                                 JwtService jwtService) {

        this.userService = userService;
        this.userMapper = userMapper;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    public UserResponseDTO register(RegisterRequestDTO request) {

        User user = userService.register(request);

        return userMapper.toResponseDTO(user);
    }

    public AuthenticationResponseDTO login(LoginRequestDTO request) {

        try {

            var authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getUsername(),
                            request.getPassword()
                    )
            );

            CustomUserDetails userDetails =
                    (CustomUserDetails) authentication.getPrincipal();

            String token = jwtService.generateToken(userDetails);

            return new AuthenticationResponseDTO(
                    token,
                    "Bearer",
                    userDetails.getUsername(),
                    userDetails.getUser().getRole()
            );

        } catch (Exception ex) {

            throw new ResponseStatusException(
                    HttpStatus.UNAUTHORIZED,
                    "Invalid username or password."
            );
        }
    }

}