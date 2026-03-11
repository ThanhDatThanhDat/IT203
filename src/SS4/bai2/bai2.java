package SS4.bai2;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class UserService {

    public boolean checkRegistrationAge(int age) {
        if (age < 0) {
            throw new IllegalArgumentException("Tuổi không được là số âm");
        }
        return age >= 18;
    }
}

public class bai2 {

    private final UserService userService = new UserService();

    @Test
    void testBoundaryAge_18() {
        // Arrange
        int age = 18;

        boolean result = userService.checkRegistrationAge(age);

        assertEquals(true, result, "Người 18 tuổi phải đăng ký được");
    }

    @Test
    void testUnderAge_17() {
        int age = 17;

        boolean result = userService.checkRegistrationAge(age);

        assertEquals(false, result, "Người 17 tuổi không được phép đăng ký");
    }

    @Test
    void testNegativeAge_ThrowsException() {
        int age = -1;

        assertThrows(IllegalArgumentException.class, () -> {
            userService.checkRegistrationAge(age);
        }, "Tuổi âm phải ném ra ngoại lệ IllegalArgumentException");
    }
}