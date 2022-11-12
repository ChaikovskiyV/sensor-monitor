package com.example.sensormonitor.model.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "sensors")
public class Sensor extends BaseEntity {
    @Column(nullable = false)
    private String name;
    private String description;
    @Column(nullable = false)
    private String model;
    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "range_id", nullable = false)
    private SensorRange sensorRange;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "type_id", nullable = false)
    private SensorType sensorType;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "unit_id", nullable = false)
    private SensorUnit sensorUnit;
    private String location;

    public Sensor() {
    }

    public Sensor(SensorBuilder builder) {
        this.name = builder.name;
        this.description = builder.description;
        this.model = builder.model;
        this.sensorRange = builder.range;
        this.sensorType = builder.type;
        this.sensorUnit = builder.unit;
        this.location = builder.location;
    }

    public static SensorBuilder getBuilder() {
        return new SensorBuilder();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public SensorRange getSensorRange() {
        return sensorRange;
    }

    public void setSensorRange(SensorRange range) {
        this.sensorRange = range;
    }

    public SensorType getSensorType() {
        return sensorType;
    }

    public void setSensorType(SensorType sensorType) {
        this.sensorType = sensorType;
    }

    public SensorUnit getSensorUnit() {
        return sensorUnit;
    }

    public void setSensorUnit(SensorUnit unit) {
        this.sensorUnit = unit;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sensor sensor = (Sensor) o;
        return Objects.equals(name, sensor.name) && Objects.equals(description, sensor.description) && Objects.equals(model, sensor.model) && Objects.equals(sensorRange, sensor.sensorRange) && Objects.equals(sensorType, sensor.sensorType) && Objects.equals(sensorUnit, sensor.sensorUnit) && Objects.equals(location, sensor.location);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description, model, sensorRange, sensorType, sensorUnit, location);
    }

    @Override
    public String toString() {
        return new StringBuilder(super.toString())
                .append("name='")
                .append(name)
                .append("', description='")
                .append(description)
                .append("', model='")
                .append(model)
                .append("', range=")
                .append(sensorRange)
                .append(", type=")
                .append(sensorType)
                .append(", unit=")
                .append(sensorUnit)
                .append(", location='")
                .append(location)
                .append("'}")
                .toString();
    }

    static class SensorBuilder {
        private String name;
        private String description;
        private String model;
        private SensorRange range;
        private SensorType type;
        private SensorUnit unit;
        private String location;

        SensorBuilder() {
        }

        public SensorBuilder setName(String name) {
            this.name = name;

            return this;
        }

        public SensorBuilder setDescription(String description) {
            this.description = description;

            return this;
        }

        public SensorBuilder setModel(String model) {
            this.model = model;

            return this;
        }

        public SensorBuilder setRange(SensorRange range) {
            this.range = range;

            return this;
        }

        public SensorBuilder setType(SensorType type) {
            this.type = type;

            return this;
        }

        public SensorBuilder setUnit(SensorUnit unit) {
            this.unit = unit;

            return this;
        }

        public SensorBuilder setLocation(String location) {
            this.location = location;

            return this;
        }

        public Sensor build() {
            return new Sensor(this);
        }
    }
}