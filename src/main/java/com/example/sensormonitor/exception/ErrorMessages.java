package com.example.sensormonitor.exception;

public final class ErrorMessages {
    public static final String EMPTY_EMAIL = "Email is empty.";
    public static final String NOT_FOUND_USER_BY_NAME = "User with name %s is not found";
    public static final String NOT_FOUND_SENSOR_BY_ID = "Sensor with id=%s is not found";
    public static final String NOT_VALID_TOKEN = "The provided token %s is not valid";
    public static final String NOT_CORRECT_ID = "Id=%s is not correct";
    public static final String NOT_CORRECT_PAGINATION_DATA = "Pagination data %s is not correct";
    public static final String NOT_CORRECT_SENSOR_DATA = "Sensor data is not correct: \n %s";
    public static final String NOT_CORRECT_LOGIN_DATA = "Username or password is not correct: \n %s";
    public static final String DUPLICATE_SENSOR = "The sensor %s is already exist.";
    public static final String INTERNAL_SERVER_ERR = "Something went wrong :). We'll try to fix this problem.";
    public static final String ACCESS_FORBIDDEN_ERR = "Access is forbidden. User is not authorized.";

    private ErrorMessages() {
    }
}