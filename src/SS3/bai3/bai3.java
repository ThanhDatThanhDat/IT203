package SS3.bai3;

import java.util.List;
import java.util.Optional;

record User(String username, String email) {}

class UserRepository {
    private final List<User> users = List.of(
            new User("alice", "alice@gmail.com"),
            new User("bob", "bob@yahoo.com"),
            new User("charlie", "charlie@gmail.com")
    );

    public Optional<User> findUserByUsername(String username) {
        return users.stream()
                .filter(u -> u.username().equalsIgnoreCase(username))
                .findFirst();
    }
}

public class bai3 {
    public static void main(String[] args) {
        UserRepository repository = new UserRepository();

        System.out.println("--- Tìm kiếm alice ---");
        Optional<User> aliceOpt = repository.findUserByUsername("alice");

        aliceOpt.ifPresentOrElse(
                user -> System.out.println("Welcome " + user.username()),
                () -> System.out.println("Guest login")
        );

        System.out.println("\n--- Tìm kiếm unknown_user ---");
        String result = repository.findUserByUsername("unknown")
                .map(u -> "Welcome " + u.username())
                .orElse("Guest login");

        System.out.println(result);
    }
}