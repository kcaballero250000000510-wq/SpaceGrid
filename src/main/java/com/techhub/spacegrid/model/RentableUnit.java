package com.techhub.spacegrid.model;

public class RentableUnit {
    private int id;
    private int propertyId;
    private String unitIdentifier;
    private double basePrice;
    private boolean includesElectricity;
    private boolean includesWater;
    private boolean hasExactDimensions;
    private String unitSizeDescription;
    private double totalAreaSqm;
    private String status; // 'AVAILABLE', 'OCCUPIED'

    public RentableUnit(int propertyId, String unitIdentifier, double basePrice) {
        this.propertyId = propertyId;
        this.unitIdentifier = unitIdentifier;
        this.basePrice = basePrice;
        this.status = "AVAILABLE";
        this.includesElectricity = false;
        this.includesWater = false;
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

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getPropertyId() { return propertyId; }
    public String getUnitIdentifier() { return unitIdentifier; }
    public double getBasePrice() { return basePrice; }
    public boolean isIncludesElectricity() { return includesElectricity; }
    public boolean isIncludesWater() { return includesWater; }
    public boolean isHasExactDimensions() { return hasExactDimensions; }
    public String getUnitSizeDescription() { return unitSizeDescription; }
    public double getTotalAreaSqm() { return totalAreaSqm; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}