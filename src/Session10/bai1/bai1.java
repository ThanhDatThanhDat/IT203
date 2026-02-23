package Session10.bai1;

import java.util.Scanner;

public class bai1 {

    interface Shape {
        double getArea();
        double getPerimeter();
    }

    static class Circle implements Shape {
        private double radius;

        public Circle(double radius) {
            this.radius = radius;
        }

        @Override
        public double getArea() {
            return Math.PI * radius * radius;
        }

        @Override
        public double getPerimeter() {
            return 2 * Math.PI * radius;
        }
    }

    static class Rectangle implements Shape {
        private double width;
        private double height;

        public Rectangle(double width, double height) {
            this.width = width;
            this.height = height;
        }

        @Override
        public double getArea() {
            return width * height;
        }

        @Override
        public double getPerimeter() {
            return 2 * (width + height);
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Nhập bán kính hình tròn: ");
        double radius = sc.nextDouble();
        Shape circle = new Circle(radius);

        System.out.print("Nhập chiều rộng hình chữ nhật: ");
        double width = sc.nextDouble();
        System.out.print("Nhập chiều cao hình chữ nhật: ");
        double height = sc.nextDouble();
        Shape rectangle = new Rectangle(width, height);

        // kết quả
        System.out.println("\n===== KẾT QUẢ =====");

        System.out.println("Hình tròn:");
        System.out.println("Diện tích: " + circle.getArea());
        System.out.println("Chu vi: " + circle.getPerimeter());

        System.out.println("\nHình chữ nhật:");
        System.out.println("Diện tích: " + rectangle.getArea());
        System.out.println("Chu vi: " + rectangle.getPerimeter());

        sc.close();
    }
}