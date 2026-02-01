package Session6.bai6;

public class bai6 {
    static class User {
        private int id;
        private String username;
        private String password;
        private String email;

        public User(int id, String username, String password, String email) {
            this.id = id;
            this.username = username;
            setPassword(password);
            setEmail(email);
        }

        public int getId() {
            return id;
        }
        public String getUsername() {
            return username;
        }
        public void setUsername(String username) {
            this.username = username;
        }
        public String getPassword() {
            return password;
        }
        public void setPassword(String password) {
            if (password != null && !password.trim().isEmpty()) {
                this.password = password;
            } else {
                System.out.println("Password khong duoc de trong!");
                this.password = "default";
            }
        }
        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            if (email != null && email.contains("@")) {
                this.email = email;
            } else {
                System.out.println("Email khong hop le!");
                this.email = "unknown@email.com";
            }
        }

        public void hienThiThongTin() {
            System.out.println("ID: " + id);
            System.out.println("Username: " + username);
            System.out.println("Email: " + email);
            System.out.println("Password: ********");
            System.out.println("-------------------------");
        }
    }

    public static void main(String[] args) {
        User u1 = new User(1, "datthanh", "123456", "datthanh@gmail.com");
        User u2 = new User(2, "minhha", "", "minhha@gmail.com");
        User u3 = new User(3, "trangnn", "abcdef", "trangnn-gmail.com");

        System.out.println("Thong tin user u1:");
        u1.hienThiThongTin();
        System.out.println("Thong tin user u2:");
        u2.hienThiThongTin();
        System.out.println("Thong tin user u3:");
        u3.hienThiThongTin();

        System.out.println("Thu cap nhat password và email cho u3:");
        u3.setPassword("");
        u3.setEmail("trangnn@gmail.com");
        u3.hienThiThongTin();
    }
}