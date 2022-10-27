package com.example.core.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "units")
public class SensorUnit extends BaseEntity {
    @Column(nullable = false)
    private String unitType;

    public SensorUnit() {
    }

    public SensorUnit(String unitType) {
        this.unitType = unitType;
    }

    public String getUnitType() {
        return unitType;
    }

    public void setUnitType(String unitType) {
        this.unitType = unitType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SensorUnit that = (SensorUnit) o;
        return Objects.equals(unitType, that.unitType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(unitType);
    }

    @Override
    public String toString() {
        return new StringBuilder(super.toString())
                .append("unitType='")
                .append(unitType)
                .append('}')
                .toString();
    }
}