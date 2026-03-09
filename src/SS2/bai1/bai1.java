package SS2.bai1;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

class User {
    String username;
    String role;

    public User(String username, String role) {
        this.username = username;
        this.role = role;
    }

    @Override
    public String toString() {
        return "User{username='" + username + "', role='" + role + "'}";
    }
}

public class bai1 {
    public static void main(String[] args) {
        User user = new User("admin_01", "ADMIN");

        Predicate<User> isAdmin = u -> "ADMIN".equals(u.role);
        System.out.println("Is Admin? " + isAdmin.test(user));

        Function<User, String> getUsername = u -> u.username;
        System.out.println("Username: " + getUsername.apply(user));

        Consumer<User> printDetail = u -> System.out.println("Detail: " + u);
        printDetail.accept(user);

        Supplier<User> userFactory = () -> new User("guest", "USER");
        User newUser = userFactory.get();
        System.out.println("New User: " + newUser);
    }
}