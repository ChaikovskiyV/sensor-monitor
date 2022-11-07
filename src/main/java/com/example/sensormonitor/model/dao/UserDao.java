package com.example.sensormonitor.model.dao;

import com.example.sensormonitor.model.entity.User;

import java.util.List;

public interface UserDao {
    List<User> findUserByUsername(String username);
}