package SS4.bai3;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class UserProcessor {

    public String processEmail(String email) {
        if (email == null) {
            throw new IllegalArgumentException("Email không được null");
        }

        int atIndex = email.indexOf('@');
        if (atIndex == -1) {
            throw new IllegalArgumentException("Email thiếu ký tự '@'");
        }

        if (atIndex == email.length() - 1) {
            throw new IllegalArgumentException("Email thiếu tên miền sau ký tự '@'");
        }

        return email.toLowerCase();
    }
}

public class bai3 {

    private UserProcessor processor;

    @BeforeEach
    void setUp() {
        processor = new UserProcessor();
    }

    @Test
    void testValidEmail_ShouldReturnLowercase() {
        String input = "user@gmail.com";
        String result = processor.processEmail(input);
        assertEquals("user@gmail.com", result, "Email hợp lệ phải được giữ nguyên");
    }

    @Test
    void testEmailMissingAtSign_ShouldThrowException() {
        String input = "usergmail.com";
        assertThrows(IllegalArgumentException.class, () -> {
            processor.processEmail(input);
        }, "Email không có '@' phải ném ngoại lệ");
    }

    @Test
    void testEmailMissingDomain_ShouldThrowException() {
        String input = "user@";
        assertThrows(IllegalArgumentException.class, () -> {
            processor.processEmail(input);
        }, "Email thiếu tên miền sau '@' phải ném ngoại lệ");
    }

    @Test
    void testEmailNormalization_ShouldConvertToLowercase() {
        String input = "Example@Gmail.com";
        String result = processor.processEmail(input);
        assertEquals("example@gmail.com", result, "Email phải được chuyển về chữ thường");
    }
}