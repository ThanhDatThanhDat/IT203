package SS13.bai5;

import java.sql.*;
import java.util.Scanner;

public class bai5 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("\n=== RIKKEI HOSPITAL - TIEP NHAN 1 CHAM ===");
            System.out.println("1. Xem giuong trong");
            System.out.println("2. Tiep nhan benh nhan");
            System.out.println("3. Thoat");
            System.out.print("Chon: ");
            String choice = sc.nextLine();

            if (choice.equals("3")) break;

            if (choice.equals("1")) {
                hienThiGiuongTrong();
            } else if (choice.equals("2")) {
                try {
                    System.out.print("Ten: "); String ten = sc.nextLine();
                    System.out.print("Tuoi: "); int tuoi = Integer.parseInt(sc.nextLine());
                    System.out.print("Ma giuong: "); int maG = Integer.parseInt(sc.nextLine());
                    System.out.print("Tien tam ung: "); double tien = Double.parseDouble(sc.nextLine());

                    xuLyTiepNhan(ten, tuoi, maG, tien);
                } catch (NumberFormatException e) {
                    System.err.println("Loi: Du lieu nhap vao phai la so!");
                }
            }
        }
        sc.close();
    }

    private static Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/Rikkei_Hospital", "root", "password");
    }

    private static void hienThiGiuongTrong() {
        String sql = "SELECT id, bed_number FROM Beds WHERE status = 'Empty'";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            System.out.println("Danh sach giuong trong:");
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id") + " - So giuong: " + rs.getString("bed_number"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void xuLyTiepNhan(String ten, int tuoi, int maG, double tien) {
        Connection conn = null;
        try {
            conn = getConnection();
            conn.setAutoCommit(false);

            String sqlP = "INSERT INTO Patients (name, age, bed_id) VALUES (?, ?, ?)";
            PreparedStatement psP = conn.prepareStatement(sqlP, Statement.RETURN_GENERATED_KEYS);
            psP.setString(1, ten);
            psP.setInt(2, tuoi);
            psP.setInt(3, maG);
            psP.executeUpdate();

            ResultSet rs = psP.getGeneratedKeys();
            int pId = 0;
            if (rs.next()) pId = rs.getInt(1);

            String sqlB = "UPDATE Beds SET status = 'Occupied' WHERE id = ? AND status = 'Empty'";
            PreparedStatement psB = conn.prepareStatement(sqlB);
            psB.setInt(1, maG);
            if (psB.executeUpdate() == 0) {
                throw new Exception("Giuong khong hop le hoac da co nguoi!");
            }

            String sqlF = "INSERT INTO Finance (patient_id, amount) VALUES (?, ?)";
            PreparedStatement psF = conn.prepareStatement(sqlF);
            psF.setInt(1, pId);
            psF.setDouble(2, tien);
            psF.executeUpdate();

            conn.commit();
            System.out.println(">>> Thanh cong!");

        } catch (Exception e) {
            if (conn != null) {
                try { conn.rollback(); } catch (SQLException ex) { ex.printStackTrace(); }
            }
            System.err.println(">>> LOI: " + e.getMessage());
        } finally {
            try { if (conn != null) conn.close(); } catch (SQLException e) { e.printStackTrace(); }
        }
    }
}