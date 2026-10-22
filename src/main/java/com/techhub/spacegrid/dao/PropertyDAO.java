package com.techhub.spacegrid.dao;

import com.techhub.spacegrid.config.DatabaseConnection;
import com.techhub.spacegrid.model.Property;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PropertyDAO {

    // Fetch all properties for PropertyGridFrame dashboard
    public List<Property> getAllProperties() {
        List<Property> list = new ArrayList<>();
        String sql = "SELECT * FROM properties";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                Property p = new Property();
                
                // Try reading PK column (supports either 'property_id' or 'id')
                try {
                    p.setId(rs.getInt("property_id"));
                } catch (SQLException e) {
                    p.setId(rs.getInt("id"));
                }

                p.setOwnerId(rs.getInt("owner_id"));
                p.setPropertyName(rs.getString("property_name"));
                
                // Optional columns with fallback defaults if null
                try { p.setPropertyType(rs.getString("property_type")); } catch (SQLException ignored) {}
                try { p.setAddress(rs.getString("address")); } catch (SQLException ignored) {}
                try { p.setDescription(rs.getString("description")); } catch (SQLException ignored) {}

                p.setGridRows(rs.getInt("grid_rows"));
                p.setGridColumns(rs.getInt("grid_columns"));
                p.setStatus(rs.getString("status"));

                list.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    // Original saveProperty method
    public int saveProperty(Property property) {
        String sql = "INSERT INTO properties (owner_id, property_name, property_type, address, description, "
                   + "has_exact_dimensions, size_description, total_area_sqm, grid_rows, grid_columns, status) "
                   + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setInt(1, property.getOwnerId());
            pstmt.setString(2, property.getPropertyName());
            pstmt.setString(3, property.getPropertyType());
            pstmt.setString(4, property.getAddress());
            pstmt.setString(5, property.getDescription());
            pstmt.setBoolean(6, property.isHasExactDimensions());
            pstmt.setString(7, property.getSizeDescription());
            pstmt.setDouble(8, property.getTotalAreaSqm());
            pstmt.setInt(9, property.getGridRows());
            pstmt.setInt(10, property.getGridColumns());
            pstmt.setString(11, property.getStatus());

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

    // Wrapper boolean method expected by PropertyFormDialog
    public boolean addProperty(Property property) {
        return saveProperty(property) > 0;
    }

    // Update existing property record
    public boolean updateProperty(Property property) {
        String sql = "UPDATE properties SET property_name = ?, property_type = ?, address = ?, description = ? WHERE property_id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, property.getPropertyName());
            pstmt.setString(2, property.getPropertyType());
            pstmt.setString(3, property.getAddress());
            pstmt.setString(4, property.getDescription());
            pstmt.setInt(5, property.getId());

            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            // Fallback for schema using 'id' instead of 'property_id'
            String fallbackSql = "UPDATE properties SET property_name = ?, property_type = ?, address = ?, description = ? WHERE id = ?";
            try (Connection conn = DatabaseConnection.getConnection();
                 PreparedStatement pstmt = conn.prepareStatement(fallbackSql)) {

                pstmt.setString(1, property.getPropertyName());
                pstmt.setString(2, property.getPropertyType());
                pstmt.setString(3, property.getAddress());
                pstmt.setString(4, property.getDescription());
                pstmt.setInt(5, property.getId());

                return pstmt.executeUpdate() > 0;
            } catch (SQLException ex) {
                ex.printStackTrace();
                return false;
            }
        }
    }
}