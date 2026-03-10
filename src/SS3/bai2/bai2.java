package SS3.bai2;

import java.util.List;

record User(String username, String email) {}

public class bai2 {
    public static void main(String[] args) {
        List<User> users = List.of(
                new User("alice", "alice@gmail.com"),
                new User("bob", "bob@yahoo.com"),
                new User("charlie", "charlie@gmail.com"),
                new User("dave", "dave@outlook.com"),
                new User("eve", "eve@gmail.com")
        );

        System.out.println("=== Danh sách người dùng sử dụng Gmail ===");

        users.stream()
                .filter(user -> user.email().endsWith("@gmail.com"))
                .forEach(user -> System.out.println("Username: " + user.username()));
    }
}