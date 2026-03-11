package SS4.bai6;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class UserProfile {
    String email;
    LocalDate dob;

    public UserProfile(String email, LocalDate dob) {
        this.email = email;
        this.dob = dob;
    }
}

class User {
    String id;
    UserProfile profile;

    public User(String id, UserProfile profile) {
        this.id = id;
        this.profile = profile;
    }
}

class ProfileManager {

    public User updateProfile(User existingUser, UserProfile newProfile, List<User> allUsers) {
        if (newProfile.dob.isAfter(LocalDate.now())) {
            return null;
        }

        if (allUsers != null) {
            for (User u : allUsers) {
                if (!u.id.equals(existingUser.id) && u.profile.email.equalsIgnoreCase(newProfile.email)) {
                    return null;
                }
            }
        }

        // Nếu vượt qua các ràng buộc, tiến hành cập nhật
        existingUser.profile = newProfile;
        return existingUser;
    }
}

public class bai6 {
    private ProfileManager manager;
    private List<User> userList;
    private User currentUser;

    @BeforeEach
    void setUp() {
        manager = new ProfileManager();
        userList = new ArrayList<>();

        UserProfile currentProfile = new UserProfile("dat@ptit.edu.vn", LocalDate.of(2005, 1, 1));
        currentUser = new User("U001", currentProfile);

        UserProfile otherProfile = new UserProfile("other@gmail.com", LocalDate.of(2000, 5, 5));
        userList.add(new User("U002", otherProfile));
        userList.add(currentUser);
    }

    @Test
    void testUpdate_SuccessWithValidData() {
        UserProfile newInfo = new UserProfile("new_email@ptit.edu.vn", LocalDate.of(2005, 1, 1));
        User result = manager.updateProfile(currentUser, newInfo, userList);

        assertNotNull(result, "Cập nhật phải thành công với dữ liệu hợp lệ");
        assertEquals("new_email@ptit.edu.vn", result.profile.email);
    }

    @Test
    void testUpdate_FailWithFutureBirthday() {
        UserProfile newInfo = new UserProfile("dat@ptit.edu.vn", LocalDate.of(2099, 12, 31));
        User result = manager.updateProfile(currentUser, newInfo, userList);

        assertNull(result, "Hệ thống phải từ chối ngày sinh trong tương lai");
    }

    @Test
    void testUpdate_FailWithDuplicateEmail() {
        UserProfile newInfo = new UserProfile("other@gmail.com", LocalDate.of(2005, 1, 1));
        User result = manager.updateProfile(currentUser, newInfo, userList);

        assertNull(result, "Hệ thống phải từ chối email đã tồn tại của người khác");
    }

    @Test
    void testUpdate_SuccessWithSameEmail() {
        UserProfile newInfo = new UserProfile("dat@ptit.edu.vn", LocalDate.of(2005, 2, 2));
        User result = manager.updateProfile(currentUser, newInfo, userList);

        assertNotNull(result, "Phải cho phép cập nhật nếu email không đổi");
    }

    @Test
    void testUpdate_SuccessWithEmptyList() {
        UserProfile newInfo = new UserProfile("unique@ptit.edu.vn", LocalDate.of(2005, 1, 1));
        User result = manager.updateProfile(currentUser, newInfo, null);

        assertNotNull(result, "Cập nhật thành công ngay cả khi danh sách user trống");
    }

    @Test
    void testUpdate_FailWithMultipleConstraints() {
        UserProfile newInfo = new UserProfile("other@gmail.com", LocalDate.of(2099, 1, 1));
        User result = manager.updateProfile(currentUser, newInfo, userList);

        assertNull(result, "Vi phạm nhiều ràng buộc phải bị từ chối");
    }
}