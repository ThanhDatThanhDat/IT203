package SS14.KiemTra;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.CallableStatement;
import java.sql.SQLException;

public class KiemTra {
    private static final String URL = "jdbc:mysql://localhost:3306/Users";
    private static final String USER = "root";
    private static final String PASSWORD = "123456";

    private Connection connection;

    public KiemTra() throws SQLException {
        this.connection = DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public void addAccount(String accountId, String fullName, double balance) throws SQLException {
        String sql = "INSERT INTO Accounts (AccountId, FullName, Balance) VALUES (?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, accountId);
            statement.setString(2, fullName);
            statement.setDouble(3, balance);
            statement.executeUpdate();
            System.out.println("Tài khoản đã được thêm thành công: " + fullName);
        }
    }

    public void updateBalance(String accountId, double amount) throws SQLException {
        String sql = "{call sp_UpdateBalance(?, ?)}";
        try (CallableStatement statement = connection.prepareCall(sql)) {
            statement.setString(1, accountId);
            statement.setDouble(2, amount);
            statement.execute();
            System.out.println("Số dư tài khoản " + accountId + " đã được cập nhật.");
        }
    }

    public void close() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
            System.out.println("Kết nối đã được đóng.");
        }
    }

    public static void main(String[] args) {
        try {
            KiemTra manager = new KiemTra();

            manager.addAccount("ACC03", "Le Thi C", 3000);
            manager.updateBalance("ACC01", 1000);
            manager.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
