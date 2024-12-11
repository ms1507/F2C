package com.f2c.ProductService.measurement.repository;

import com.f2c.ProductService.measurement.model.Measurement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MeasurementRepository extends JpaRepository<Measurement, Long> {
}
