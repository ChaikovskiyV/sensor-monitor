package com.example.sensormonitor.model.dao;

/**
 * Contains constants with queries to the database.
 */
public final class DatabaseQueries {
    public static final String FIND_COUNT = "SELECT count(*) ";
    public static final String FIND_USER_BY_EMAIL = "FROM User WHERE email=?1";

    public static final String FIND_ALL_SENSORS = "FROM Sensor";
    public static final String FIND_SENSOR_BY_PART_OF_DATA = "FROM Sensor s WHERE " +
            "CONCAT_WS(' ', s.name, " +
            "s.model, " +
            "s.description, " +
            "s.location, " +
            "s.sensorType.type, " +
            "s.sensorUnit.unit, " +
            "s.sensorRange.rangeFrom, " +
            "s.sensorRange.rangeTo) LIKE CONCAT('%', ?1, '%')";

    public static final String FIND_ALL_SENSOR_TYPES = "FROM SensorType";
    public static final String FIND_SENSOR_TYPE_BY_TYPE = "FROM SensorType WHERE type=?1";
    public static final String FIND_ALL_SENSOR_UNITES = "FROM SensorUnit";
    public static final String FIND_SENSOR_UNIT_BY_UNIT = "FROM SensorUnit WHERE unit=?1";
    public static final String FIND_ALL_SENSOR_RANGES = "FROM SensorRange";


    private DatabaseQueries() {
    }
}