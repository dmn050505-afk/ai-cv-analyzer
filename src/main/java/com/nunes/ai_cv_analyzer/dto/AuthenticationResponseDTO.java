package com.nunes.ai_cv_analyzer.dto;

import com.nunes.ai_cv_analyzer.entity.Role;

public class AuthenticationResponseDTO {

    private String token;
    private String type;
    private String username;
    private Role role;

    public AuthenticationResponseDTO() {
    }

    public AuthenticationResponseDTO(String token, String type, String username, Role role) {
        this.token = token;
        this.type = type;
        this.username = username;
        this.role = role;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}