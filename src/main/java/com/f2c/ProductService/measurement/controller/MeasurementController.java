package com.f2c.ProductService.measurement.controller;

import com.f2c.ProductService.measurement.service.MeasurementService;
import com.f2c.ProductService.measurement.model.Measurement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class MeasurementController {

    @Autowired
    MeasurementService measurementService;

    @GetMapping("/measurements")
    public ResponseEntity<List<Measurement>> getMeasurements() {

        return ResponseEntity.ok(measurementService.fetchAllMeasurements());
    }

    @GetMapping("/measurement/{id}")
    public ResponseEntity<Measurement> getMeasurement(@PathVariable Long id) {
        return measurementService.getMeasurementById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/measurement/{id}")
    public ResponseEntity<Measurement> updateMeasurement(@PathVariable Long id, @RequestBody Measurement updatedMeasurement) {
        return ResponseEntity.ok(measurementService.updateMeasurementById(id, updatedMeasurement));
    }

    @PostMapping("/measurement")
    public ResponseEntity<Measurement> createMeasurement(@RequestBody Measurement measurement) {
        return ResponseEntity.ok(measurementService.saveMeasurement(measurement));
    }

    @DeleteMapping("/measurement/{id}")
    public ResponseEntity<Void> deleteMeasurement(@PathVariable Long id) {
        measurementService.getMeasurementById(id)
                .ifPresent(measurement -> measurementService.deleteMeasurement(id));
        return ResponseEntity.noContent().build();
    }
}
