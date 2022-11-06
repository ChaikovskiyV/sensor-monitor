package com.example.sensormonitor.model.service;

import com.example.sensormonitor.model.entity.BaseEntity;

import java.util.List;

public interface BaseService<T extends BaseEntity> {
    List<T> findAll();
}
