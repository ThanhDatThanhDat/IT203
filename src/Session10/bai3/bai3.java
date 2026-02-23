package Session10.bai3;

public class bai3 {
    static abstract class Animal {
        protected String name;

        public Animal(String name) {
            this.name = name;
        }
    }

    interface Swimmable {
        void swim();
    }

    interface Flyable {
        void fly();
    }

    static class Duck extends Animal implements Swimmable, Flyable {

        public Duck(String name) {
            super(name);
        }

        @Override
        public void swim() {
            System.out.println(name + " đang bơi dưới nước.");
        }

        @Override
        public void fly() {
            System.out.println(name + " đang bay trên trời.");
        }
    }

    static class Fish extends Animal implements Swimmable {

        public Fish(String name) {
            super(name);
        }

        @Override
        public void swim() {
            System.out.println(name + " đang bơi dưới nước.");
        }
    }

    public static void main(String[] args) {

        Duck duck = new Duck("Vịt Donald");
        Fish fish = new Fish("Cá Nemo");

        System.out.println("=== Duck ===");
        duck.swim();
        duck.fly();

        System.out.println("\n=== Fish ===");
        fish.swim();
    }
}