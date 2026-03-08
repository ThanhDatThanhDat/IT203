package SS1.bai5;

class InvalidAgeException extends Exception {
    public InvalidAgeException(String message) {
        super(message);
    }
}

class User {
    private int age;

    public void setAge(int age) throws InvalidAgeException {
        if (age < 0 || age > 150) {
            throw new InvalidAgeException("Tuổi nhập vào (" + age + ") không hợp lệ. Tuổi phải từ 0 đến 150!");
        }
        this.age = age;
    }

    public int getAge() {
        return age;
    }
}

public class bai5 {
    public static void main(String[] args) {
        User user = new User();

        try {
            System.out.println("Thử thiết lập tuổi là 200...");
            user.setAge(200);
        } catch (InvalidAgeException e) {
            System.err.println("Lỗi nghiệp vụ: " + e.getMessage());
        }

        try {
            System.out.println("\nThử thiết lập tuổi là 25...");
            user.setAge(25);
            System.out.println("Thiết lập thành công! Tuổi: " + user.getAge());
        } catch (InvalidAgeException e) {
            System.err.println("Lỗi nghiệp vụ: " + e.getMessage());
        }
    }
}