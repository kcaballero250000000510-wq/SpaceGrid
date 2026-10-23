package com.techhub.spacegrid.model;

import java.math.BigDecimal;

public class RentableUnit {
    private int id;
    private int propertyId;
    private String unitName;
    private BigDecimal rentalRate;
    private boolean includesElectricity;
    private boolean includesWater;
    private boolean hasExactDimensions;
    private String unitSizeDescription;
    private double totalAreaSqm;
    private String status; // 'AVAILABLE', 'OCCUPIED'

    // 1. Default constructor required by DAO mapping
    public RentableUnit() {
        this.status = "AVAILABLE";
        this.rentalRate = BigDecimal.ZERO;
        this.includesElectricity = false;
        this.includesWater = false;
    }

    // 2. Constructors for instantiation
    public RentableUnit(int propertyId, String unitName, double rentalRate) {
        this();
        this.propertyId = propertyId;
        this.unitName = unitName;
        this.rentalRate = BigDecimal.valueOf(rentalRate);
    }

    public RentableUnit(int propertyId, String unitName, BigDecimal rentalRate) {
        this();
        this.propertyId = propertyId;
        this.unitName = unitName;
        this.rentalRate = rentalRate;
    }

    // Overloaded dimension methods
    public void setDimensions(double totalAreaSqm) {
        this.hasExactDimensions = true;
        this.totalAreaSqm = totalAreaSqm;
    }

    public void setDimensions(String unitSizeDescription) {
        this.hasExactDimensions = false;
        this.unitSizeDescription = unitSizeDescription;
    }

    // Utility Toggles
    public void setUtilitiesIncluded(boolean electricity, boolean water) {
        this.includesElectricity = electricity;
        this.includesWater = water;
    }

    // Core Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getPropertyId() { return propertyId; }
    public void setPropertyId(int propertyId) { this.propertyId = propertyId; }

    // Standardized DB fields (unit_name, rental_rate)
    public String getUnitName() { return unitName; }
    public void setUnitName(String unitName) { this.unitName = unitName; }

    public BigDecimal getRentalRate() { return rentalRate; }
    public void setRentalRate(BigDecimal rentalRate) { this.rentalRate = rentalRate; }

    // Aliases for legacy code compatibility
    public String getUnitIdentifier() { return unitName; }
    public void setUnitIdentifier(String unitIdentifier) { this.unitName = unitIdentifier; }

    public double getBasePrice() { return rentalRate != null ? rentalRate.doubleValue() : 0.0; }
    public void setBasePrice(double basePrice) { this.rentalRate = BigDecimal.valueOf(basePrice); }

    public boolean isIncludesElectricity() { return includesElectricity; }
    public void setIncludesElectricity(boolean includesElectricity) { this.includesElectricity = includesElectricity; }

    public boolean isIncludesWater() { return includesWater; }
    public void setIncludesWater(boolean includesWater) { this.includesWater = includesWater; }

    public boolean isHasExactDimensions() { return hasExactDimensions; }
    public void setHasExactDimensions(boolean hasExactDimensions) { this.hasExactDimensions = hasExactDimensions; }

    public String getUnitSizeDescription() { return unitSizeDescription; }
    public void setUnitSizeDescription(String unitSizeDescription) { this.unitSizeDescription = unitSizeDescription; }

    public double getTotalAreaSqm() { return totalAreaSqm; }
    public void setTotalAreaSqm(double totalAreaSqm) { this.totalAreaSqm = totalAreaSqm; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}