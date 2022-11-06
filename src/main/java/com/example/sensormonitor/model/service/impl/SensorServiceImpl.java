package com.example.sensormonitor.model.service.impl;

import com.example.sensormonitor.model.dao.SensorDao;
import com.example.sensormonitor.model.dao.SensorRangeDao;
import com.example.sensormonitor.model.dao.SensorTypeDao;
import com.example.sensormonitor.model.dao.SensorUnitDao;
import com.example.sensormonitor.model.dto.PageDto;
import com.example.sensormonitor.model.dto.PageDtoFactory;
import com.example.sensormonitor.model.dto.SensorDto;
import com.example.sensormonitor.model.entity.*;
import com.example.sensormonitor.exception.ApplicationDuplicateException;
import com.example.sensormonitor.exception.ApplicationNotFoundException;
import com.example.sensormonitor.exception.ApplicationNotValidDataException;
import com.example.sensormonitor.model.service.RequestParamNames;
import com.example.sensormonitor.model.service.SensorService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static com.example.sensormonitor.exception.ErrorMessages.*;

@Service
public class SensorServiceImpl implements SensorService {
    public static final Logger logger = LogManager.getLogger();
    private final SensorDao sensorDao;
    private final SensorTypeDao sensorTypeDao;
    private final SensorUnitDao sensorUnitDao;
    private final SensorRangeDao sensorRangeDao;

    @Autowired
    public SensorServiceImpl(SensorDao sensorDao,
                             SensorTypeDao sensorTypeDao,
                             SensorUnitDao sensorUnitDao,
                             SensorRangeDao sensorRangeDao) {
        this.sensorDao = sensorDao;
        this.sensorTypeDao = sensorTypeDao;
        this.sensorUnitDao = sensorUnitDao;
        this.sensorRangeDao = sensorRangeDao;
    }

    @Override
    public Sensor findById(long id) {
        if (id <= 0) {
            logger.error(() -> String.format(NOT_CORRECT_ID, id));
            throw new ApplicationNotValidDataException(NOT_CORRECT_ID, id);
        }

        return Optional.ofNullable(sensorDao.findById(id)).orElseThrow(() -> {
            logger.error(() -> String.format(NOT_FOUND_SENSOR_BY_ID, id));
            throw new ApplicationNotFoundException(NOT_FOUND_SENSOR_BY_ID, id);
        });
    }

    @Transactional
    @Override
    public void delete(long id) {
        Sensor sensor = findById(id);
        sensorDao.remove(sensor);
    }

    @Override
    public PageDto findSensors(Map<String, Object> searchParams) {
        String searchParam = (String) searchParams.get(RequestParamNames.SEARCH);
        int page = searchParams.get(RequestParamNames.PAGE) != null ? (Integer) searchParams.get(RequestParamNames.PAGE) : 0;
        int limit = searchParams.get(RequestParamNames.LIMIT) != null ? (Integer) searchParams.get(RequestParamNames.LIMIT) : 0;

        Map<String, Integer> notCorrectPaginationParams = buildNotCorrectPaginationParamsMap(page, limit);

        if (!notCorrectPaginationParams.isEmpty()) {
            logger.error(() -> String.format(NOT_CORRECT_PAGINATION_DATA, notCorrectPaginationParams));
            throw new ApplicationNotValidDataException(NOT_CORRECT_PAGINATION_DATA, notCorrectPaginationParams);
        }

        PageDto pageDto = PageDtoFactory.createPageDto(page, limit);

        return searchParam != null ?
                sensorDao.findSensorByPartOfData(searchParam, pageDto) : sensorDao.findAllPaginated(pageDto);
    }

    @Transactional
    @Override
    public Sensor createSensor(SensorDto sensorDto) {
        Sensor sensor = SensorFactory.createSensorFromDto(sensorDto);

        if (isSensorAlreadyExist(sensor)) {
            logger.error(() -> String.format(DUPLICATE_SENSOR, sensor));
            throw new ApplicationDuplicateException(DUPLICATE_SENSOR, sensor);
        }

        replaceExistChild(sensor);

        return sensorDao.insert(sensor);
    }

    @Transactional
    @Override
    public Sensor updateSensor(SensorDto sensorDto, long id) {
        Sensor currentSensor = findById(id);
        Sensor newSensor = SensorFactory.createSensorFromDto(sensorDto);
        newSensor.setId(id);

        if (currentSensor.equals(newSensor)) {
            return currentSensor;
        }

        if (isSensorAlreadyExist(newSensor)) {
            logger.error(() -> String.format(DUPLICATE_SENSOR, newSensor));
            throw new ApplicationDuplicateException(DUPLICATE_SENSOR, newSensor);
        }

        replaceExistChild(newSensor);

        return sensorDao.update(newSensor);
    }

    private boolean isSensorAlreadyExist(Sensor sensor) {
        List<Sensor> sensors = sensorDao.findAll();

        return sensors.stream().anyMatch(s -> s.equals(sensor));
    }

    private Map<String, Integer> buildNotCorrectPaginationParamsMap(int page, int limit) {
        Map<String, Integer> wrongPaginationParams = new HashMap<>();

        if (page < 0) {
            wrongPaginationParams.put(RequestParamNames.PAGE, page);
        }
        if (limit < 0) {
            wrongPaginationParams.put(RequestParamNames.LIMIT, limit);
        }

        return wrongPaginationParams;
    }

    private Sensor replaceExistChild(Sensor sensor) {
        Optional<SensorUnit> unitOptional = findTheSameUnit(sensor.getSensorUnit());
        Optional<SensorType> typeOptional = findTheSameType(sensor.getSensorType());
        Optional<SensorRange> rangeOptional = findTheSameRange(sensor.getSensorRange());

        unitOptional.ifPresent(sensor::setSensorUnit);
        typeOptional.ifPresent(sensor::setSensorType);
        rangeOptional.ifPresent(sensor :: setSensorRange);

        return sensor;
    }

    private Optional<SensorUnit> findTheSameUnit(SensorUnit unit) {
        List<SensorUnit> units = sensorUnitDao.findByUnitName(unit.getUnit());

        return units.stream().filter(u -> u.equals(unit)).findFirst();
    }

    private Optional<SensorType> findTheSameType(SensorType type) {
        List<SensorType> types = sensorTypeDao.findByTypeName(type.getType());

        return types.stream().filter(t -> t.equals(type)).findFirst();
    }

    private Optional<SensorRange> findTheSameRange(SensorRange range) {
        List<SensorRange> ranges = sensorRangeDao.findAll();

        return ranges.stream().filter(r -> r.equals(range)).findFirst();
    }
}