package SS13.bai3;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class bai3 {

    public void xuatVienVaThanhToan(int maBenhNhan, double tienVienPhi) {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Rikkei_Hospital", "root", "your_password");

            // Bắt đầu Transaction
            conn.setAutoCommit(false);

            // --- XỬ LÝ BẪY 1: Kiểm tra số dư nghiệp vụ ---
            String sqlCheckBalance = "SELECT balance FROM Patient_Wallet WHERE patient_id = ?";
            PreparedStatement pstmtCheck = conn.prepareStatement(sqlCheckBalance);
            pstmtCheck.setInt(1, maBenhNhan);
            ResultSet rs = pstmtCheck.executeQuery();

            if (rs.next()) {
                double soDuHienTai = rs.getDouble("balance");
                if (soDuHienTai < tienVienPhi) {
                    // Chủ động ném lỗi nếu không đủ tiền (Bẫy 1)
                    throw new Exception("Lỗi nghiệp vụ: Số dư tạm ứng không đủ để thanh toán viện phí!");
                }
            } else {
                throw new Exception("Lỗi: Không tìm thấy ví của bệnh nhân có mã " + maBenhNhan);
            }

            // --- BƯỚC 1: Trừ tiền viện phí ---
            String sqlUpdateWallet = "UPDATE Patient_Wallet SET balance = balance - ? WHERE patient_id = ?";
            PreparedStatement pstmt1 = conn.prepareStatement(sqlUpdateWallet);
            pstmt1.setDouble(1, tienVienPhi);
            pstmt1.setInt(2, maBenhNhan);
            int rows1 = pstmt1.executeUpdate();
            // Xử lý Bẫy 2: Kiểm tra dòng bị ảnh hưởng
            if (rows1 == 0) throw new Exception("Lỗi: Cập nhật ví thất bại (Mã bệnh nhân không tồn tại)");

            // --- BƯỚC 2: Giải phóng giường bệnh ---
            String sqlUpdateBed = "UPDATE Beds SET status = 'Empty' WHERE patient_id = ?";
            PreparedStatement pstmt2 = conn.prepareStatement(sqlUpdateBed);
            pstmt2.setInt(1, maBenhNhan);
            int rows2 = pstmt2.executeUpdate();
            // Xử lý Bẫy 2: Nếu giường không tìm thấy
            if (rows2 == 0) throw new Exception("Lỗi: Không tìm thấy giường bệnh đang gán cho bệnh nhân này");

            // --- BƯỚC 3: Cập nhật trạng thái bệnh nhân ---
            String sqlUpdatePatient = "UPDATE Patients SET status = 'Discharged' WHERE id = ?";
            PreparedStatement pstmt3 = conn.prepareStatement(sqlUpdatePatient);
            pstmt3.setInt(1, maBenhNhan);
            int rows3 = pstmt3.executeUpdate();
            // Xử lý Bẫy 2
            if (rows3 == 0) throw new Exception("Lỗi: Cập nhật trạng thái xuất viện thất bại");

            // --- HOÀN TẤT: Nếu chạy đến đây nghĩa là không có lỗi ---
            conn.commit();
            System.out.println("Chúc mừng! Bệnh nhân " + maBenhNhan + " đã xuất viện và thanh toán thành công.");

        } catch (Exception e) {
            // XỬ LÝ ROLLBACK: Khi gặp bất kỳ Exception nào (lỗi SQL hoặc lỗi chủ động ném ra)
            if (conn != null) {
                try {
                    conn.rollback();
                    System.err.println("Giao dịch bị hủy bỏ (Rollback). Lý do: " + e.getMessage());
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        } finally {
            // Đóng kết nối
            try {
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}