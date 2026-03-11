package SS4.bai1;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class UserValidator {
    public boolean isValidUsername(String username) {
        if (username == null) return false;

        int length = username.length();
        return length >= 6 && length <= 20 && !username.contains(" ");
    }
}

public class bai1 {

    private final UserValidator validator = new UserValidator();

    @Test
    void TC01_ValidUsername() {
        String username = "user123";
        boolean result = validator.isValidUsername(username);
        assertTrue(result, "TC01 Thất bại: 'user123' phải hợp lệ");
    }

    @Test
    void TC02_UsernameTooShort() {
        String username = "abc";
        boolean result = validator.isValidUsername(username);
        assertFalse(result, "TC02 Thất bại: 'abc' quá ngắn");
    }

    @Test
    void TC03_UsernameContainsSpace() {
        String username = "user name";
        boolean result = validator.isValidUsername(username);
        assertFalse(result, "TC03 Thất bại: Tên không được chứa khoảng trắng");
    }
}