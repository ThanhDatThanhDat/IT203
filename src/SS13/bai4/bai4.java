package SS13.bai4;

import java.sql.*;
import java.util.*;

// Giả sử đã có lớp BenhNhanDTO và DichVu đơn giản
class DichVu {
    int id; String tenDV;
    public DichVu(int id, String ten) { this.id = id; this.tenDV = ten; }
}

class BenhNhanDTO {
    int maBN; String tenBN; List<DichVu> dsDichVu = new ArrayList<>();
    public BenhNhanDTO(int id, String ten) { this.maBN = id; this.tenBN = ten; }
}

public class bai4 {
    public List<BenhNhanDTO> getEmergencyDashboard() {
        // Sử dụng LinkedHashMap để giữ đúng thứ tự bệnh nhân lấy từ DB ra
        Map<Integer, BenhNhanDTO> map = new LinkedHashMap<>();

        String sql = "SELECT b.maBenhNhan, b.hoTen, d.id AS maDV, d.tenDichVu " +
                "FROM BenhNhan b " +
                "LEFT JOIN DichVuSuDung d ON b.maBenhNhan = d.maBenhNhan " +
                "ORDER BY b.maBenhNhan"; // Sắp xếp để dữ liệu gom nhóm tốt hơn

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Rikkei_Hospital", "root", "password");
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                int maBN = rs.getInt("maBenhNhan");

                // Kiểm tra xem bệnh nhân đã tồn tại trong Map chưa
                BenhNhanDTO bn = map.get(maBN);
                if (bn == null) {
                    bn = new BenhNhanDTO(maBN, rs.getString("hoTen"));
                    map.put(maBN, bn);
                }

                // XỬ LÝ BẪY 2: Bệnh nhân mới nhập viện chưa có dịch vụ
                // Vì dùng LEFT JOIN nên maDV sẽ là NULL/0 nếu không có bản ghi bên bảng DichVu
                int maDV = rs.getInt("maDV");
                if (maDV != 0) { // rs.getInt trả về 0 nếu giá trị trong DB là NULL
                    DichVu dv = new DichVu(maDV, rs.getString("tenDichVu"));
                    bn.dsDichVu.add(dv);
                }
                // Nếu maDV == 0, danh sách dsDichVu vẫn là ArrayList trống (không gây NullPointerException)
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return new ArrayList<>(map.values());
    }
}