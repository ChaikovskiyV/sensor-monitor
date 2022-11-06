package com.example.sensormonitor.model.dao.impl;

import com.example.sensormonitor.model.dao.SensorUnitDao;
import com.example.sensormonitor.model.entity.SensorUnit;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

import static com.example.sensormonitor.model.dao.DatabaseQueries.*;

@Repository
public class SensorUnitDaoImpl implements SensorUnitDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public SensorUnit findById(long id) {
        return entityManager.find(SensorUnit.class, id);
    }

    @Override
    public List<SensorUnit> findAll() {
        return entityManager.createQuery(FIND_ALL_SENSOR_UNITES, SensorUnit.class).getResultList();
    }

    @Override
    public void remove(SensorUnit sensorUnit) {
        entityManager.remove(sensorUnit);
    }

    @Override
    public List<SensorUnit> findByUnitName(String unit) {
        return entityManager.createQuery(FIND_SENSOR_UNIT_BY_UNIT, SensorUnit.class)
                .setParameter(1, unit)
                .getResultList();
    }
}