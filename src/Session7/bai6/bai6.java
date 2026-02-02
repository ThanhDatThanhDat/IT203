package Session7.bai6;

import java.util.ArrayList;

class User {
    public final int id;
    public String username;
    public String password;

    public User(int id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    @Override
    public String toString() {
        return "User[id=" + id + ", name=" + username + "]";
    }
}

class UserManager {
    public static ArrayList<User> users = new ArrayList<>();

    public static void addUser(User u) {
        users.add(u);
    }

    public static boolean checkLogin(String username, String password) {
        for (User u : users) {
            if (u.username.equals(username) && u.password.equals(password)) {
                return true;
            }
        }
        return false;
    }
}

public class bai6 {
    public static void main(String[] args) {
        User u1 = new User(1, "dev_a", "123456");
        User u2 = new User(2, "dev_b", "654321");
        User u3 = new User(3, "dev_c", "admin123");

        UserManager.addUser(u1);
        UserManager.addUser(u2);
        UserManager.addUser(u3);

        System.out.println(">> Da them 3 user vao he thong.");
        System.out.println("Danh sach hien tai:");
        for (User u : UserManager.users) {
            System.out.println(u.id + ". " + u);
        }

        // Ktra
        System.out.println("\n>> Kiem tra dang nhap:");

        System.out.print("- Login (\"dev_a\", \"123456\"): ");
        System.out.println(UserManager.checkLogin("dev_a", "123456") ? "Thanh cong!" : "That bai!");

        System.out.print("- Login (\"dev_b\", \"sai_pass\"): ");
        System.out.println(UserManager.checkLogin("dev_b", "sai_pass") ? "Thanh cong!" : "That bai!");
    }
}