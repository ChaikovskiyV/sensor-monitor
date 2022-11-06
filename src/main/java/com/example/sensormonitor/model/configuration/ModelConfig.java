package com.example.sensormonitor.model.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@ComponentScan(basePackages = {"com.example.sensormonitor"})
@EnableTransactionManagement
public class ModelConfig {
}