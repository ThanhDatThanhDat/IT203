package SS1.bai6;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

class InvalidAgeException extends Exception {
    public InvalidAgeException(String message) {
        super(message);
    }
}

class User {
    private String name;
    private int age;

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) throws InvalidAgeException {
        if (age < 0 || age > 150) {
            throw new InvalidAgeException("Tuổi " + age + " nằm ngoài phạm vi cho phép (0-150).");
        }
        this.age = age;
    }

    public void displayInfo() {
        if (this.name == null || this.name.trim().isEmpty()) {
            System.out.println("[INFO] Người dùng chưa cập nhật tên.");
        } else {
            System.out.println("Tên người dùng: " + this.name.toUpperCase());
        }
        System.out.println("Tuổi: " + this.age);
    }
}

public class bai6 {
    public static void main(String[] args) {
        User user = new User();

        String inputAge = "200";
        String inputName = null;

        user.setName(inputName);
        user.displayInfo();

        try {
            int age = Integer.parseInt(inputAge);
            user.setAge(age);
        } catch (NumberFormatException e) {
            logError("Định dạng số không hợp lệ: " + inputAge);
        } catch (InvalidAgeException e) {
            logError("Vi phạm quy tắc nghiệp vụ: " + e.getMessage());
        } finally {
            System.out.println("[SYSTEM] Hoàn tất kiểm tra hồ sơ người dùng.");
        }
    }

    private static void logError(String message) {
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        System.err.println("[ERROR] [" + timestamp + "] " + message);
    }
}