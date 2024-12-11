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

        return ResponseEntity.ok(measurementService.getAllMeasurements());
    }

    @GetMapping("/category/{id}")
    public ResponseEntity<Measurement> getCategory(@PathVariable Long id) {
        return measurementService.getMeasurementById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/category/{id}")
    public ResponseEntity<Measurement> updateCategory(@PathVariable Long id, @RequestBody Measurement updatedCategory) {
        return ResponseEntity.ok(measurementService.updateMeasurementById(id, updatedCategory));
    }
    
    @PostMapping("/category")
    public ResponseEntity<Measurement> createCategory(@RequestBody Measurement measurement) {
        return ResponseEntity.ok(measurementService.createMeasurement(measurement));
    }
    
        @DeleteMapping("/category/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        measurementService.getMeasurementById(id)
            .ifPresent(category -> measurementService.deleteMeasurement(id));
        return ResponseEntity.noContent().build();
    }
}
