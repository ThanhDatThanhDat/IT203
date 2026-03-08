package SS1.bai4;

import java.io.IOException;

public class bai4 {

    public static void main(String[] args) {
        try {
            processUserData();
        } catch (IOException e) {
            System.out.println("Hàm Main (A) đã xử lý lỗi: " + e.getMessage());
        }
        System.out.println("Chương trình kết thúc an toàn sau khi xử lý ngoại lệ.");
    }

    public static void processUserData() throws IOException {
        saveToFile();
    }

    public static void saveToFile() throws IOException {
        throw new IOException("Lỗi kết nối ổ cứng hoặc file không tồn tại!");
    }
}