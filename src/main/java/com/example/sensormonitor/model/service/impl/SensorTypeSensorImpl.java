package com.example.sensormonitor.model.service.impl;

import com.example.sensormonitor.model.dao.SensorTypeDao;
import com.example.sensormonitor.model.entity.SensorType;
import com.example.sensormonitor.model.service.SensorTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SensorTypeSensorImpl implements SensorTypeService {
    private final SensorTypeDao sensorTypeDao;

    @Autowired
    public SensorTypeSensorImpl(SensorTypeDao sensorTypeDao) {
        this.sensorTypeDao = sensorTypeDao;
    }

    @Override
    public List<SensorType> findAll() {
        return sensorTypeDao.findAll();
    }
}