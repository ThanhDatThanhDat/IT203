package SS12.bai3;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;

public class bai3 {
    public void getSurgeryFee(Connection conn, int surgeryId) {
        String sql = "{call GET_SURGERY_FEE(?, ?)}";

        try (CallableStatement cstmt = conn.prepareCall(sql)) {

            cstmt.setInt(1, surgeryId);

            cstmt.registerOutParameter(2, Types.DECIMAL);

            cstmt.execute();

            double totalCost = cstmt.getDouble(2);

            System.out.println("Chi phí phẫu thuật cho mã " + surgeryId + " là: " + totalCost + " VND");

        } catch (SQLException e) {
            System.err.println("Lỗi khi gọi Stored Procedure: " + e.getMessage());
        }
    }
}