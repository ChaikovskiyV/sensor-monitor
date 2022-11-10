package com.example.sensormonitor.model.entity;

import javax.persistence.*;

@MappedSuperclass
public class BaseEntity {
    @Id
    @SequenceGenerator(name = "id_generator", initialValue = 11)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_generator")
    private long id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return new StringBuilder(getClass().getSimpleName())
                .append("{id=")
                .append(id)
                .append(", ")
                .toString();
    }
}