package SS9.KiemTra;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

abstract class Product {
    protected String id;
    protected String name;
    protected double price;

    public Product(String id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public String getId() {
        return id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public abstract void displayInfo();
}

class PhysicalProduct extends Product {
    private double weight;

    public PhysicalProduct(String id, String name, double price, double weight) {
        super(id, name, price);
        this.weight = weight;
    }

    public void setWeight(double weight) { this.weight = weight; }

    @Override
    public void displayInfo() {
        System.out.printf("[Physical] ID: %s | Name: %s | Price: %.2f | Weight: %.2f kg\n", id, name, price, weight);
    }
}

class DigitalProduct extends Product {
    private double size;

    public DigitalProduct(String id, String name, double price, double size) {
        super(id, name, price);
        this.size = size;
    }

    public void setSize(double size) { this.size = size; }

    @Override
    public void displayInfo() {
        System.out.printf("[Digital]  ID: %s | Name: %s | Price: %.2f | Size: %.2f MB\n", id, name, price, size);
    }
}

class ProductDatabase {
    private static ProductDatabase instance;
    private List<Product> products;

    private ProductDatabase() {
        products = new ArrayList<>();
    }

    public static ProductDatabase getInstance() {
        if (instance == null) {
            instance = new ProductDatabase();
        }
        return instance;
    }

    public void addProduct(Product p) {
        products.add(p);
    }

    public List<Product> getAllProducts() { return products; }

    public boolean removeProduct(String id) {
        return products.removeIf(p -> p.getId().equalsIgnoreCase(id));
    }

    public Product findById(String id) {
        return products.stream()
                .filter(p -> p.getId().equalsIgnoreCase(id))
                .findFirst()
                .orElse(null);
    }
}

class ProductFactory {
    public static Product createProduct(int type, String id, String name, double price, double extraInfo) {
        if (type == 1) {
            return new PhysicalProduct(id, name, price, extraInfo);
        }else if (type == 2) {
            return new DigitalProduct(id, name, price, extraInfo);
        }
        return null;
    }
}

public class KiemTra {
    static Scanner sc = new Scanner(System.in);
    private static final ProductDatabase db = ProductDatabase.getInstance();
    static void main(String[] args) {
        while (true){
            while (true) {
                System.out.println("\n----------------------- QUẢN LÝ SẢN PHẨM -----------------------");
                System.out.println("1. Thêm mới sản phẩm");
                System.out.println("2. Xem danh sách sản phẩm");
                System.out.println("3. Cập nhật thông tin sản phẩm");
                System.out.println("4. Xoá sản phẩm");
                System.out.println("5. Thoát");
                System.out.print("Lựa chọn của bạn: ");

                try {
                    int choice = Integer.parseInt(sc.nextLine());
                    switch (choice) {
                        case 1 :
                            addProduct();
                            break;
                        case 2 :
                            showProducts();
                            break;
                        case 3 :
                            updateProduct();
                            break;
                        case 4 :
                            deleteProduct();
                            break;
                        case 5 : {
                            System.out.println("Thoát trương chình!");
                            return;
                        }
                        default :
                            System.out.println("Lựa chọn không hợp lệ!");
                    }
                } catch (Exception e) {
                    System.out.println("Lỗi: Vui lòng nhập số!");
                }
            }
        }
    }
    private static void addProduct() {
        System.out.print("Chọn loại (1-Physical, 2-Digital): ");
        int type = Integer.parseInt(sc.nextLine());
        System.out.print("Nhập ID: ");
        String id = sc.nextLine();
        System.out.print("Nhập tên: ");
        String name = sc.nextLine();
        System.out.print("Nhập giá: ");
        double price = Double.parseDouble(sc.nextLine());
        System.out.print(type == 1 ? "Nhập trọng lượng (kg): " : "Nhập dung lượng (MB): ");
        double extra = Double.parseDouble(sc.nextLine());

        Product p = ProductFactory.createProduct(type, id, name, price, extra);
        if (p != null) {
            db.addProduct(p);
            System.out.println("Thêm thành công!");
        }
    }
    private static void showProducts() {
        List<Product> list = db.getAllProducts();
        if (list.isEmpty()) {
            System.out.println("Danh sách trống.");
        } else {
            list.forEach(Product::displayInfo);
        }
    }

    private static void updateProduct() {
        System.out.print("Nhập ID sản phẩm cần sửa: ");
        String id = sc.nextLine();
        Product p = db.findById(id);
        if (p == null) {
            System.out.println("Không tìm thấy!");
            return;
        }
        System.out.print("Tên mới: ");
        p.setName(sc.nextLine());
        System.out.print("Giá mới: ");
        p.setPrice(Double.parseDouble(sc.nextLine()));

        if (p instanceof PhysicalProduct pp) {
            System.out.print("Trọng lượng mới: ");
            pp.setWeight(Double.parseDouble(sc.nextLine()));
        } else if (p instanceof DigitalProduct dp) {
            System.out.print("Dung lượng mới: ");
            dp.setSize(Double.parseDouble(sc.nextLine()));
        }
        System.out.println("Cập nhật thành công!");
    }

    private static void deleteProduct() {
        System.out.print("Nhập ID sản phẩm cần xóa: ");
        String id = sc.nextLine();
        if (db.removeProduct(id)) {
            System.out.println("Xóa thành công!");
        } else {
            System.out.println("Không tìm thấy ID!");
        }
    }
}
