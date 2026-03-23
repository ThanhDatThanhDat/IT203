package SS12.bai2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class bai2 {
    public void updateVitalSigns(Connection conn, double temperature, int heartRate, int patientId) throws SQLException {
        String sql = "UPDATE PatientVitals SET temperature = ?, heart_rate = ? WHERE patient_id = ?";

        PreparedStatement pstmt = conn.prepareStatement(sql);

        pstmt.setDouble(1, temperature);
        pstmt.setInt(2, heartRate);
        pstmt.setInt(3, patientId);

        int rowsAffected = pstmt.executeUpdate();

        if (rowsAffected > 0) {
            System.out.println("Cập nhật chỉ số sinh tồn thành công!");
        }
    }
}