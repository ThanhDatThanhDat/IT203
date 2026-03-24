package SS13.bai1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class bai1 {
    public static void main(String[] args) {
        Connection conn = null;
        try {
            // 1. Kết nối Database
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Rikkei_Hospital", "root", "your_password");

            // 2. TẮT Auto-Commit (Bắt đầu Transaction thủ công)
            conn.setAutoCommit(false);

            // 3. Thao tác 1: Trừ thuốc trong kho
            String sqlUpdate = "UPDATE Medicine_Inventory SET quantity = quantity - 1 WHERE medicine_id = ?";
            PreparedStatement pstmtUpdate = conn.prepareStatement(sqlUpdate);
            pstmtUpdate.setInt(1, 101); // Giả sử ID thuốc là 101
            pstmtUpdate.executeUpdate();

            // 4. Thao tác 2: Lưu lịch sử (Giả sử dòng này bị lỗi do mạng)
            String sqlInsert = "INSERT INTO Prescription_History (patient_id, medicine_id, date) VALUES (?, ?, NOW())";
            PreparedStatement pstmtInsert = conn.prepareStatement(sqlInsert);
            pstmtInsert.setInt(1, 501); // Giả sử ID bệnh nhân là 501
            pstmtInsert.setInt(2, 101);
            pstmtInsert.executeUpdate();

            // 5. Nếu mọi thứ chạy đến đây mà không lỗi -> COMMIT toàn bộ
            conn.commit();
            System.out.println("Giao dịch thành công!");

        } catch (SQLException e) {
            // 6. Nếu có bất kỳ lỗi nào xảy ra -> ROLLBACK (Hủy bỏ mọi thay đổi)
            if (conn != null) {
                try {
                    conn.rollback();
                    System.err.println("Đã xảy ra lỗi. Hệ thống đã hoàn tác dữ liệu (Rollback).");
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            e.printStackTrace();
        } finally {
            // 7. Giải phóng tài nguyên
            try {
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}