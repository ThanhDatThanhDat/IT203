package SS7.bai5;

import java.util.*;

class Product {
    String id, name, category;
    double price;

    public Product(String id, String name, double price, String category) {
        this.id = id; this.name = name; this.price = price; this.category = category;
    }
}

class Customer {
    String name, email, phone;

    public Customer(String name, String email, String phone) {
        this.name = name; this.email = email; this.phone = phone;
    }
}

class OrderItem {
    Product product;
    int quantity;

    public OrderItem(Product product, int quantity) {
        this.product = product; this.quantity = quantity;
    }

    public double getSubTotal() { return product.price * quantity; }
}

class Order {
    String orderId;
    Customer customer;
    List<OrderItem> items = new ArrayList<>();
    double totalAmount, finalAmount;

    public Order(String orderId, Customer customer) {
        this.orderId = orderId; this.customer = customer;
    }
}

interface DiscountStrategy { double applyDiscount(double amount); }

class PercentageDiscount implements DiscountStrategy {
    double pct;
    public PercentageDiscount(double pct) { this.pct = pct; }
    @Override public double applyDiscount(double amount) { return amount * (pct / 100); }
}

interface PaymentMethod { void process(double amount); }

class CreditCardPayment implements PaymentMethod {
    @Override public void process(double amount) { System.out.println("Thanh toán thẻ tín dụng thành công: " + (long)amount); }
}

interface OrderRepository { void save(Order order); List<Order> getAll(); }

class DatabaseOrderRepository implements OrderRepository {
    private List<Order> db = new ArrayList<>();
    @Override public void save(Order order) { db.add(order); System.out.println("Đã lưu đơn hàng " + order.orderId); }
    @Override public List<Order> getAll() { return db; }
}

interface NotificationService { void send(String msg, Customer c); }

class EmailNotification implements NotificationService {
    @Override public void send(String msg, Customer c) { System.out.println("Gửi email đến " + c.email + ": " + msg); }
}

class InvoiceGenerator {
    public void printInvoice(Order order, double discount) {
        System.out.println("=== HÓA ĐƠN ===");
        System.out.println("Khách: " + order.customer.name);
        for (OrderItem item : order.items) {
            System.out.println(item.product.name + " - SL: " + item.quantity + " - Thành tiền: " + (long)item.getSubTotal());
        }
        System.out.println("Tổng tiền: " + (long)order.totalAmount);
        System.out.println("Giảm giá: " + (long)discount);
        System.out.println("Cần thanh toán: " + (long)order.finalAmount);
    }
}

class OrderService {
    private OrderRepository repo;
    private NotificationService notify;

    public OrderService(OrderRepository repo, NotificationService notify) {
        this.repo = repo; this.notify = notify;
    }

    public void processOrder(Order order, DiscountStrategy discount, PaymentMethod payment, InvoiceGenerator gen) {
        double total = 0;
        for (OrderItem item : order.items) total += item.getSubTotal();
        order.totalAmount = total;
        double discountVal = (discount != null) ? discount.applyDiscount(total) : 0;
        order.finalAmount = total - discountVal;

        payment.process(order.finalAmount);
        gen.printInvoice(order, discountVal);
        repo.save(order);
        notify.send("Đơn hàng " + order.orderId + " đã tạo", order.customer);
    }

    public List<Order> getHistory() { return repo.getAll(); }
}

public class bai5 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Product> products = new ArrayList<>();
        List<Customer> customers = new ArrayList<>();
        OrderService service = new OrderService(new DatabaseOrderRepository(), new EmailNotification());
        InvoiceGenerator invoiceGen = new InvoiceGenerator();

        while (true) {
            System.out.println("\n1. Thêm SP | 2. Thêm KH | 3. Tạo Đơn | 4. Xem Đơn | 5. Doanh thu | 0. Thoát");
            int choice = Integer.parseInt(sc.nextLine());
            if (choice == 0) break;

            switch (choice) {
                case 1:
                    products.add(new Product("SP01", "Laptop", 15000000, "Điện tử"));
                    System.out.println("Đã thêm sản phẩm SP01");
                    break;
                case 2:
                    customers.add(new Customer("Nguyễn Văn A", "a@example.com", "0123456789"));
                    System.out.println("Đã thêm khách hàng");
                    break;
                case 3:
                    if (customers.isEmpty() || products.isEmpty()) break;
                    Order order = new Order("ORD001", customers.get(0));
                    order.items.add(new OrderItem(products.get(0), 1));
                    service.processOrder(order, new PercentageDiscount(10), new CreditCardPayment(), invoiceGen);
                    break;
                case 4:
                    for (Order o : service.getHistory())
                        System.out.println(o.orderId + " - " + o.customer.name + " - " + (long)o.finalAmount);
                    break;
                case 5:
                    double totalRevenue = service.getHistory().stream().mapToDouble(o -> o.finalAmount).sum();
                    System.out.println("Tổng doanh thu: " + (long)totalRevenue);
                    break;
            }
        }
    }
}