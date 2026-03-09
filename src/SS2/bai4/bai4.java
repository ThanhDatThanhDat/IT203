package SS2.bai4;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

class User {
    private String username;

    public User() {
        this.username = "Default_User";
    }

    public User(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}

public class bai4 {
    public static void main(String[] args) {
        List<User> users = new ArrayList<>();
        users.add(new User("Alice"));
        users.add(new User("Bob"));
        users.add(new User("Charlie"));

        Supplier<User> userFactory = User::new;
        User newUser = userFactory.get();
        System.out.println("Tạo user mới: " + newUser.getUsername());
        System.out.println("--- Danh sách User ---");
        Function<User, String> getNameFunc = User::getUsername;
        Consumer<String> printConsumer = System.out::println;

        for (User user : users) {
            String name = getNameFunc.apply(user);
            printConsumer.accept(name);
        }
    }
}