package com.example.sensormonitor.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "ranges")
public class SensorRange extends BaseEntity {
    private int rangeFrom;
    private int rangeTo;
    @OneToMany(mappedBy = "sensorRange")
    @JsonIgnore
    private Set<Sensor> sensors;

    public SensorRange(int rangeFrom, int rangeTo) {
        sensors = new HashSet<>();
        this.rangeFrom = rangeFrom;
        this.rangeTo = rangeTo;
    }

    public SensorRange() {
        sensors = new HashSet<>();
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

    public void setRangeTo(int rangeTo) {
        this.rangeTo = rangeTo;
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
        SensorRange that = (SensorRange) o;
        return rangeFrom == that.rangeFrom && rangeTo == that.rangeTo;
    }

    @Override
    public int hashCode() {
        return Objects.hash(rangeFrom, rangeTo);
    }

    @Override
    public String toString() {
        return new StringBuilder(super.toString())
                .append("from=")
                .append(rangeFrom)
                .append("to=")
                .append(rangeTo)
                .append('}')
                .toString();
    }
}