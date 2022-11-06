package com.example.sensormonitor.model.dao;

import com.example.sensormonitor.model.dto.PageDto;
import com.example.sensormonitor.model.entity.Sensor;

public interface SensorDao extends BaseDao<Sensor> {
    PageDto findSensorByPartOfData(String text, PageDto pageDto);

    PageDto findAllPaginated(PageDto pageDto);

    Sensor insert(Sensor sensor);

    Sensor update(Sensor entity);
}