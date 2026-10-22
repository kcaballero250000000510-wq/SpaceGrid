package com.techhub.spacegrid.dao;

import com.techhub.spacegrid.config.DatabaseConnection;
import com.techhub.spacegrid.model.Property;
import java.sql.*;

public class PropertyDAO {

    public int saveProperty(Property property) {
        String sql = "INSERT INTO properties (owner_id, property_name, has_exact_dimensions, "
                   + "size_description, total_area_sqm, grid_rows, grid_columns, status) "
                   + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setInt(1, property.getOwnerId());
            pstmt.setString(2, property.getPropertyName());
            pstmt.setBoolean(3, property.isHasExactDimensions());
            pstmt.setString(4, property.getSizeDescription());
            pstmt.setDouble(5, property.getTotalAreaSqm());
            pstmt.setInt(6, property.getGridRows());
            pstmt.setInt(7, property.getGridColumns());
            pstmt.setString(8, property.getStatus());

            int affectedRows = pstmt.executeUpdate();

            if (affectedRows > 0) {
                try (ResultSet rs = pstmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        int generatedId = rs.getInt(1);
                        property.setId(generatedId);
                        return generatedId;
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1; // Return -1 if insertion failed
    }
}