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
@Table(name = "units")
public class SensorUnit extends BaseEntity {
    @Column(name = "unit", unique = true, nullable = false)
    private String unit;
    @OneToMany(mappedBy = "sensorUnit")
    @JsonIgnore
    private Set<Sensor> sensors;

    public SensorUnit() {
        sensors = new HashSet<>();
    }

    public SensorUnit(String unit) {
        sensors = new HashSet<>();
        this.unit = unit;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unitType) {
        this.unit = unitType;
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
        SensorUnit that = (SensorUnit) o;
        return Objects.equals(unit, that.unit);
    }

    @Override
    public int hashCode() {
        return Objects.hash(unit);
    }

    @Override
    public String toString() {
        return new StringBuilder(super.toString())
                .append("unitType='")
                .append(unit)
                .append('}')
                .toString();
    }
}