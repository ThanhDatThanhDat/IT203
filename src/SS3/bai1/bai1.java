package SS3.bai1;

import java.util.List;

enum Status {
    ACTIVE, INACTIVE
}

record User(String username, String email, Status status) {
}

public class bai1 {
    public static void main(String[] args) {
        User alice = new User("alice", "alice@example.com", Status.ACTIVE);
        User bob = new User("bob", "bob@example.com", Status.INACTIVE);
        User charlie = new User("charlie", "charlie@example.com", Status.ACTIVE);

        List<User> users = List.of(alice, bob, charlie);
        System.out.println("=== Danh sách người dùng (Record) ===");

        users.forEach(user -> {
            System.out.println("Tên: " + user.username() + " | Email: " + user.email() + " | Trạng thái: " + user.status());
        });
    }
}