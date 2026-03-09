package SS2.bai5;

interface UserActions {
    default void logActivity(String activity) {
        System.out.println("User log: " + activity);
    }
}

interface AdminActions {
    default void logActivity(String activity) {
        System.out.println("Admin log: " + activity);
    }
}

class SuperAdmin implements UserActions, AdminActions {

    @Override
    public void logActivity(String activity) {
        AdminActions.super.logActivity(activity);
        System.out.println("SuperAdmin additional security check...");
    }
}

public class bai5 {
    public static void main(String[] args) {
        SuperAdmin sa = new SuperAdmin();
        sa.logActivity("Xóa cơ dữ liệu hệ thống");
    }
}