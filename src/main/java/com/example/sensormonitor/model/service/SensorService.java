package com.example.sensormonitor.model.service;

import com.example.sensormonitor.model.dto.PageDto;
import com.example.sensormonitor.model.dto.SensorDto;
import com.example.sensormonitor.model.entity.Sensor;

import java.util.Map;

public interface SensorService {
    Sensor findById(long id);

    PageDto findSensors(Map<String, Object> searchParams);

    Sensor createSensor(SensorDto sensorDto);

    Sensor updateSensor(SensorDto sensorDto, long id);

    void delete(long id);
}