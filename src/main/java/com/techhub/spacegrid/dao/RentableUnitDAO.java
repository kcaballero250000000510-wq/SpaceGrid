package com.techhub.spacegrid.dao;

import com.techhub.spacegrid.config.DatabaseConnection;
import com.techhub.spacegrid.model.RentableUnit;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RentableUnitDAO {

    public List<RentableUnit> getUnitsByPropertyId(int propertyId) {
        List<RentableUnit> units = new ArrayList<>();
        String sql = "SELECT * FROM rentable_units WHERE property_id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, propertyId);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    RentableUnit unit = new RentableUnit();
                    unit.setId(rs.getInt("id"));
                    unit.setPropertyId(rs.getInt("property_id"));
                    unit.setUnitName(rs.getString("unit_name"));
                    unit.setRentalRate(rs.getBigDecimal("rental_rate"));
                    unit.setStatus(rs.getString("status"));
                    units.add(unit);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return units;
    }
}