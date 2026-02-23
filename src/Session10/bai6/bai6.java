package Session10.bai6;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class bai6 {
    static class Product {
        private String name;
        private double price;

        public Product(String name, double price) {
            this.name = name;
            this.price = price;
        }

        public String getName() {
            return name;
        }

        public double getPrice() {
            return price;
        }

        @Override
        public String toString() {
            return "Tên: " + name + " | Giá: " + price;
        }
    }

    public static void main(String[] args) {

        List<Product> products = new ArrayList<>();
        products.add(new Product("Laptop", 1500));
        products.add(new Product("Chuột", 25));
        products.add(new Product("Bàn phím", 75));
        products.add(new Product("Màn hình", 300));

        Collections.sort(products, new Comparator<Product>() {
            @Override
            public int compare(Product p1, Product p2) {
                return Double.compare(p1.getPrice(), p2.getPrice());
            }
        });

        System.out.println("=== Sắp xếp theo giá tăng dần (Anonymous Class) ===");
        for (Product p : products) {
            System.out.println(p);
        }

        Collections.sort(products, (p1, p2) ->
                p1.getName().compareTo(p2.getName())
        );

        System.out.println("\n=== Sắp xếp theo tên A-Z (Lambda) ===");
        for (Product p : products) {
            System.out.println(p);
        }
    }
}