package com.example.sensormonitor.model.dao;

import com.example.sensormonitor.model.entity.BaseEntity;

import java.util.List;

public interface BaseDao<T extends BaseEntity> {
    T findById(long id);

    List<T> findAll();

    void remove(T entity);
}