package com.example.core.entity;


import javax.persistence.Entity;
import java.util.Objects;

@Entity
public class SensorType extends BaseEntity {
    private String type;

    public SensorType() {
    }

    public SensorType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String sensorType) {
        this.type = sensorType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SensorType that = (SensorType) o;
        return Objects.equals(type, that.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type);
    }

    @Override
    public String toString() {
        return new StringBuilder(super.toString())
                .append("type='")
                .append(type)
                .append('}')
                .toString();
    }
}