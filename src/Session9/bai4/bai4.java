package Session9.bai4;

class Animal {
    void makeSound() {
        System.out.println("Animal makes a generic sound");
    }
}

class Dog extends Animal {
    @Override
    void makeSound() {
        System.out.println("Dog barks: Woof! Woof!");
    }

    void wagTail() {
        System.out.println("Dog is wagging its tail... Happy!");
    }
}

public class bai4 {
    public static void main(String[] args) {
        Animal animal = new Dog();
        System.out.println("--- 1. Gọi phương thức chung ---");
        animal.makeSound();
        System.out.println("\n--- 2. Thử gọi phương thức riêng ---");
        System.out.println("Không thể gọi animal.wagTail() vì trình biên dịch chỉ nhìn vào Kiểu Khai Báo (Animal).");
        System.out.println("\n--- 3. Downcasting an toàn với instanceof ---");
        if (animal instanceof Dog) {
            Dog realDog = (Dog) animal;
            realDog.wagTail();
        } else {
            System.out.println("Đối tượng này không phải là Dog.");
        }
    }
}