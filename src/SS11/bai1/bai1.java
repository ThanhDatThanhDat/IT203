package SS11.bai1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class bai1 {

    private static final String URL = "jdbc:mysql://192.168.1.10:3306/Hospital_DB";
    private static final String USER = "admin";
    private static final String PASS = "med123";

    public Connection getHospitalConn() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(URL, USER, PASS);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void exampleQuery() {
        Connection conn = getHospitalConn();
        try {
            if (conn != null) {
                System.out.println("Kết nối thành công!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (conn != null && !conn.isClosed()) {
                    conn.close();
                    System.out.println("Đã đóng kết nối an toàn.");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}