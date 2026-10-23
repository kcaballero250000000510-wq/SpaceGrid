package com.techhub.spacegrid.model;

public class Property {
    private int id;
    private int landlordId;
    private String name;
    private String propertyType;
    private String address;
    private String description;
    private boolean hasExactDimensions;
    private String sizeDescription;
    private double totalAreaSqm;
    private int gridRows;
    private int gridColumns;
    private String status; // 'ACTIVE', 'DRAFT', etc.

    // 1. Default constructor for DAO and form initialization
    public Property() {
        this.status = "ACTIVE";
        this.landlordId = 1; // Default landlord ID for testing
        this.gridRows = 1;
        this.gridColumns = 1;
    }

    // 2. Existing constructor for grid insertion
    public Property(String name, int gridRows, int gridColumns) {
        this();
        this.name = name;
        this.gridRows = gridRows;
        this.gridColumns = gridColumns;
    }

    // Overloaded methods to set spatial details
    public void setDimensions(double totalAreaSqm) {
        this.hasExactDimensions = true;
        this.totalAreaSqm = totalAreaSqm;
    }

    public void setDimensions(String sizeDescription) {
        this.hasExactDimensions = false;
        this.sizeDescription = sizeDescription;
    }

    // Primary Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    // Standard Landlord ID Getters & Setters
    public int getLandlordId() { return landlordId; }
    public void setLandlordId(int landlordId) { this.landlordId = landlordId; }

    // Alias for backward compatibility if old DAOs use ownerId
    public int getOwnerId() { return landlordId; }
    public void setOwnerId(int ownerId) { this.landlordId = ownerId; }

    // Standard Property Name Getters & Setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    // Alias for backward compatibility if old DAOs use propertyName
    public String getPropertyName() { return name; }
    public void setPropertyName(String propertyName) { this.name = propertyName; }

    public String getPropertyType() { return propertyType != null ? propertyType : "Apartment"; }
    public void setPropertyType(String propertyType) { this.propertyType = propertyType; }

    public String getAddress() { return address != null ? address : ""; }
    public void setAddress(String address) { this.address = address; }

    public String getDescription() { return description != null ? description : ""; }
    public void setDescription(String description) { this.description = description; }

    public boolean isHasExactDimensions() { return hasExactDimensions; }
    public void setHasExactDimensions(boolean hasExactDimensions) { this.hasExactDimensions = hasExactDimensions; }

    public String getSizeDescription() { return sizeDescription; }
    public void setSizeDescription(String sizeDescription) { this.sizeDescription = sizeDescription; }

    public double getTotalAreaSqm() { return totalAreaSqm; }
    public void setTotalAreaSqm(double totalAreaSqm) { this.totalAreaSqm = totalAreaSqm; }

    public int getGridRows() { return gridRows; }
    public void setGridRows(int gridRows) { this.gridRows = gridRows; }

    public int getGridColumns() { return gridColumns; }
    public void setGridColumns(int gridColumns) { this.gridColumns = gridColumns; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}