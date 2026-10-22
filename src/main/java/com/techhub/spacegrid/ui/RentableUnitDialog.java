package com.techhub.spacegrid.ui;

import javax.swing.*;
import java.awt.*;

/**
 * Dialog for managing rentable units for a specific property.
 */
public class RentableUnitDialog extends javax.swing.JDialog {

    private final int propertyId;

    /**
     * Default constructor for NetBeans design view.
     */
    public RentableUnitDialog() {
        this(null, true, 0);
    }

    /**
     * Parameterized constructor called from PropertyGridFrame.
     */
    public RentableUnitDialog(java.awt.Frame parent, boolean modal, int propertyId) {
        super(parent, modal);
        this.propertyId = propertyId;
        
        initComponents();
        setTitle("Manage Rentable Units - Property ID: " + propertyId);
        setLocationRelativeTo(parent);
    }

    private void initComponents() {
        // Basic dialog window setup
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setSize(600, 400);
        
        JLabel lblPlaceholder = new JLabel("Managing units for Property #" + propertyId, SwingConstants.CENTER);
        lblPlaceholder.setFont(new Font("Segoe UI", Font.BOLD, 14));
        
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(lblPlaceholder, BorderLayout.CENTER);
    }
}