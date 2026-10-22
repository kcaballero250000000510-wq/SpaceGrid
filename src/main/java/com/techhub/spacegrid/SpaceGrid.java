package com.techhub.spacegrid;

import com.formdev.flatlaf.FlatDarkLaf;
import com.techhub.spacegrid.config.DatabaseConnection;
import java.sql.Connection;
import java.sql.SQLException;
import com.techhub.spacegrid.dao.PropertyDAO;
import com.techhub.spacegrid.model.Property;
import com.techhub.spacegrid.ui.MainDashboardFrame;
import com.techhub.spacegrid.ui.LoginRegisterFrame;
import javax.swing.SwingUtilities;

public class SpaceGrid {

    public static void main(String[] args) {
        // Activate FlatLaf Dark Theme
        FlatDarkLaf.setup();

        System.out.println("Initializing SpaceGrid...");

        // Test Database Connection
        try (Connection conn = DatabaseConnection.getConnection()) {
            if (conn != null && !conn.isClosed()) {
                System.out.println("SUCCESS: Connected to MySQL database successfully!");
            }
        } catch (SQLException e) {
            System.err.println("FAILED: Could not connect to MySQL database.");
            e.printStackTrace();
        }
        
       
       SwingUtilities.invokeLater(() -> {
           LoginRegisterFrame loginFrame = new LoginRegisterFrame();
           loginFrame.setVisible(true);
       });
       
       SwingUtilities.invokeLater(() -> {
           MainDashboardFrame dashboard = new MainDashboardFrame();
           dashboard.setLocationRelativeTo(null); // Center window on screen
           dashboard.setVisible(true);
       });
       
       System.out.println("Testing Property Persistence...");

       // 1. Create dummy Property model
       Property testProperty = new Property("Davao Commercial Center", 10, 10);
       testProperty.setDimensions(500.0); // 500 sqm

       // 2. Save via DAO
       PropertyDAO propertyDAO = new PropertyDAO();
       int createdId = propertyDAO.saveProperty(testProperty);

       if (createdId != -1) {
           System.out.println("SUCCESS: Created Property in MySQL with ID: " + createdId);
       } else {
           System.out.println("FAILED: Could not save property.");
       }
    }
}