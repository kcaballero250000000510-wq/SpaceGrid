package com.techhub.spacegrid.model;

public class Property {
    private int id;
    private int ownerId;
    private String propertyName;
    private boolean hasExactDimensions;
    private String sizeDescription;
    private double totalAreaSqm;
    private int gridRows;
    private int gridColumns;
    private String status; // 'DRAFT', 'PUBLISHED'

    // Constructor for creating a new property before DB insertion
    public Property(String propertyName, int gridRows, int gridColumns) {
        this.propertyName = propertyName;
        this.gridRows = gridRows;
        this.gridColumns = gridColumns;
        this.status = "DRAFT";
        this.ownerId = 1; // Default landlord ID for testing
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

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getOwnerId() { return ownerId; }
    public void setOwnerId(int ownerId) { this.ownerId = ownerId; }

    public String getPropertyName() { return propertyName; }
    public void setPropertyName(String propertyName) { this.propertyName = propertyName; }

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