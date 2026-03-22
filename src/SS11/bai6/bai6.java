package SS11.bai6;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class bai6 {
    static class DatabaseConnection {
        private static final String URL = "jdbc:mysql://localhost:3306/MedicalAppointmentDB";
        private static final String USER = "root";
        private static final String PASS = "";

        public static Connection getConnection() throws SQLException {
            return DriverManager.getConnection(URL, USER, PASS);
        }
    }

    static class Appointment {
        private int id;
        private String patientName;
        private Date appointmentDate;
        private String doctorName;
        private String status;

        public Appointment() {}
        public Appointment(String patientName, Date appointmentDate, String doctorName, String status) {
            this.patientName = patientName;
            this.appointmentDate = appointmentDate;
            this.doctorName = doctorName;
            this.status = status;
        }

        public int getId() { return id; }
        public void setId(int id) { this.id = id; }
        public String getPatientName() { return patientName; }
        public void setPatientName(String patientName) { this.patientName = patientName; }
        public Date getAppointmentDate() { return appointmentDate; }
        public void setAppointmentDate(Date appointmentDate) { this.appointmentDate = appointmentDate; }
        public String getDoctorName() { return doctorName; }
        public void setDoctorName(String doctorName) { this.doctorName = doctorName; }
        public String getStatus() { return status; }
        public void setStatus(String status) { this.status = status; }
    }

    // --- Lớp Repository: Thao tác dữ liệu ---
    static class AppointmentRepository {
        public void addAppointment(Appointment app) throws SQLException {
            String sql = "INSERT INTO appointments (patient_name, appointment_date, doctor_name, status) VALUES (?, ?, ?, ?)";
            try (Connection conn = DatabaseConnection.getConnection();
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, app.getPatientName());
                pstmt.setDate(2, app.getAppointmentDate());
                pstmt.setString(3, app.getDoctorName());
                pstmt.setString(4, app.getStatus());
                pstmt.executeUpdate();
            }
        }

        public void updateAppointment(Appointment app) throws SQLException {
            String sql = "UPDATE appointments SET patient_name=?, appointment_date=?, doctor_name=?, status=? WHERE id=?";
            try (Connection conn = DatabaseConnection.getConnection();
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, app.getPatientName());
                pstmt.setDate(2, app.getAppointmentDate());
                pstmt.setString(3, app.getDoctorName());
                pstmt.setString(4, app.getStatus());
                pstmt.setInt(5, app.getId());
                pstmt.executeUpdate();
            }
        }

        public void deleteAppointment(int id) throws SQLException {
            String sql = "DELETE FROM appointments WHERE id = ?";
            try (Connection conn = DatabaseConnection.getConnection();
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setInt(1, id);
                pstmt.executeUpdate();
            }
        }

        public List<Appointment> getAllAppointments() throws SQLException {
            List<Appointment> list = new ArrayList<>();
            String sql = "SELECT * FROM appointments";
            try (Connection conn = DatabaseConnection.getConnection();
                 Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery(sql)) {
                while (rs.next()) {
                    Appointment app = new Appointment();
                    app.setId(rs.getInt("id"));
                    app.setPatientName(rs.getString("patient_name"));
                    app.setAppointmentDate(rs.getDate("appointment_date"));
                    app.setDoctorName(rs.getString("doctor_name"));
                    app.setStatus(rs.getString("status"));
                    list.add(app);
                }
            }
            return list;
        }
    }

    public static void main(String[] args) {
        AppointmentRepository repo = new AppointmentRepository();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n--- QUẢN LÝ LỊCH KHÁM BỆNH ---");
            System.out.println("1. Thêm lịch khám");
            System.out.println("2. Xem danh sách");
            System.out.println("3. Cập nhật lịch khám");
            System.out.println("4. Xóa lịch khám");
            System.out.println("5. Thoát");
            System.out.print("Chọn: ");

            try {
                int choice = Integer.parseInt(sc.nextLine());
                switch (choice) {
                    case 1 -> {
                        System.out.print("Tên BN: "); String name = sc.nextLine();
                        System.out.print("Ngày (yyyy-mm-dd): "); Date date = Date.valueOf(sc.nextLine());
                        System.out.print("Bác sĩ: "); String doc = sc.nextLine();
                        System.out.print("Trạng thái: "); String status = sc.nextLine();
                        repo.addAppointment(new Appointment(name, date, doc, status));
                        System.out.println("Đã thêm!");
                    }
                    case 2 -> {
                        List<Appointment> list = repo.getAllAppointments();
                        System.out.printf("%-5s | %-20s | %-12s | %-15s | %-10s\n", "ID", "Bệnh nhân", "Ngày", "Bác sĩ", "Trạng thái");
                        for (Appointment a : list) {
                            System.out.printf("%-5d | %-20s | %-12s | %-15s | %-10s\n",
                                    a.getId(), a.getPatientName(), a.getAppointmentDate(), a.getDoctorName(), a.getStatus());
                        }
                    }
                    case 3 -> {
                        System.out.print("Nhập ID cần sửa: "); int id = Integer.parseInt(sc.nextLine());
                        System.out.print("Tên mới: "); String name = sc.nextLine();
                        System.out.print("Ngày mới (yyyy-mm-dd): "); Date date = Date.valueOf(sc.nextLine());
                        System.out.print("Bác sĩ mới: "); String doc = sc.nextLine();
                        System.out.print("Trạng thái mới: "); String status = sc.nextLine();
                        Appointment app = new Appointment(name, date, doc, status);
                        app.setId(id);
                        repo.updateAppointment(app);
                        System.out.println("Đã cập nhật!");
                    }
                    case 4 -> {
                        System.out.print("Nhập ID cần xóa: "); int id = Integer.parseInt(sc.nextLine());
                        repo.deleteAppointment(id);
                        System.out.println("Đã xóa!");
                    }
                    case 5 -> { return; }
                }
            } catch (Exception e) {
                System.out.println("Lỗi: " + e.getMessage());
            }
        }
    }
}