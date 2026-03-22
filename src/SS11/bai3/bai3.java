package SS11.bai3;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class bai3 {
    public void updateBedStatus(Connection conn, String inputId) {
        String sql = "UPDATE Beds SET bed_status = 'Occupied' WHERE bed_id = " + inputId;
        PreparedStatement pstmt = null;

        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, inputId);

            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Thành công: Đã cập nhật giường bệnh " + inputId + " thành 'Đang sử dụng'.");
            } else {
                System.out.println("Lỗi: Không tìm thấy giường có mã " + inputId + ". Vui lòng kiểm tra lại!");
            }

        } catch (SQLException e) {
            System.out.println("Lỗi hệ thống khi cập nhật dữ liệu.");
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null) pstmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}