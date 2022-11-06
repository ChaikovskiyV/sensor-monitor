package com.example.sensormonitor;

import com.example.sensormonitor.model.configuration.ModelConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication(scanBasePackages = {"com.example.sensormonitor"})
@Import({ModelConfig.class})
public class SensorMonitorApplication {

	public static void main(String[] args) {
		SpringApplication.run(SensorMonitorApplication.class, args);
	}


}
