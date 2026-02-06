package Session9.bai6;

import java.util.ArrayList;
import java.util.List;

abstract class Shape {
    abstract double area();
}

class Circle extends Shape {
    private double radius;
    public Circle() { this.radius = 1.0; }
    public Circle(double radius) { this.radius = radius; }

    @Override
    double area() {
        return Math.PI * radius * radius;
    }
}

class Rectangle extends Shape {
    private double width, height;

    public Rectangle(double width, double height) {
        this.width = width;
        this.height = height;
    }

    @Override
    double area() {
        return width * height;
    }

    public double area(double scale) {
        return (width * scale) * (height * scale);
    }
}

public class bai6 {
    public static void main(String[] args) {
        List<Shape> shapes = new ArrayList<>();

        shapes.add(new Circle());
        shapes.add(new Circle(5.5));
        shapes.add(new Rectangle(4, 5));

        System.out.println("--- Tính diện tích (Overriding tại Runtime) ---");
        for (Shape s : shapes) {
            System.out.printf("Diện tích: %.2f\n", s.area());
        }

        System.out.println("\n--- Minh họa Overloading (Compile-time) ---");
        Rectangle rect = new Rectangle(10, 20);
        System.out.println("Diện tích gốc: " + rect.area());
        System.out.println("Diện tích khi phóng to x2: " + rect.area(2.0));
    }
}