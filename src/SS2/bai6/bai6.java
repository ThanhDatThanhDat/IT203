package SS2.bai6;

class User {
    private String username;

    public User(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}

@FunctionalInterface
interface UserProcessor {
    String process(User u);
}

class UserUtils {
    public static String convertToUpperCase(User u) {
        if (u == null || u.getUsername() == null) {
            return "";
        }
        return u.getUsername().toUpperCase();
    }
}

public class bai6 {
    public static void main(String[] args) {
        User myUser = new User("gemini_ai_2026");

        UserProcessor processor = UserUtils::convertToUpperCase;

        String result = processor.process(myUser);

        System.out.println("--- Kết quả xử lý User ---");
        System.out.println("Username gốc: " + myUser.getUsername());
        System.out.println("Username sau khi xử lý: " + result);
    }
}