package com.techhub.spacegrid.ui;

import com.techhub.spacegrid.dao.PropertyDAO;
import com.techhub.spacegrid.model.Property;

import javax.swing.*;
import java.awt.*;

/**
 * Dialog form for creating or editing property records in spacegrid_db.
 * @author kit jasper
 */
public class PropertyFormDialog extends javax.swing.JDialog {

    private final Property propertyToEdit;
    private final PropertyDAO propertyDAO = new PropertyDAO();

    /**
     * Creates new form PropertyFormDialog as a modal dialog.
     */
    public PropertyFormDialog(java.awt.Frame parent, boolean modal, Property property) {
        super(parent, modal);
        this.propertyToEdit = property;
        
        initComponents();
        setLocationRelativeTo(parent);

        if (propertyToEdit != null) {
            setTitle("Edit Property - " + propertyToEdit.getName());
            populateFields();
        } else {
            setTitle("Add New Property");
        }
    }

    private void populateFields() {
        txtName.setText(propertyToEdit.getName());
        cmbType.setSelectedItem(propertyToEdit.getPropertyType());
        txtAddress.setText(propertyToEdit.getAddress());
        txtDescription.setText(propertyToEdit.getDescription());
    }

    private void saveProperty() {
        String name = txtName.getText().trim();
        String type = (String) cmbType.getSelectedItem();
        String address = txtAddress.getText().trim();
        String description = txtDescription.getText().trim();

        if (name.isEmpty() || address.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Name and Address cannot be empty.", "Validation Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (propertyToEdit == null) {
            // Create new property
            Property newProperty = new Property();
            newProperty.setName(name);
            newProperty.setPropertyType(type);
            newProperty.setAddress(address);
            newProperty.setDescription(description);

            boolean success = propertyDAO.addProperty(newProperty);
            if (success) {
                JOptionPane.showMessageDialog(this, "Property added successfully!");
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Failed to add property. Check database connection.", "Database Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            // Update existing property
            propertyToEdit.setName(name);
            propertyToEdit.setPropertyType(type);
            propertyToEdit.setAddress(address);
            propertyToEdit.setDescription(description);

            boolean success = propertyDAO.updateProperty(propertyToEdit);
            if (success) {
                JOptionPane.showMessageDialog(this, "Property updated successfully!");
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Failed to update property.", "Database Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {

        lblTitle = new JLabel();
        lblName = new JLabel("Property Name:");
        txtName = new JTextField();
        lblType = new JLabel("Property Type:");
        cmbType = new JComboBox<>(new String[] { "Apartment", "Condominium", "Townhouse", "Commercial" });
        lblAddress = new JLabel("Address:");
        jScrollPaneAddress = new JScrollPane();
        txtAddress = new JTextArea();
        lblDescription = new JLabel("Description:");
        jScrollPaneDesc = new JScrollPane();
        txtDescription = new JTextArea();
        btnSave = new JButton("Save");
        btnCancel = new JButton("Cancel");

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 16));
        lblTitle.setText(propertyToEdit == null ? "Add New Property" : "Edit Property");

        txtAddress.setRows(3);
        txtAddress.setLineWrap(true);
        jScrollPaneAddress.setViewportView(txtAddress);

        txtDescription.setRows(3);
        txtDescription.setLineWrap(true);
        jScrollPaneDesc.setViewportView(txtDescription);

        btnSave.setBackground(new Color(37, 99, 235));
        btnSave.setForeground(Color.WHITE);
        btnSave.addActionListener(e -> saveProperty());

        btnCancel.addActionListener(e -> dispose());

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblTitle)
                    .addComponent(lblName)
                    .addComponent(txtName)
                    .addComponent(lblType)
                    .addComponent(cmbType, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblAddress)
                    .addComponent(jScrollPaneAddress, GroupLayout.DEFAULT_SIZE, 350, Short.MAX_VALUE)
                    .addComponent(lblDescription)
                    .addComponent(jScrollPaneDesc)
                    .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btnCancel, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSave, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(lblTitle)
                .addGap(15, 15, 15)
                .addComponent(lblName)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtName, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(lblType)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmbType, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(lblAddress)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPaneAddress, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(lblDescription)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPaneDesc, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSave, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCancel, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        pack();
    }

    // Variables declaration
    private JButton btnCancel;
    private JButton btnSave;
    private JComboBox<String> cmbType;
    private JScrollPane jScrollPaneAddress;
    private JScrollPane jScrollPaneDesc;
    private JLabel lblAddress;
    private JLabel lblDescription;
    private JLabel lblName;
    private JLabel lblTitle;
    private JLabel lblType;
    private JTextArea txtAddress;
    private JTextArea txtDescription;
    private JTextField txtName;
}