/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.techhub.spacegrid.ui;

import com.techhub.spacegrid.dao.PropertyDAO;
import com.techhub.spacegrid.model.Property;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author kit jasper
 */
public class PropertyGridFrame extends javax.swing.JFrame {

    private final PropertyDAO propertyDAO = new PropertyDAO();
    private List<Property> allProperties;
    private int currentLandlordId = 1;
    /**
     * Creates new form PropertyGridFrame
     */
public PropertyGridFrame() {
        initComponents();
        setLocationRelativeTo(null);
        setupCustomLogic();
        loadPropertiesFromDatabase();
    }

    /**
     * Overloaded constructor passed from LoginRegisterFrame upon login
     */
    public PropertyGridFrame(int landlordId) {
        this.currentLandlordId = landlordId;
        initComponents();
        setLocationRelativeTo(null);
        setupCustomLogic();
        loadPropertiesFromDatabase();
    }

    private void setupCustomLogic() {
        // Real-time search filter listener
        txtSearch.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                filterAndRender();
            }
        });

        // Add Property button action handler
        btnAddProperty.addActionListener(e -> {
            PropertyFormDialog dialog = new PropertyFormDialog(this, true, null);
            dialog.setVisible(true);
            loadPropertiesFromDatabase(); // Refresh cards after form closes
        });
    }

public void loadPropertiesFromDatabase() {
        // Fetch properties strictly belonging to the currently logged-in landlord
        allProperties = propertyDAO.getPropertiesByLandlordId(currentLandlordId);
        filterAndRender();
    }

    private void filterAndRender() {
        gridContainer.removeAll();

        if (allProperties == null) {
            gridContainer.revalidate();
            gridContainer.repaint();
            return;
        }

        String query = txtSearch.getText().trim().toLowerCase();
        if (query.equals("search properties...")) {
            query = "";
        }

        String selectedType = cmbPropertyType.getSelectedItem() != null 
                ? cmbPropertyType.getSelectedItem().toString() 
                : "All Types";

        final String searchQuery = query;
        List<Property> filtered = allProperties.stream()
            .filter(p -> (selectedType.equals("All Types") || p.getPropertyType().equalsIgnoreCase(selectedType)))
            .filter(p -> p.getName().toLowerCase().contains(searchQuery) || p.getAddress().toLowerCase().contains(searchQuery))
            .collect(Collectors.toList());

        if (filtered.isEmpty()) {
            JLabel emptyLabel = new JLabel("No properties found.", SwingConstants.CENTER);
            emptyLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
            emptyLabel.setForeground(Color.GRAY);
            gridContainer.add(emptyLabel);
        } else {
            for (Property prop : filtered) {
                gridContainer.add(createPropertyCard(prop));
            }
        }

        gridContainer.revalidate();
        gridContainer.repaint();
    }

    private JPanel createPropertyCard(Property prop) {
        JPanel card = new JPanel(new BorderLayout(8, 8));
        card.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(220, 225, 230), 1),
            BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));
        card.setBackground(Color.WHITE);

        // 1. Dynamic Image Thumbnail
        ImageIcon propIcon = ImageUtils.getPropertyImage(prop.getId(), 220, 130);
        JLabel imgLabel = new JLabel(propIcon, SwingConstants.CENTER);

        // 2. Info Section
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        infoPanel.setOpaque(false);

        JLabel titleLabel = new JLabel(prop.getName());
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));

        JLabel typeLabel = new JLabel(prop.getPropertyType());
        typeLabel.setFont(new Font("Segoe UI", Font.ITALIC, 11));
        typeLabel.setForeground(new Color(100, 116, 139));

        JLabel addressLabel = new JLabel("<html><body style='width: 170px;'>" + prop.getAddress() + "</body></html>");
        addressLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));

        infoPanel.add(titleLabel);
        infoPanel.add(typeLabel);
        infoPanel.add(Box.createVerticalStrut(5));
        infoPanel.add(addressLabel);

        // 3. Actions Panel
        JPanel actionPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 0));
        actionPanel.setOpaque(false);

        JButton btnUnits = new JButton("Units");
        JButton btnEdit = new JButton("Edit");

        btnUnits.addActionListener(e -> {
            RentableUnitDialog dialog = new RentableUnitDialog(this, true, prop.getId());
            dialog.setVisible(true);
        });

        btnEdit.addActionListener(e -> {
            PropertyFormDialog dialog = new PropertyFormDialog(this, true, prop);
            dialog.setVisible(true);
            loadPropertiesFromDatabase();
        });

        actionPanel.add(btnUnits);
        actionPanel.add(btnEdit);

        card.add(imgLabel, BorderLayout.NORTH);
        card.add(infoPanel, BorderLayout.CENTER);
        card.add(actionPanel, BorderLayout.SOUTH);

        return card;
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtSearch = new javax.swing.JTextField();
        cmbPropertyType = new javax.swing.JComboBox<>();
        btnAddProperty = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        gridContainer = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("SpaceGrid Land Owner Dashboard");

        txtSearch.setText("Search Properties...");
        txtSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSearchActionPerformed(evt);
            }
        });

        cmbPropertyType.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "All Types", "Apartment", "Condominium", "Townhouse", "Commercial" }));
        cmbPropertyType.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbPropertyTypeActionPerformed(evt);
            }
        });

        btnAddProperty.setText("+ Add Property");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 431, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmbPropertyType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnAddProperty)))
                .addContainerGap(305, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addComponent(jLabel1)
                .addGap(6, 6, 6)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbPropertyType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAddProperty))
                .addGap(3, 3, 3))
        );

        gridContainer.setBackground(new java.awt.Color(255, 255, 255));
        gridContainer.setLayout(new java.awt.GridLayout(0, 3, 15, 15));
        jScrollPane1.setViewportView(gridContainer);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 455, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cmbPropertyTypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbPropertyTypeActionPerformed
        // TODO add your handling code here:
        filterAndRender();
    }//GEN-LAST:event_cmbPropertyTypeActionPerformed

    private void txtSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSearchActionPerformed
        // TODO add your handling code here:
        filterAndRender();
    }//GEN-LAST:event_txtSearchActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(PropertyGridFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PropertyGridFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PropertyGridFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PropertyGridFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PropertyGridFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddProperty;
    private javax.swing.JComboBox<String> cmbPropertyType;
    private javax.swing.JPanel gridContainer;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField txtSearch;
    // End of variables declaration//GEN-END:variables
}
