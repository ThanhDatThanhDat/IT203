package SS2.bai3;

@FunctionalInterface
interface Authenticatable {
    String getPassword();

    default boolean isAuthenticated() {
        return getPassword() != null && !getPassword().isEmpty();
    }

    static String encrypt(String rawPassword) {
        return "ENC[" + rawPassword + "]-Hash";
    }
}

public class bai3 {
    public static void main(String[] args) {
        Authenticatable user = () -> "MySecretPassword123";

        String encrypted = Authenticatable.encrypt(user.getPassword());

        System.out.println("Password hợp lệ: " + user.isAuthenticated());
        System.out.println("Mật khẩu đã mã hóa: " + encrypted);
    }
}