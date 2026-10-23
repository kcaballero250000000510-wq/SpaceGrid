package com.techhub.spacegrid.dao;

import com.techhub.spacegrid.config.DatabaseConnection;
import com.techhub.spacegrid.model.Property;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PropertyDAO {

    // 1. Get all properties for a specific Landlord
    public List<Property> getPropertiesByLandlordId(int landlordId) {
        List<Property> properties = new ArrayList<>();
        String sql = "SELECT * FROM properties WHERE landlord_id = ? ORDER BY created_at DESC";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, landlordId);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    properties.add(mapResultSetToProperty(rs));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return properties;
    }

    // 2. Save new property
    public int saveProperty(Property property) {
        String sql = "INSERT INTO properties (landlord_id, name, address, property_type, description, " +
                     "has_exact_dimensions, size_description, total_area_sqm, grid_rows, grid_columns, status) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setInt(1, property.getLandlordId());
            pstmt.setString(2, property.getName());
            pstmt.setString(3, property.getAddress());
            pstmt.setString(4, property.getPropertyType());
            pstmt.setString(5, property.getDescription());
            pstmt.setBoolean(6, property.isHasExactDimensions());
            pstmt.setString(7, property.getSizeDescription());
            pstmt.setDouble(8, property.getTotalAreaSqm());
            pstmt.setInt(9, property.getGridRows());
            pstmt.setInt(10, property.getGridColumns());
            pstmt.setString(11, property.getStatus() != null ? property.getStatus() : "ACTIVE");

            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        return generatedKeys.getInt(1);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public boolean updateProperty(Property property) {
    String sql = "UPDATE properties SET name = ?, property_type = ?, address = ?, description = ? WHERE id = ?";

    try (Connection conn = DatabaseConnection.getConnection();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {

        pstmt.setString(1, property.getName());
        pstmt.setString(2, property.getPropertyType());
        pstmt.setString(3, property.getAddress());
        pstmt.setString(4, property.getDescription());
        pstmt.setInt(5, property.getId());

        return pstmt.executeUpdate() > 0;
    } catch (SQLException e) {
        e.printStackTrace();
        return false;
    }
}
    // Helper method mapping ResultSet columns to Property Object
    private Property mapResultSetToProperty(ResultSet rs) throws SQLException {
        Property property = new Property();
        property.setId(rs.getInt("id"));
        property.setLandlordId(rs.getInt("landlord_id"));
        property.setName(rs.getString("name"));
        property.setAddress(rs.getString("address"));
        property.setPropertyType(rs.getString("property_type"));
        property.setDescription(rs.getString("description"));
        property.setHasExactDimensions(rs.getBoolean("has_exact_dimensions"));
        property.setSizeDescription(rs.getString("size_description"));
        property.setTotalAreaSqm(rs.getDouble("total_area_sqm"));
        property.setGridRows(rs.getInt("grid_rows"));
        property.setGridColumns(rs.getInt("grid_columns"));
        property.setStatus(rs.getString("status"));
        return property;
    }
}