package SS5.KiemTra;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

class Product {
    private int id;
    private String name;
    private double price;
    private int quantity;
    private String category;

    public Product(int id, String name, double price, int quantity, String category) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.category = category;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Tên: " + name + ", Giá: " + price + ", Số Lượng: " + quantity + ", Loại: " + category;
    }
}

class InvalidProductException extends Exception {
    public InvalidProductException(String message) {
        super(message);
    }
}

public class KiemTra {
    private static List<Product> products = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            displayMenu();
            try {
                int choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1: createProduct();
                        break;
                    case 2: displayProducts();
                        break;
                    case 3: updateQuantity();
                        break;
                    case 4: deleteOutOfStock();
                        break;
                    case 5:
                        return;
                    default: System.out.println("Chọn lại.");
                }
            } catch (Exception e) {
                System.out.println("Lỗi");
            }
        }
    }

    private static void displayMenu() {
        System.out.println("\n========= PRODUCT MANAGEMENT SYSTEM =========");
        System.out.println("1. Thêm sản phẩm mới");
        System.out.println("2. Hiển thị danh sách sản phẩm");
        System.out.println("3. Cập nhật số lượng theo ID");
        System.out.println("4. Xóa sản phẩm đã hết hàng");
        System.out.println("5. Thoát chương trìrình");
        System.out.print("Lựa chọn của bạn: ");
    }

    private static void createProduct() {
        try {
            System.out.print("ID: ");
            int id = Integer.parseInt(scanner.nextLine());

            if (products.stream().anyMatch(p -> p.getId() == id)) {
                throw new InvalidProductException("ID đã tồn tại.");
            }

            System.out.print("Tên: ");
            String name = scanner.nextLine();
            System.out.print("Giá: ");
            double price = Double.parseDouble(scanner.nextLine());
            System.out.print("Số lượng: ");
            int quantity = Integer.parseInt(scanner.nextLine());
            System.out.print("Danh mục: ");
            String category = scanner.nextLine();

            products.add(new Product(id, name, price, quantity, category));
            System.out.println("Thêm thành công.");
        } catch (InvalidProductException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void displayProducts() {
        if (products.isEmpty()) {
            System.out.println("Trống.");
        } else {
            products.forEach(System.out::println);
        }
    }

    private static void updateQuantity() {
        System.out.print("Nhập ID: ");
        int id = Integer.parseInt(scanner.nextLine());

        Optional<Product> opt = products.stream()
                .filter(p -> p.getId() == id)
                .findFirst();

        try {
            if (opt.isPresent()) {
                System.out.print("SL mới: ");
                int qty = Integer.parseInt(scanner.nextLine());
                opt.get().setQuantity(qty);
                System.out.println("Xong.");
            } else {
                throw new InvalidProductException("Không tìm thấy ID.");
            }
        } catch (InvalidProductException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void deleteOutOfStock() {
        products.removeIf(p -> p.getQuantity() == 0);
        System.out.println("Đã xóa sản phẩm có SL = 0.");
    }
}