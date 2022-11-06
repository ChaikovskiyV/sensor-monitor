package com.example.sensormonitor.model.dto;

import com.example.sensormonitor.model.entity.Sensor;

import java.util.List;

public class PageDto {
    private int limit;
    private int currentPage;
    private long pagesNumber;
    private int startWithPosition;

    private List<Sensor> sensors;

    public PageDto(int limit, int currentPage) {
        this.limit = limit;
        this.currentPage = currentPage;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public long getPagesNumber() {
        return pagesNumber;
    }

    public void setPagesNumber(long pagesNumber) {
        this.pagesNumber = pagesNumber;
    }

    public int getStartWithPosition() {
        return startWithPosition;
    }

    public void setStartWithPosition(int startWithPosition) {
        this.startWithPosition = startWithPosition;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public List<Sensor> getSensors() {
        return sensors;
    }

    public void setSensors(List<Sensor> sensors) {
        this.sensors = sensors;
    }
}