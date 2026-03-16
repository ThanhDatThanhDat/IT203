package SS7.bai6;

import java.util.Scanner;

interface DiscountStrategy { double getDiscount(double amount); }
interface PaymentMethod { void pay(double amount); }
interface NotificationService { void notifyStatus(String msg); }

interface SalesChannelFactory {
    DiscountStrategy createDiscount();
    PaymentMethod createPayment();
    NotificationService createNotification();
}

class WebsiteDiscount implements DiscountStrategy {
    @Override public double getDiscount(double amount) { return amount * 0.1; }
}
class OnlinePayment implements PaymentMethod {
    @Override public void pay(double amount) { System.out.println("Xử lý thanh toán thẻ online: " + (long)amount); }
}
class EmailNotification implements NotificationService {
    @Override public void notifyStatus(String msg) { System.out.println("Gửi email xác nhận: " + msg); }
}
class WebsiteFactory implements SalesChannelFactory {
    @Override public DiscountStrategy createDiscount() { return new WebsiteDiscount(); }
    @Override public PaymentMethod createPayment() { return new OnlinePayment(); }
    @Override public NotificationService createNotification() { return new EmailNotification(); }
}

class MobileDiscount implements DiscountStrategy {
    @Override public double getDiscount(double amount) { return amount * 0.15; }
}
class MomoPayment implements PaymentMethod {
    @Override public void pay(double amount) { System.out.println("Xử lý thanh toán MoMo tích hợp: " + (long)amount); }
}
class PushNotification implements NotificationService {
    @Override public void notifyStatus(String msg) { System.out.println("Gửi push notification: " + msg); }
}
class MobileAppFactory implements SalesChannelFactory {
    @Override public DiscountStrategy createDiscount() { return new MobileDiscount(); }
    @Override public PaymentMethod createPayment() { return new MomoPayment(); }
    @Override public NotificationService createNotification() { return new PushNotification(); }
}

class POSDiscount implements DiscountStrategy {
    @Override public double getDiscount(double amount) { return 50000; }
}
class CashPayment implements PaymentMethod {
    @Override public void pay(double amount) { System.out.println("In hóa đơn và thu tiền mặt: " + (long)amount); }
}
class POSNotification implements NotificationService {
    @Override public void notifyStatus(String msg) { System.out.println("Hệ thống POS thông báo: " + msg); }
}
class StorePOSFactory implements SalesChannelFactory {
    @Override public DiscountStrategy createDiscount() { return new POSDiscount(); }
    @Override public PaymentMethod createPayment() { return new CashPayment(); }
    @Override public NotificationService createNotification() { return new POSNotification(); }
}

class OrderService {
    public void createOrder(double amount, SalesChannelFactory factory) {
        DiscountStrategy discount = factory.createDiscount();
        PaymentMethod payment = factory.createPayment();
        NotificationService notification = factory.createNotification();

        double discountVal = discount.getDiscount(amount);
        double finalAmount = amount - discountVal;

        payment.pay(finalAmount);
        notification.notifyStatus("Đơn hàng thành công. Giảm giá: " + (long)discountVal);
    }
}

public class bai6 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        OrderService orderService = new OrderService();
        SalesChannelFactory factory = null;

        while (true) {
            System.out.println("\n--- CHỌN KÊNH BÁN HÀNG ---");
            System.out.println("1. Website | 2. Mobile App | 3. Store POS | 0. Thoát");
            String choice = sc.nextLine();

            if (choice.equals("0")) break;

            switch (choice) {
                case "1":
                    System.out.println("Bạn đã chọn kênh Website");
                    factory = new WebsiteFactory();
                    break;
                case "2":
                    System.out.println("Bạn đã chọn kênh Mobile App");
                    factory = new MobileAppFactory();
                    break;
                case "3":
                    System.out.println("Bạn đã chọn kênh Store POS");
                    factory = new StorePOSFactory();
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ");
                    continue;
            }

            System.out.print("Nhập giá trị sản phẩm: ");
            double amount = Double.parseDouble(sc.nextLine());
            orderService.createOrder(amount, factory);
        }
    }
}