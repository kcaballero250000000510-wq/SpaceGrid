/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.spacegrid.model;

import java.awt.Color;

public class Property {
    private String category;
    private String title;
    private String location;
    private String availableBlocks;
    private String priceRange;
    private String priceUnit;
    private Color statusColor;
    private String statusText;

    public Property(String category, String title, String location, String availableBlocks, 
                    String priceRange, String priceUnit, Color statusColor, String statusText) {
        this.category = category;
        this.title = title;
        this.location = location;
        this.availableBlocks = availableBlocks;
        this.priceRange = priceRange;
        this.priceUnit = priceUnit;
        this.statusColor = statusColor;
        this.statusText = statusText;
    }

    public String getCategory() { return category; }
    public String getTitle() { return title; }
    public String getLocation() { return location; }
    public String getAvailableBlocks() { return availableBlocks; }
    public String getPriceRange() { return priceRange; }
    public String getPriceUnit() { return priceUnit; }
    public Color getStatusColor() { return statusColor; }
    public String getStatusText() { return statusText; }
}
