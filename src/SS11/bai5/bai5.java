package SS11.bai5;

import java.sql.*;
import java.util.Scanner;

public class bai5 {
    static class Doctor {
        private String id, name, specialty;

        public Doctor(String id, String name, String specialty) {
            this.id = id;
            this.name = name;
            this.specialty = specialty;
        }

        public String getId() { return id; }
        public String getName() { return name; }
        public String getSpecialty() { return specialty; }
    }

    static class DoctorDAO {
        private Connection conn;

        public DoctorDAO(Connection conn) { this.conn = conn; }

        public void displayAll() throws SQLException {
            String sql = "SELECT * FROM Doctors";
            try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
                System.out.println("\n[DANH SÁCH BÁC SĨ]");
                System.out.printf("%-10s | %-25s | %-20s\n", "Mã số", "Họ tên", "Chuyên khoa");
                while (rs.next()) {
                    System.out.printf("%-10s | %-25s | %-20s\n",
                            rs.getString("doctor_id"), rs.getString("full_name"), rs.getString("specialty"));
                }
            }
        }

        public void insert(Doctor doc) throws SQLException {
            String sql = "INSERT INTO Doctors (doctor_id, full_name, specialty) VALUES (?, ?, ?)";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, doc.getId());
                pstmt.setString(2, doc.getName());
                pstmt.setString(3, doc.getSpecialty());
                pstmt.executeUpdate();
            }
        }

        public void showStats() throws SQLException {
            String sql = "SELECT specialty, COUNT(*) as count FROM Doctors GROUP BY specialty";
            try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
                System.out.println("\n[THỐNG KÊ CHUYÊN KHOA]");
                while (rs.next()) {
                    System.out.println(rs.getString("specialty") + ": " + rs.getInt("count") + " bác sĩ");
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String url = "jdbc:mysql://localhost:3306/Hospital_DB";
        String user = "root";
        String pass = "";

        try (Connection conn = DriverManager.getConnection(url, user, pass)) {
            DoctorDAO dao = new DoctorDAO(conn);

            while (true) {
                System.out.println("\n--- RIKKEI-CARE HOSPITAL MANAGEMENT ---");
                System.out.println("1. Xem danh sách bác sĩ");
                System.out.println("2. Thêm bác sĩ mới");
                System.out.println("3. Thống kê chuyên khoa");
                System.out.println("4. Thoát");
                System.out.print("Lựa chọn: ");

                try {
                    int choice = Integer.parseInt(sc.nextLine());
                    switch (choice) {
                        case 1 -> dao.displayAll();
                        case 2 -> {
                            System.out.print("Mã BS: "); String id = sc.nextLine();
                            System.out.print("Họ tên: "); String name = sc.nextLine();
                            System.out.print("Chuyên khoa: "); String spec = sc.nextLine();
                            dao.insert(new Doctor(id, name, spec));
                            System.out.println("=> Đã thêm thành công!");
                        }
                        case 3 -> dao.showStats();
                        case 4 -> { System.out.println("Kết thúc chương trình."); return; }
                        default -> System.out.println("Lỗi: Chọn từ 1 đến 4.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Lỗi: Vui lòng nhập số.");
                } catch (SQLException e) {
                    System.out.println("Lỗi SQL: " + e.getMessage());
                }
            }
        } catch (SQLException e) {
            System.out.println("Không thể kết nối Database. Kiểm tra URL/User/Pass.");
        }
    }
}