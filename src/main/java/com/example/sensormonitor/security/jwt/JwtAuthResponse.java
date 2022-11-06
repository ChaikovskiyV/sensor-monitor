package com.example.sensormonitor.security.jwt;

import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class JwtAuthResponse {
    private String username;
    private String token;

    private Collection<? extends GrantedAuthority> roles;

    public JwtAuthResponse() {
    }

    public JwtAuthResponse(String username, String token, Collection<? extends GrantedAuthority> roles) {
        this.username = username;
        this.token = token;
        this.roles = roles;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Collection<? extends GrantedAuthority> getRoles() {
        return roles;
    }

    public void setRoles(Collection<? extends GrantedAuthority> roles) {
        this.roles = roles;
    }
}