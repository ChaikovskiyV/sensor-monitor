package com.example.core.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "sensors")
public class Sensor extends BaseEntity {
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String description;
    @Column(nullable = false)
    private String model;
    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "range_id", nullable = false)
    private SensorRange range;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "type_id", nullable = false)
    private SensorType sensorType;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "unit_id", nullable = false)
    private SensorUnit unit;
    @Column(nullable = false)
    private String location;

    public Sensor() {
    }

    public Sensor(SensorBuilder builder) {
        this.name = builder.name;
        this.description = builder.description;
        this.model = builder.model;
        this.range = builder.range;
        this.sensorType = builder.sensorType;
        this.unit = builder.unit;
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

    public SensorRange getRange() {
        return range;
    }

    public void setRange(SensorRange range) {
        this.range = range;
    }

    public SensorType getSensorType() {
        return sensorType;
    }

    public void setSensorType(SensorType sensorType) {
        this.sensorType = sensorType;
    }

    public SensorUnit getUnit() {
        return unit;
    }

    public void setUnit(SensorUnit unit) {
        this.unit = unit;
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
        return Objects.equals(name, sensor.name) && Objects.equals(description, sensor.description) && Objects.equals(model, sensor.model) && Objects.equals(range, sensor.range) && Objects.equals(sensorType, sensor.sensorType) && Objects.equals(unit, sensor.unit) && Objects.equals(location, sensor.location);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description, model, range, sensorType, unit, location);
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
                .append(range)
                .append(", sensorType=")
                .append(sensorType)
                .append(", unit=")
                .append(unit)
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
        private SensorType sensorType;
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

        public SensorBuilder setSensorType(SensorType sensorType) {
            this.sensorType = sensorType;

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