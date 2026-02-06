package Session9.bai2;

class Animal {
    public void sound() {
        System.out.println("Động vật đang tạo ra âm thanh...");
    }
}

class Dog extends Animal {
    @Override
    public void sound() {
        System.out.println("Gâu gâu! (Chó đang sủa)");
    }
}

class Cat extends Animal {
    @Override
    public void sound() {
        System.out.println("Meo meo! (Mèo đang kêu)");
    }
}

public class bai2 {
    public static void main(String[] args) {
        Animal myAnimal = new Animal();
        Animal myDog = new Dog();
        Animal myCat = new Cat();

        System.out.println("--- Kiểm tra tiếng kêu ---");

        myAnimal.sound();
        myDog.sound();
        myCat.sound();
    }
}