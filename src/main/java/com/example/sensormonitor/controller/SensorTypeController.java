package com.example.sensormonitor.controller;

import com.example.sensormonitor.model.entity.SensorType;
import com.example.sensormonitor.model.service.SensorTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/types")
public class SensorTypeController {
    @Autowired
    private SensorTypeService typeService;

    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMINISTRATOR')")
    public List<SensorType> findAllTypes() {
        return typeService.findAll();
    }
}
