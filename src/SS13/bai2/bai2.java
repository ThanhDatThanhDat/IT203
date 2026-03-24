package SS13.bai2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class bai2 {
    public static void main(String[] args) {
        Connection conn = null;
        PreparedStatement pstmtUpdateWallet = null;
        PreparedStatement pstmtUpdateInvoice = null;

        try {
            // 1. Kết nối DB
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Rikkei_Hospital", "root", "your_password");

            // 2. Bắt đầu Transaction
            conn.setAutoCommit(false);

            // Bước 1: Trừ tiền trong ví bệnh nhân
            String sqlWallet = "UPDATE Patient_Wallet SET balance = balance - ? WHERE patient_id = ?";
            pstmtUpdateWallet = conn.prepareStatement(sqlWallet);
            pstmtUpdateWallet.setDouble(1, 500000); // Số tiền viện phí
            pstmtUpdateWallet.setInt(2, 1);       // ID bệnh nhân
            pstmtUpdateWallet.executeUpdate();

            // Bước 2: Cập nhật trạng thái hóa đơn (Giả sử sai tên bảng 'Invoices_Error' để test lỗi)
            String sqlInvoice = "UPDATE Invoices SET status = 'PAID' WHERE patient_id = ?";
            pstmtUpdateInvoice = conn.prepareStatement(sqlInvoice);
            pstmtUpdateInvoice.setInt(1, 1);
            pstmtUpdateInvoice.executeUpdate();

            // 3. Nếu mọi thứ OK -> Xác nhận giao dịch
            conn.commit();
            System.out.println("Thanh toán viện phí thành công!");

        } catch (SQLException e) {
            // PHẦN SỬA LỖI QUAN TRỌNG NHẤT: Bổ sung Rollback
            if (conn != null) {
                try {
                    System.err.println("Phát hiện lỗi hệ thống: " + e.getMessage());
                    System.out.println("Đang thực hiện hoàn tác dữ liệu (Rollback)...");
                    conn.rollback(); // Đưa dữ liệu về trạng thái an toàn
                    System.out.println("Đã khôi phục trạng thái số dư ví ban đầu.");
                } catch (SQLException rollbackEx) {
                    // Bọc try-catch cho chính lệnh rollback như yêu cầu
                    rollbackEx.printStackTrace();
                }
            }
        } finally {
            // 4. Giải phóng tài nguyên
            try {
                if (pstmtUpdateWallet != null) pstmtUpdateWallet.close();
                if (pstmtUpdateInvoice != null) pstmtUpdateInvoice.close();
                if (conn != null) {
                    conn.setAutoCommit(true); // Trả lại chế độ mặc định
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}