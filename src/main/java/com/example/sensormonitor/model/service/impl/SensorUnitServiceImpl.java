package com.example.sensormonitor.model.service.impl;

import com.example.sensormonitor.model.dao.SensorUnitDao;
import com.example.sensormonitor.model.entity.SensorUnit;
import com.example.sensormonitor.model.service.SensorUnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SensorUnitServiceImpl implements SensorUnitService {
    private final SensorUnitDao sensorUnitDao;

    @Autowired
    public SensorUnitServiceImpl(SensorUnitDao sensorUnitDao) {
        this.sensorUnitDao = sensorUnitDao;
    }

    @Override
    public List<SensorUnit> findAll() {
        return sensorUnitDao.findAll();
    }
}