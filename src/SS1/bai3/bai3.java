package SS1.bai3;

class User {
    private int age;

    public void setAge(int age) {
        if (age < 0) {
            throw new IllegalArgumentException("Lỗi: Tuổi không thể âm!");
        }
        this.age = age;
    }

    public int getAge() {
        return age;
    }
}

public class bai3 {
    public static void main(String[] args) {
        User user = new User();

        try {
            System.out.println("Đang thử thiết lập tuổi là -5...");
            user.setAge(-5);
            System.out.println("Tuổi đã được thiết lập thành công.");
        } catch (IllegalArgumentException e) {
            System.out.println("Bắt được ngoại lệ: " + e.getMessage());
        }

        try {
            System.out.println("\nĐang thử thiết lập tuổi là 25...");
            user.setAge(25);
            System.out.println("Thiết lập thành công! Tuổi hiện tại: " + user.getAge());
        } catch (IllegalArgumentException e) {
            System.out.println("Bắt được ngoại lệ: " + e.getMessage());
        }
    }
}