package com.techhub.spacegrid.model;

import java.sql.Date;

public class LeaseContract {
    private int id;
    private int propertyId;
    private int unitId;
    private int renterId;
    private Date startDate;
    private Date endDate;
    private double monthlyRate;
    private String status; // 'ACTIVE', 'TERMINATED', 'EXPIRED'

    public LeaseContract(int propertyId, int unitId, int renterId, Date startDate, Date endDate, double monthlyRate) {
        this.propertyId = propertyId;
        this.unitId = unitId;
        this.renterId = renterId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.monthlyRate = monthlyRate;
        this.status = "ACTIVE";
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public int getPropertyId() { return propertyId; }
    public int getUnitId() { return unitId; }
    public int getRenterId() { return renterId; }
    public Date getStartDate() { return startDate; }
    public Date getEndDate() { return endDate; }
    public double getMonthlyRate() { return monthlyRate; }
    public String getStatus() { return status; }
}