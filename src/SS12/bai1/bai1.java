package SS12.bai1;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class bai1 {
    public void loginDoctor(Connection conn, String code, String pass) {
        String sql = "SELECT * FROM Doctors WHERE code = ? AND pass = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, code);
            pstmt.setString(2, pass);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    System.out.println("Đăng nhập thành công! Chào bác sĩ: " + rs.getString("name"));
                } else {
                    System.out.println("Sai mã số hoặc mật khẩu.");
                }
            }

        } catch (SQLException e) {
            System.err.println("Lỗi kết nối cơ sở dữ liệu: " + e.getMessage());
        }
    }
}