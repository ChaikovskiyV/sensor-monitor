package com.example.sensormonitor.model.dao.impl;

import com.example.sensormonitor.model.dao.SensorTypeDao;
import com.example.sensormonitor.model.entity.SensorType;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

import static com.example.sensormonitor.model.dao.DatabaseQueries.FIND_ALL_SENSOR_TYPES;
import static com.example.sensormonitor.model.dao.DatabaseQueries.FIND_SENSOR_TYPE_BY_TYPE;

@Repository
public class SensorTypeDaoImpl implements SensorTypeDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public SensorType findById(long id) {
        return entityManager.find(SensorType.class, id);
    }

    @Override
    public List<SensorType> findAll() {
        return entityManager.createQuery(FIND_ALL_SENSOR_TYPES, SensorType.class).getResultList();
    }

    @Override
    public void remove(SensorType sensorType) {
        entityManager.remove(sensorType);
    }

    @Override
    public List<SensorType> findByTypeName(String type) {
        return entityManager.createQuery(FIND_SENSOR_TYPE_BY_TYPE, SensorType.class)
                .setParameter(1, type)
                .getResultList();
    }
}