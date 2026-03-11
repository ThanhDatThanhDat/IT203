package SS4.bai4;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PasswordValidator {
    public String evaluatePasswordStrength(String password) {
        if (password == null || password.length() < 8) return "Yếu";

        boolean hasUpper = false, hasLower = false, hasDigit = false, hasSpecial = false;
        String specialChars = "!@#$%^&*()-_=+[]{}|;:',.<>?/";

        for (char c : password.toCharArray()) {
            if (Character.isUpperCase(c)) hasUpper = true;
            else if (Character.isLowerCase(c)) hasLower = true;
            else if (Character.isDigit(c)) hasDigit = true;
            else if (specialChars.contains(String.valueOf(c))) hasSpecial = true;
        }

        int criteriaMet = 0;
        if (hasUpper) criteriaMet++;
        if (hasLower) criteriaMet++;
        if (hasDigit) criteriaMet++;
        if (hasSpecial) criteriaMet++;

        if (criteriaMet == 4) return "Mạnh";
        if (criteriaMet >= 3) return "Trung bình";
        return "Yếu";
    }
}

public class bai4 {

    private final PasswordValidator validator = new PasswordValidator();

    @Test
    void testStrongPassword() {
        assertEquals("Mạnh", validator.evaluatePasswordStrength("Abc123!@"));
    }

    @Test
    void testMediumPasswords() {
        assertAll("Kiểm thử các mật khẩu mức độ Trung bình",
                () -> assertEquals("Trung bình", validator.evaluatePasswordStrength("abc123!@"), "Thiếu chữ hoa"),
                () -> assertEquals("Trung bình", validator.evaluatePasswordStrength("ABC123!@"), "Thiếu chữ thường"),
                () -> assertEquals("Trung bình", validator.evaluatePasswordStrength("Abcdef!@"), "Thiếu số"),
                () -> assertEquals("Trung bình", validator.evaluatePasswordStrength("Abc12345"), "Thiếu ký tự đặc biệt")
        );
    }

    @Test
    void testWeakPasswords() {
        assertAll("Kiểm thử các mật khẩu mức độ Yếu",
                () -> assertEquals("Yếu", validator.evaluatePasswordStrength("Ab1!"), "Quá ngắn"),
                () -> assertEquals("Yếu", validator.evaluatePasswordStrength("password"), "Chỉ có chữ thường"),
                () -> assertEquals("Yếu", validator.evaluatePasswordStrength("ABC12345"), "Chỉ có chữ hoa và số")
        );
    }
}