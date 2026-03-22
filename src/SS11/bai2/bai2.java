package SS11.bai2;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;

public class bai2 {
    public void displayPharmacyCatalogue(Connection conn) {
        String sql = "SELECT medicine_name, stock FROM Pharmacy";
        Statement stmt = null;
        ResultSet rs = null;

        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);

            System.out.println("--- DANH MỤC THUỐC TRONG KHO ---");
            System.out.printf("%-20s | %-10s\n", "Tên thuốc", "Số lượng");
            System.out.println("\n");

            while (rs.next()) {
                String name = rs.getString("medicine_name");
                int stock = rs.getInt("stock");

                System.out.printf("%-20s | %-10d\n", name, stock);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}