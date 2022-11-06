package com.example.sensormonitor.model.dao.impl;

import com.example.sensormonitor.model.dao.SensorRangeDao;
import com.example.sensormonitor.model.entity.SensorRange;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

import static com.example.sensormonitor.model.dao.DatabaseQueries.FIND_ALL_SENSOR_RANGES;

@Repository
public class SensorRangeDaoImpl implements SensorRangeDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public SensorRange findById(long id) {
        return entityManager.find(SensorRange.class, id);
    }

    @Override
    public List<SensorRange> findAll() {
        return entityManager.createQuery(FIND_ALL_SENSOR_RANGES, SensorRange.class).getResultList();
    }

    @Override
    public void remove(SensorRange sensorRange) {
        entityManager.remove(sensorRange);
    }
}