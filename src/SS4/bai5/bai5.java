package SS4.bai5;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

enum Role { ADMIN, MODERATOR, USER }
enum Action { DELETE_USER, LOCK_USER, VIEW_PROFILE }

class User {
    private String username;
    private Role role;

    public User(String username, Role role) {
        this.username = username;
        this.role = role;
    }
    public Role getRole() { return role; }
}

class AccessControlSystem {
    public boolean canPerformAction(User user, Action action) {
        if (user == null || action == null) return false;

        switch (user.getRole()) {
            case ADMIN:
                return true;
            case MODERATOR:
                return action != Action.DELETE_USER;
            case USER:
                return action == Action.VIEW_PROFILE;
            default:
                return false;
        }
    }
}

public class bai5 {

    private AccessControlSystem accessSystem;
    private User adminUser;
    private User modUser;
    private User normalUser;

    @BeforeEach
    void setUp() {
        accessSystem = new AccessControlSystem();
        adminUser = new User("Admin_Dat", Role.ADMIN);
        modUser = new User("Mod_Thanh", Role.MODERATOR);
        normalUser = new User("User_Ptit", Role.USER);
    }

    @AfterEach
    void tearDown() {
        accessSystem = null;
        adminUser = null;
        modUser = null;
        normalUser = null;
    }

    @Test
    void testAdminPermissions() {
        assertAll("ADMIN có toàn quyền trên hệ thống",
                () -> assertTrue(accessSystem.canPerformAction(adminUser, Action.DELETE_USER)),
                () -> assertTrue(accessSystem.canPerformAction(adminUser, Action.LOCK_USER)),
                () -> assertTrue(accessSystem.canPerformAction(adminUser, Action.VIEW_PROFILE))
        );
    }

    @Test
    void testModeratorPermissions() {
        assertAll("MODERATOR có quyền khóa và xem, nhưng không được xóa",
                () -> assertFalse(accessSystem.canPerformAction(modUser, Action.DELETE_USER)),
                () -> assertTrue(accessSystem.canPerformAction(modUser, Action.LOCK_USER)),
                () -> assertTrue(accessSystem.canPerformAction(modUser, Action.VIEW_PROFILE))
        );
    }

    @Test
    void testUserPermissions() {
        assertAll("USER chỉ có quyền xem thông tin cá nhân",
                () -> assertFalse(accessSystem.canPerformAction(normalUser, Action.DELETE_USER)),
                () -> assertFalse(accessSystem.canPerformAction(normalUser, Action.LOCK_USER)),
                () -> assertTrue(accessSystem.canPerformAction(normalUser, Action.VIEW_PROFILE))
        );
    }
}