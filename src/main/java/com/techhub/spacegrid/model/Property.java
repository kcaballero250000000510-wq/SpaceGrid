package com.techhub.spacegrid.model;

public class Property {
    private int id;
    private int ownerId;
    private String propertyName;
    private String propertyType;
    private String address;
    private String description;
    private boolean hasExactDimensions;
    private String sizeDescription;
    private double totalAreaSqm;
    private int gridRows;
    private int gridColumns;
    private String status; // 'DRAFT', 'PUBLISHED'

    // 1. Default constructor for DAO and form initialization
    public Property() {
        this.status = "DRAFT";
        this.ownerId = 1; // Default landlord ID for testing
        this.gridRows = 1;
        this.gridColumns = 1;
    }

    // 2. Existing constructor for grid insertion
    public Property(String propertyName, int gridRows, int gridColumns) {
        this();
        this.propertyName = propertyName;
        this.gridRows = gridRows;
        this.gridColumns = gridColumns;
    }

    // Overloaded method to set spatial details
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

    public int getOwnerId() { return ownerId; }
    public void setOwnerId(int ownerId) { this.ownerId = ownerId; }

    public String getPropertyName() { return propertyName; }
    public void setPropertyName(String propertyName) { this.propertyName = propertyName; }

    // Alias methods for UI compatibility (getName / setName)
    public String getName() { return propertyName; }
    public void setName(String name) { this.propertyName = name; }

    public String getPropertyType() { return propertyType != null ? propertyType : "Apartment"; }
    public void setPropertyType(String propertyType) { this.propertyType = propertyType; }

    public String getAddress() { return address != null ? address : ""; }
    public void setAddress(String address) { this.address = address; }

    public String getDescription() { return description != null ? description : ""; }
    public void setDescription(String description) { this.description = description; }

    public boolean isHasExactDimensions() { return hasExactDimensions; }
    public String getSizeDescription() { return sizeDescription; }
    public double getTotalAreaSqm() { return totalAreaSqm; }

    public int getGridRows() { return gridRows; }
    public void setGridRows(int gridRows) { this.gridRows = gridRows; }

    public int getGridColumns() { return gridColumns; }
    public void setGridColumns(int gridColumns) { this.gridColumns = gridColumns; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}