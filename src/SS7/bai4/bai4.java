package SS7.bai4;

import java.util.ArrayList;
import java.util.List;

class Order {
    String id;
    public Order(String id) { this.id = id; }
}

interface OrderRepository {
    void save(Order order);
    List<Order> findAll();
}

interface NotificationService {
    void send(String message, String recipient);
}

class FileOrderRepository implements OrderRepository {
    @Override
    public void save(Order order) {
        System.out.println("Lưu đơn hàng vào file: " + order.id);
    }
    @Override
    public List<Order> findAll() { return new ArrayList<>(); }
}

class EmailService implements NotificationService {
    @Override
    public void send(String message, String recipient) {
        System.out.println("Gửi email: " + message);
    }
}

class DatabaseOrderRepository implements OrderRepository {
    @Override
    public void save(Order order) {
        System.out.println("Lưu đơn hàng vào database: " + order.id);
    }
    @Override
    public List<Order> findAll() { return new ArrayList<>(); }
}

class SMSNotification implements NotificationService {
    @Override
    public void send(String message, String recipient) {
        System.out.println("Gửi SMS: " + message);
    }
}

class OrderService {
    private final OrderRepository repository;
    private final NotificationService notification;

    public OrderService(OrderRepository repository, NotificationService notification) {
        this.repository = repository;
        this.notification = notification;
    }

    public void createOrder(Order order) {
        repository.save(order);
        notification.send("Đơn hàng " + order.id + " đã được tạo", "Khách hàng");
    }
}

public class bai4 {
    public static void main(String[] args) {
        System.out.println("--- Cấu hình 1: File + Email ---");
        OrderService service1 = new OrderService(new FileOrderRepository(), new EmailService());
        service1.createOrder(new Order("ORD001"));

        System.out.println("\n--- Cấu hình 2: Database + SMS ---");
        OrderService service2 = new OrderService(new DatabaseOrderRepository(), new SMSNotification());
        service2.createOrder(new Order("ORD002"));
    }
}