package com.example.sensormonitor.security.jwt;

import javax.validation.constraints.NotEmpty;

public class JwtAuthRequest {
    @NotEmpty(message = "User name can't be empty.")
    private String username;
    @NotEmpty(message = "Password can't be empty.")
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}