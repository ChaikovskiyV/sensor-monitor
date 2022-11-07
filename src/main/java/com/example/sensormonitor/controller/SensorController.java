package com.example.sensormonitor.controller;

import com.example.sensormonitor.model.dto.PageDto;
import com.example.sensormonitor.model.dto.SensorDto;
import com.example.sensormonitor.model.entity.Sensor;
import com.example.sensormonitor.exception.ApplicationNotValidDataException;
import com.example.sensormonitor.model.service.SensorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

import static com.example.sensormonitor.exception.ErrorMessages.NOT_CORRECT_SENSOR_DATA;
import static com.example.sensormonitor.model.service.RequestParamNames.*;

@RestController
@RequestMapping("api/v1/sensors")
public class SensorController {
    @Autowired
    private SensorService sensorService;

    @GetMapping(value = "/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMINISTRATOR', 'ROLE_VIEWER')")
    public HttpEntity<Sensor> findSensorById(@PathVariable(name = "id") long id) {
        Sensor sensor = sensorService.findById(id);

        return new ResponseEntity<>(sensor, HttpStatus.OK);
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMINISTRATOR', 'ROLE_VIEWER')")
    public HttpEntity<PageDto> findSensors(@RequestParam(name = "search", required = false) String search,
                                           @RequestParam(name = "page", required = false) Integer page,
                                           @RequestParam(name = "limit", required = false) Integer limit) {

        Map<String, Object> searchParams = new HashMap<>();
        searchParams.put(SEARCH, search);
        searchParams.put(PAGE, page);
        searchParams.put(LIMIT, limit);

        PageDto pageDtoDto = sensorService.findSensors(searchParams);

        return new ResponseEntity<>(pageDtoDto, HttpStatus.OK);
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMINISTRATOR')")
    public HttpEntity<Sensor> saveNewSensor(@Valid @RequestBody SensorDto sensorDto, BindingResult bindingResult) {

        if (sensorDto.getRangeFrom() >= sensorDto.getRangeTo()) {
            addRangeErrorToBindingResult(bindingResult);
        }

        if (bindingResult.hasErrors()) {
            throw new ApplicationNotValidDataException(NOT_CORRECT_SENSOR_DATA, bindingResult);
        }

        Sensor newSensor = sensorService.createSensor(sensorDto);

        return new ResponseEntity<>(newSensor, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMINISTRATOR')")
    public HttpEntity<Sensor> updateSensor(@PathVariable(name = "id") long id,
                                           @Valid @RequestBody SensorDto sensorDto, BindingResult bindingResult) {

        if (sensorDto.getRangeFrom() >= sensorDto.getRangeTo()) {
            addRangeErrorToBindingResult(bindingResult);
        }

        if (bindingResult.hasErrors()) {
            throw new ApplicationNotValidDataException(NOT_CORRECT_SENSOR_DATA, bindingResult);
        }

        Sensor updatedSensor = sensorService.updateSensor(sensorDto, id);

        return new ResponseEntity<>(updatedSensor, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMINISTRATOR')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteSensor(@PathVariable(name = "id") long id) {
        sensorService.delete(id);
    }

    private void addRangeErrorToBindingResult(BindingResult bindingResult) {
        final String rangeError = "rangeError";
        final String rangeErrorMessage = "The rangeTo parameter has to be higher than the rangeFrom";

        bindingResult.addError(new ObjectError(rangeError, rangeErrorMessage));
    }
}