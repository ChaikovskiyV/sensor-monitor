package com.example.core.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "ranges")
public class SensorRange extends BaseEntity {
    private int rangeFrom;
    private int rangeTo;

    public SensorRange(int rangeFrom, int rangeTo) {
        this.rangeFrom = rangeFrom;
        this.rangeTo = rangeTo;
    }

    public SensorRange() {
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
