package SS7.bai3;

interface PaymentMethod {
    void processPayment(double amount);
}

interface CODPayable extends PaymentMethod {
    void processCOD(double amount);
}

interface CardPayable extends PaymentMethod {
    void processCreditCard(double amount);
}

interface EWalletPayable extends PaymentMethod {
    void processMomo(double amount);
}

class CODPayment implements CODPayable {
    @Override
    public void processPayment(double amount) {
        processCOD(amount);
    }

    @Override
    public void processCOD(double amount) {
        System.out.println("Xử lý thanh toán COD: " + (long)amount + " - Thành công");
    }
}

class CreditCardPayment implements CardPayable {
    @Override
    public void processPayment(double amount) {
        processCreditCard(amount);
    }

    @Override
    public void processCreditCard(double amount) {
        System.out.println("Xử lý thanh toán thẻ tín dụng: " + (long)amount + " - Thành công");
    }
}

class MomoPayment implements EWalletPayable {
    @Override
    public void processPayment(double amount) {
        processMomo(amount);
    }

    @Override
    public void processMomo(double amount) {
        System.out.println("Xử lý thanh toán MoMo: " + (long)amount + " - Thành công");
    }
}

class PaymentProcessor {
    public void process(PaymentMethod method, double amount) {
        method.processPayment(amount);
    }
}

public class bai3 {
    public static void main(String[] args) {
        PaymentProcessor processor = new PaymentProcessor();

        System.out.print("COD: ");
        processor.process(new CODPayment(), 500000);

        System.out.print("Thẻ tín dụng: ");
        processor.process(new CreditCardPayment(), 1000000);

        System.out.print("Ví MoMo: ");
        processor.process(new MomoPayment(), 750000);

        System.out.println("\n--- Kiểm tra LSP ---");
        PaymentMethod flexibleMethod;

        flexibleMethod = new CreditCardPayment();
        System.out.print("Sử dụng thẻ: ");
        processor.process(flexibleMethod, 1000000);

        flexibleMethod = new MomoPayment();
        System.out.print("Thay thế bằng MoMo: ");
        processor.process(flexibleMethod, 1000000);
    }
}