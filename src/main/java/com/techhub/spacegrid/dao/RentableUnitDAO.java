package com.techhub.spacegrid.dao;

import com.techhub.spacegrid.config.DatabaseConnection;
import com.techhub.spacegrid.model.RentableUnit;
import java.sql.*;

public class RentableUnitDAO {

    public boolean saveRentableUnit(RentableUnit unit) {
        String sql = "INSERT INTO rentable_units (property_id, unit_identifier, base_price, "
                   + "includes_electricity, includes_water, has_exact_dimensions, "
                   + "unit_size_description, total_area_sqm, status) "
                   + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setInt(1, unit.getPropertyId());
            pstmt.setString(2, unit.getUnitIdentifier());
            pstmt.setDouble(3, unit.getBasePrice());
            pstmt.setBoolean(4, unit.isIncludesElectricity());
            pstmt.setBoolean(5, unit.isIncludesWater());
            pstmt.setBoolean(6, unit.isHasExactDimensions());
            pstmt.setString(7, unit.getUnitSizeDescription());
            pstmt.setDouble(8, unit.getTotalAreaSqm());
            pstmt.setString(9, unit.getStatus());

            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                try (ResultSet rs = pstmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        unit.setId(rs.getInt(1));
                    }
                }
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}