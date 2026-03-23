package SS12.bai4;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class bai4 {

    public static class TestResult {
        private int patientId;
        private String testType;
        private double resultValue;

        public TestResult(int patientId, String testType, double resultValue) {
            this.patientId = patientId;
            this.testType = testType;
            this.resultValue = resultValue;
        }

        public int getPatientId() { return patientId; }
        public String getTestType() { return testType; }
        public double getResultValue() { return resultValue; }
    }

    public void importTestResults(Connection conn, List<TestResult> results) throws SQLException {
        String sql = "INSERT INTO TestResults (patient_id, test_type, result_value) VALUES (?, ?, ?)";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {

            conn.setAutoCommit(false);

            for (TestResult item : results) {
                pstmt.setInt(1, item.getPatientId());
                pstmt.setString(2, item.getTestType());
                pstmt.setDouble(3, item.getResultValue());

                pstmt.addBatch();
            }

            int[] resultsCount = pstmt.executeBatch();

            conn.commit();

            System.out.println("Đã nạp thành công " + resultsCount.length + " bản ghi xét nghiệm!");

        } catch (SQLException e) {
            conn.rollback();
            System.err.println("Lỗi nạp dữ liệu: " + e.getMessage());
        } finally {
            conn.setAutoCommit(true);
        }
    }
}