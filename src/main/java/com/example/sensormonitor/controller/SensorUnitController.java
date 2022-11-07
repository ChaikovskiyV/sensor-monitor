package com.example.sensormonitor.controller;

import com.example.sensormonitor.model.entity.SensorUnit;
import com.example.sensormonitor.model.service.SensorUnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/units")
public class SensorUnitController {
    @Autowired
    private SensorUnitService unitService;

    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMINISTRATOR')")
    public List<SensorUnit> findAllUnits() {
        return unitService.findAll();
    }
}