package com.example.core.dto;

import com.example.core.entity.SensorRange;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.example.core.entity.Sensor} entity
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
    private SensorRange sensorRange;
    private String sensorType;
    private String unitType;
    @Length(max = 40, message = "A location length has to be not longer than 40 characters.")
    private String location;

    public SensorDto() {
    }

    public SensorDto(String name,
                     String description,
                     String model,
                     SensorRange sensorRange, String sensorType,
                     String unitType,
                     String location) {
        this.name = name;
        this.description = description;
        this.model = model;
        this.sensorRange = sensorRange;
        this.sensorType = sensorType;
        this.unitType = unitType;
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getModel() {
        return model;
    }

    public SensorRange getSensorRange() {
        return sensorRange;
    }

    public void setSensorRange(SensorRange sensorRange) {
        this.sensorRange = sensorRange;
    }

    public String getSensorType() {
        return sensorType;
    }

    public String getUnitType() {
        return unitType;
    }

    public String getLocation() {
        return location;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SensorDto entity = (SensorDto) o;
        return Objects.equals(this.name, entity.name) &&
                Objects.equals(this.description, entity.description) &&
                Objects.equals(this.model, entity.model) &&
                Objects.equals(this.sensorRange, entity.sensorRange) &&
                Objects.equals(this.sensorType, entity.sensorType) &&
                Objects.equals(this.unitType, entity.unitType) &&
                Objects.equals(this.location, entity.location);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description, model, sensorRange, sensorType, unitType, location);
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
                .append("', sensorRange=")
                .append(sensorRange)
                .append(", sensorType=")
                .append(sensorType)
                .append(", unitType=")
                .append(unitType)
                .append(", location='")
                .append(location)
                .append("'}")
                .toString();
    }
}