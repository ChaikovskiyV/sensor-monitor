package com.example.sensormonitor.model.dao;

import com.example.sensormonitor.model.entity.SensorType;

import java.util.List;

public interface SensorTypeDao extends BaseDao<SensorType> {
    List<SensorType> findByTypeName(String type);
}
