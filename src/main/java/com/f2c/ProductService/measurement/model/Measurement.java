package com.f2c.ProductService.measurement.model;

import jakarta.persistence.*;


@Entity
@Table(name = "measurements")
public class Measurement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long measurementId;

    @Column(nullable = false)
    private String unitType;

    @Column(nullable = false)
    private String unitSymbol;

    public Measurement(Long measurementId, String unitType, String unitSymbol) {
        this.measurementId = measurementId;
        this.unitType = unitType;
        this.unitSymbol = unitSymbol;
    }

    public Measurement() {
    }

    public Long getMeasurementId() {
        return measurementId;
    }

    public void setMeasurementId(Long measurementId) {
        this.measurementId = measurementId;
    }

    public String getUnitType() {
        return unitType;
    }

    public void setUnitType(String unitType) {
        this.unitType = unitType;
    }

    public String getUnitSymbol() {
        return unitSymbol;
    }

    public void setUnitSymbol(String unitSymbol) {
        this.unitSymbol = unitSymbol;
    }


}
