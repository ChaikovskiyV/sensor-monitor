package com.example.sensormonitor.model.entity;

import com.example.sensormonitor.model.dto.SensorDto;

public class SensorFactory {

    private SensorFactory() {
    }

    public static Sensor createSensorFromDto(SensorDto sensorDto) {
        return Sensor.getBuilder()
                .setName(sensorDto.getName())
                .setModel(sensorDto.getModel())
                .setType(sensorDto.getSensorType())
                .setUnit(sensorDto.getSensorUnit())
                .setRange(sensorDto.getSensorRange())
                .setDescription(sensorDto.getDescription())
                .setLocation(sensorDto.getLocation())
                .build();
    }
}