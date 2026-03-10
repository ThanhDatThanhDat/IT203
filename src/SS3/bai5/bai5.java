package SS3.bai5;

import java.util.Comparator;
import java.util.List;

record User(String username, String email) {}

public class bai5 {
    public static void main(String[] args) {
        List<User> users = List.of(
                new User("alex", "alex@gmail.com"),
                new User("alexander", "alexander@gmail.com"),
                new User("bob", "bob@yahoo.com"),
                new User("charlotte", "charlotte@gmail.com"),
                new User("benjamin", "benjamin@gmail.com"),
                new User("dan", "dan@outlook.com"),
                new User("elizabeth", "elizabeth@gmail.com")
        );

        System.out.println("=== Top 3 người dùng có Username dài nhất ===");

        users.stream()
                .sorted(Comparator.comparingInt((User u) -> u.username().length()).reversed())
                .limit(3)
                .forEach(u -> System.out.println(u.username() + " (" + u.username().length() + " ký tự)"));
    }
}