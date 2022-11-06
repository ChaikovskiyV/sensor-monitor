package com.example.sensormonitor.model.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "types")
public class SensorType extends BaseEntity {
    @Column(unique = true, nullable = false)
    private String type;

    @OneToMany(mappedBy = "sensorType")
    @JsonIgnore
    private Set<Sensor> sensors;

    public SensorType() {
        sensors = new HashSet<>();
    }

    public SensorType(String type) {
        sensors = new HashSet<>();
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String sensorType) {
        this.type = sensorType;
    }

    public Set<Sensor> getSensors() {
        return sensors;
    }

    public void setSensors(Set<Sensor> sensors) {
        this.sensors = sensors;
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