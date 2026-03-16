package SS7.bai1;

import java.util.ArrayList;
import java.util.List;

class Product {
    String id;
    String name;
    double price;

    public Product(String id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }
}

class Customer {
    String name;
    String email;

    public Customer(String name, String email) {
        this.name = name;
        this.email = email;
    }
}

class Order {
    String orderId;
    Customer customer;
    List<Product> products = new ArrayList<>();
    double totalAmount;

    public Order(String orderId, Customer customer) {
        this.orderId = orderId;
        this.customer = customer;
    }

    public void addProduct(Product product) {
        products.add(product);
    }
}

class OrderCalculator {
    public void calculateTotal(Order order) {
        double total = 0;
        for (Product p : order.products) {
            total += p.price;
        }
        order.totalAmount = total;
    }
}

class OrderRepository {
    public void save(Order order) {
        System.out.println("Đã lưu đơn hàng " + order.orderId);
    }
}

class EmailService {
    public void sendConfirmationEmail(Order order) {
        System.out.println("Đã gửi email đến " + order.customer.email + ": Đơn hàng " + order.orderId + " đã được tạo");
    }
}

public class bai1 {
    public static void main(String[] args) {
        Product p1 = new Product("SP01", "Laptop", 15000000);
        Product p2 = new Product("SP02", "Chuột", 300000);
        Product p3 = new Product("SP02", "Chuột", 300000);
        System.out.println("Đã thêm sản phẩm SP01, SP02");

        Customer customer = new Customer("Nguyễn Văn A", "a@example.com");
        System.out.println("Đã thêm khách hàng");

        Order order = new Order("ORD001", customer);
        order.addProduct(p1);
        order.addProduct(p2);
        order.addProduct(p3);
        System.out.println("Đơn hàng ORD001 được tạo");

        OrderCalculator calculator = new OrderCalculator();
        calculator.calculateTotal(order);
        System.out.println("Tổng tiền: " + (long)order.totalAmount);

        OrderRepository repository = new OrderRepository();
        repository.save(order);

        EmailService emailService = new EmailService();
        emailService.sendConfirmationEmail(order);
    }
}