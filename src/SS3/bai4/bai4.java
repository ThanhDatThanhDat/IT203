package SS3.bai4;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

record User(String username, String email) {}

public class bai4 {
    public static void main(String[] args) {
        List<User> rawUsers = List.of(
                new User("alice", "alice@gmail.com"),
                new User("bob", "bob@yahoo.com"),
                new User("alice", "alice_new@gmail.com"),
                new User("charlie", "charlie@gmail.com"),
                new User("bob", "bob_updated@yahoo.com")
        );

        System.out.println("--- Danh sách gốc (có trùng lặp) ---");
        rawUsers.forEach(System.out::println);

        List<User> uniqueUsers = rawUsers.stream()
                .collect(Collectors.toMap(
                        User::username,
                        user -> user,
                        (existing, replacement) -> existing
                ))
                .values()
                .stream()
                .toList();

        System.out.println("\n--- Danh sách sau khi lọc trùng (theo username) ---");
        uniqueUsers.forEach(System.out::println);
    }
}