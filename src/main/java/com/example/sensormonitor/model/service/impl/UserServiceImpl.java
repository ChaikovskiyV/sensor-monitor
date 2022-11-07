package com.example.sensormonitor.model.service.impl;

import com.example.sensormonitor.exception.ApplicationNotFoundException;
import com.example.sensormonitor.exception.ApplicationNotValidDataException;
import com.example.sensormonitor.model.dao.UserDao;
import com.example.sensormonitor.model.entity.User;
import com.example.sensormonitor.model.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.sensormonitor.exception.ErrorMessages.EMPTY_EMAIL;
import static com.example.sensormonitor.exception.ErrorMessages.NOT_FOUND_USER_BY_NAME;

@Service
public class UserServiceImpl implements UserService {
    public static final Logger logger = LogManager.getLogger();
    private final UserDao userDao;

    @Autowired
    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public User findUserByUsername(String username) {
        List<User> users;
        if (username == null || username.isBlank()) {
            throw new ApplicationNotValidDataException(EMPTY_EMAIL, username);
        }

        users = userDao.findUserByUsername(username);

        if (users.isEmpty()) {
            logger.error(() -> String.format(NOT_FOUND_USER_BY_NAME, username));
            throw new ApplicationNotFoundException(NOT_FOUND_USER_BY_NAME, username);
        }

        return users.get(0);
    }
}