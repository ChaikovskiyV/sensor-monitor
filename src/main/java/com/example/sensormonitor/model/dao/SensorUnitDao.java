package com.example.sensormonitor.model.dao;

import com.example.sensormonitor.model.entity.SensorUnit;

import java.util.List;

public interface SensorUnitDao extends BaseDao<SensorUnit> {
    List<SensorUnit> findByUnitName(String unit);
}
