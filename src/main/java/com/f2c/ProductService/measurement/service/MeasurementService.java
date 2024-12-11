package com.f2c.ProductService.measurement.service;

import com.f2c.ProductService.measurement.model.Measurement;
import com.f2c.ProductService.measurement.repository.MeasurementRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class MeasurementService {

    @Autowired
    private MeasurementRepository measurementRepository;

    public List<Measurement> getAllMeasurements() {
        return measurementRepository.findAll();
    }

    public Optional<Measurement> getMeasurementById(Long measurementId) {
        return measurementRepository.findById(measurementId);
    }

    public Measurement updateMeasurementById(Long measurementId, Measurement updatedMeasurement) {
        return measurementRepository.findById(measurementId).map(existingMeasurement -> {
            existingMeasurement.setUnitType(updatedMeasurement.getUnitType());
            existingMeasurement.setUnitSymbol(updatedMeasurement.getUnitSymbol());
            return measurementRepository.save(existingMeasurement);
        }).orElseThrow(() -> new RuntimeException("No Measurement found for updation.."));
    }

    public Measurement createMeasurement(Measurement measurement) {
        return measurementRepository.save(measurement);
    }

    public void deleteMeasurement(Long measurementId) {
        measurementRepository.deleteById(measurementId);
    }

}
