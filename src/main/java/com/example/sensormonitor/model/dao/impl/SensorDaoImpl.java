package com.example.sensormonitor.model.dao.impl;

import com.example.sensormonitor.model.dao.SensorDao;
import com.example.sensormonitor.model.dto.PageDto;
import com.example.sensormonitor.model.entity.Sensor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

import static com.example.sensormonitor.model.dao.DatabaseQueries.*;
import static com.example.sensormonitor.model.util.pagination.PaginationProvider.getPagesNumber;
import static com.example.sensormonitor.model.util.pagination.PaginationProvider.getStartPosition;

@Repository
public class SensorDaoImpl implements SensorDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Sensor findById(long id) {
        return entityManager.find(Sensor.class, id);
    }

    @Override
    public List<Sensor> findAll() {
        return entityManager.createQuery(FIND_ALL_SENSORS, Sensor.class).getResultList();
    }

    @Override
    public PageDto findSensorByPartOfData(String text, PageDto pageDto) {
        int startPosition = getStartPosition(pageDto.getCurrentPage(), pageDto.getLimit());

        long rowsCount = entityManager.createQuery(FIND_COUNT.concat(FIND_SENSOR_BY_PART_OF_DATA), Long.class)
                .setParameter(1, text)
                .getSingleResult();
        List<Sensor> sensors = entityManager.createQuery(FIND_SENSOR_BY_PART_OF_DATA, Sensor.class)
                .setParameter(1, text)
                .setFirstResult(startPosition)
                .setMaxResults(pageDto.getLimit())
                .getResultList();

        pageDto.setPagesNumber(getPagesNumber(pageDto.getLimit(), rowsCount));
        pageDto.setSensors(sensors);

        return pageDto;
    }

    @Override
    public PageDto findAllPaginated(PageDto pageDto) {
        int startPosition = getStartPosition(pageDto.getCurrentPage(), pageDto.getLimit());

        long rowsCount = entityManager.createQuery(FIND_COUNT.concat(FIND_ALL_SENSORS), Long.class).getSingleResult();
        List<Sensor> sensors = entityManager.createQuery(FIND_ALL_SENSORS, Sensor.class)
                .setFirstResult(startPosition)
                .setMaxResults(pageDto.getLimit())
                .getResultList();

        pageDto.setPagesNumber(getPagesNumber(pageDto.getLimit(), rowsCount));
        pageDto.setSensors(sensors);

        return pageDto;
    }

    @Override
    public Sensor insert(Sensor sensor) {
        return entityManager.merge(sensor);
    }

    @Override
    public Sensor update(Sensor sensor) {
        return entityManager.merge(sensor);
    }

    @Override
    public void remove(Sensor sensor) {
        entityManager.remove(sensor);
    }
}