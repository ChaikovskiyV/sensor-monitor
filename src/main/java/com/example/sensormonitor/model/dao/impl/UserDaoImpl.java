package com.example.sensormonitor.model.dao.impl;

import com.example.sensormonitor.model.dao.UserDao;
import com.example.sensormonitor.model.entity.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

import static com.example.sensormonitor.model.dao.DatabaseQueries.FIND_USER_BY_USERNAME;

@Repository
public class UserDaoImpl implements UserDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<User> findUserByUsername(String username) {
        return entityManager.createQuery(FIND_USER_BY_USERNAME, User.class)
                .setParameter(1, username)
                .getResultList();
    }
}