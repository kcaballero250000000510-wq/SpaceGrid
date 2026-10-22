package com.techhub.spacegrid.dao;

import com.techhub.spacegrid.config.DatabaseConnection;
import com.techhub.spacegrid.model.LeaseContract;
import java.sql.*;

public class LeaseContractDAO {

    public boolean createContract(LeaseContract contract) {
        String sql = "INSERT INTO lease_contracts (property_id, unit_id, renter_id, start_date, end_date, monthly_rate, status) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, contract.getPropertyId());
            pstmt.setInt(2, contract.getUnitId());
            pstmt.setInt(3, contract.getRenterId());
            pstmt.setDate(4, contract.getStartDate());
            pstmt.setDate(5, contract.getEndDate());
            pstmt.setDouble(6, contract.getMonthlyRate());
            pstmt.setString(7, contract.getStatus());
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}