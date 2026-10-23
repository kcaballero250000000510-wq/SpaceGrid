package com.techhub.spacegrid;

import com.formdev.flatlaf.FlatDarkLaf;
import com.techhub.spacegrid.config.DatabaseConnection;
import java.sql.Connection;
import java.sql.SQLException;
import com.techhub.spacegrid.dao.PropertyDAO;
import com.techhub.spacegrid.model.Property;
import com.techhub.spacegrid.ui.MainDashboardFrame;
import com.techhub.spacegrid.ui.SpaceGridHomepage;
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
           SpaceGridHomepage loginFrame = new SpaceGridHomepage();
           loginFrame.setVisible(true);
       });
       
//       SwingUtilities.invokeLater(() -> {
//           MainDashboardFrame dashboard = new MainDashboardFrame();
//           dashboard.setLocationRelativeTo(null); // Center window on screen
//           dashboard.setVisible(true);
//       });




       
    }
}