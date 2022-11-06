package com.example.sensormonitor.model.dto;

import com.example.sensormonitor.model.entity.Sensor;
import com.example.sensormonitor.model.entity.SensorType;
import com.example.sensormonitor.model.entity.SensorUnit;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link Sensor} entity
 */
public class SensorDto implements Serializable {
    @NotEmpty(message = "A name can't be empty.")
    @Length(max = 30, message = "A name length has to be not longer than 30 characters.")
    private String name;
    @NotEmpty(message = "A description can't be empty.")
    @Length(max = 200, message = "A description length has to be not longer than 200 characters.")
    private String description;
    @NotEmpty(message = "A model can't be empty.")
    @Length(max = 15, message = "A model length has to be not longer than 15 characters.")
    private String model;
    private int rangeFrom;
    private int rangeTo;
    private transient SensorType sensorType;
    private transient SensorUnit sensorUnit;
    @Length(max = 40, message = "A location length has to be not longer than 40 characters.")
    private String location;

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getModel() {
        return model;
    }

    public int getRangeFrom() {
        return rangeFrom;
    }

    public void setRangeFrom(int rangeFrom) {
        this.rangeFrom = rangeFrom;
    }

    public int getRangeTo() {
        return rangeTo;
    }

    public SensorType getSensorType() {
        return sensorType;
    }

    public SensorUnit getSensorUnit() {
        return sensorUnit;
    }

    public String getLocation() {
        return location;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SensorDto sensorDto = (SensorDto) o;
        return rangeFrom == sensorDto.rangeFrom && rangeTo == sensorDto.rangeTo &&
                Objects.equals(name, sensorDto.name) && Objects.equals(description, sensorDto.description) &&
                Objects.equals(model, sensorDto.model) && Objects.equals(sensorType, sensorDto.sensorType) &&
                Objects.equals(sensorUnit, sensorDto.sensorUnit) && Objects.equals(location, sensorDto.location);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description, model, rangeFrom, rangeTo, sensorType, sensorUnit, location);
    }

    @Override
    public String toString() {
        return new StringBuilder(getClass().getSimpleName())
                .append("{name='")
                .append(name)
                .append("', description='")
                .append(description)
                .append("', model='")
                .append(model)
                .append("', rangeFrom=")
                .append(rangeFrom)
                .append("', rangeTo=")
                .append(rangeTo)
                .append(", sensorType=")
                .append(sensorType)
                .append(", unitType=")
                .append(sensorUnit)
                .append(", location='")
                .append(location)
                .append("'}")
                .toString();
    }
}