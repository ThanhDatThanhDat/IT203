package SS11.bai4;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;

public class bai4 {

    public void searchPatientSafe(Connection conn, String patientName) {
        Statement stmt = null;
        ResultSet rs = null;

        try {
            if (patientName == null || patientName.trim().isEmpty()) {
                System.out.println("Tên bệnh nhân không hợp lệ.");
                return;
            }

            String safeName = patientName
                    .replace("'", "''")
                    .replace("--", "")
                    .replace(";", "");

            String sql = "SELECT * FROM Patients WHERE full_name = '" + safeName + "'";

            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);

            System.out.println("--- KẾT QUẢ TRA CỨU ---");
            while (rs.next()) {
                System.out.println("Bệnh nhân: " + rs.getString("full_name"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}