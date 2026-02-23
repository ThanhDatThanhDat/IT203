package Session10.bai2;

public class bai2 {
    static abstract class Vehicle {

        protected String brand;

        public Vehicle(String brand) {
            this.brand = brand;
        }

        public abstract void move();
    }

    static class Car extends Vehicle {

        public Car(String brand) {
            super(brand);
        }

        @Override
        public void move() {
            System.out.println(brand + " - Cách di chuyển: Di chuyển bằng động cơ");
        }
    }

    static class Bicycle extends Vehicle {

        public Bicycle(String brand) {
            super(brand);
        }

        @Override
        public void move() {
            System.out.println(brand + " - Cách di chuyển: Di chuyển bằng sức người");
        }
    }

    public static void main(String[] args) {

        Vehicle car = new Car("Toyota");
        Vehicle bicycle = new Bicycle("Giant");

        car.move();
        bicycle.move();
    }
}