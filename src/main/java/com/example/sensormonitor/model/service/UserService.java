package com.example.sensormonitor.model.service;

import com.example.sensormonitor.model.entity.User;

public interface UserService {
    User findUserByEmail(String email);
}