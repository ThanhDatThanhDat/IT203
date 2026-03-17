package SS8.bai6;

import java.util.Scanner;

interface DiscountStrategy {
    double calculateDiscount(double amount);
}

interface PaymentMethod {
    void processPayment(double amount);
}

interface NotificationService {
    void sendNotification(String message);
}

class WebsiteDiscount implements DiscountStrategy {
    public double calculateDiscount(double amount) { return amount * 0.1; }
}

class CreditCardPayment implements PaymentMethod {
    public void processPayment(double amount) { System.out.println("Xử lý thanh toán thẻ tín dụng: " + String.format("%.0f", amount)); }
}

class EmailNotification implements NotificationService {
    public void sendNotification(String message) { System.out.println("Gửi email: " + message); }
}

class MobileAppDiscount implements DiscountStrategy {
    public double calculateDiscount(double amount) { return amount * 0.15; }
}

class MomoPayment implements PaymentMethod {
    public void processPayment(double amount) { System.out.println("Xử lý thanh toán MoMo: " + String.format("%.0f", amount)); }
}

class PushNotification implements NotificationService {
    public void sendNotification(String message) { System.out.println("Gửi push notification: " + message); }
}

class MemberDiscount implements DiscountStrategy {
    public double calculateDiscount(double amount) { return amount * 0.05; }
}

class CODPayment implements PaymentMethod {
    public void processPayment(double amount) { System.out.println("Xử lý thanh toán tiền mặt (COD): " + String.format("%.0f", amount)); }
}

class PrintReceipt implements NotificationService {
    public void sendNotification(String message) { System.out.println("In hóa đơn tại quầy: " + message); }
}

interface SalesChannelFactory {
    DiscountStrategy createDiscountStrategy();
    PaymentMethod createPaymentMethod();
    NotificationService createNotificationService();
}

class WebsiteFactory implements SalesChannelFactory {
    public DiscountStrategy createDiscountStrategy() { return new WebsiteDiscount(); }
    public PaymentMethod createPaymentMethod() { return new CreditCardPayment(); }
    public NotificationService createNotificationService() { return new EmailNotification(); }
}

class MobileAppFactory implements SalesChannelFactory {
    public DiscountStrategy createDiscountStrategy() { return new MobileAppDiscount(); }
    public PaymentMethod createPaymentMethod() { return new MomoPayment(); }
    public NotificationService createNotificationService() { return new PushNotification(); }
}

class POSFactory implements SalesChannelFactory {
    public DiscountStrategy createDiscountStrategy() { return new MemberDiscount(); }
    public PaymentMethod createPaymentMethod() { return new CODPayment(); }
    public NotificationService createNotificationService() { return new PrintReceipt(); }
}

class OrderService {
    private DiscountStrategy discountStrategy;
    private PaymentMethod paymentMethod;
    private NotificationService notificationService;

    public OrderService(SalesChannelFactory factory) {
        this.discountStrategy = factory.createDiscountStrategy();
        this.paymentMethod = factory.createPaymentMethod();
        this.notificationService = factory.createNotificationService();
    }

    public void processOrder(String product, double price, int quantity) {
        double total = price * quantity;
        double discount = discountStrategy.calculateDiscount(total);
        double finalAmount = total - discount;

        System.out.println("Sản phẩm: " + product + ", Số lượng: " + quantity);
        System.out.println("Áp dụng giảm giá: " + String.format("%.0f", discount));
        paymentMethod.processPayment(finalAmount);
        notificationService.sendNotification("Đơn hàng thành công");
    }
}

public class bai6 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        SalesChannelFactory factory = null;

        while (true) {
            System.out.println("\n--- CHỌN KÊNH BÁN HÀNG ---");
            System.out.println("1. Website | 2. Mobile App | 3. POS tại cửa hàng | 0. Thoát");
            int choice = sc.nextInt();

            if (choice == 0) break;

            switch (choice) {
                case 1 -> {
                    factory = new WebsiteFactory();
                    System.out.println("Bạn đã chọn kênh Website");
                }
                case 2 -> {
                    factory = new MobileAppFactory();
                    System.out.println("Bạn đã chọn kênh Mobile App");
                }
                case 3 -> {
                    factory = new POSFactory();
                    System.out.println("Bạn đã chọn kênh POS");
                }
                default -> {
                    System.out.println("Lựa chọn sai.");
                    continue;
                }
            }

            if (factory != null) {
                sc.nextLine();
                System.out.print("Nhập tên sản phẩm: ");
                String name = sc.nextLine();
                System.out.print("Nhập giá sản phẩm: ");
                double price = sc.nextDouble();
                System.out.print("Nhập số lượng: ");
                int qty = sc.nextInt();

                OrderService orderService = new OrderService(factory);
                orderService.processOrder(name, price, qty);
            }
        }
        sc.close();
    }
}