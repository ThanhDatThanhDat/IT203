package Session16.KiemTra;

import java.util.*;

interface IRepository<T> {
    boolean add(T item);
    boolean removeById(String id);
    T findById(String id);
    List<T> findAll();
}

abstract class Product {
    protected String id, name;
    protected double price;

    public Product(String id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public abstract double calculateFinalPrice();
    public abstract void displayInfo();

    public String getId() { return id; }
    public double getPrice() { return price; }
}

class ElectronicProduct extends Product {
    private int warrantyMonths ;

    public ElectronicProduct(String id, String name, double price, int warrantyMonths ) {
        super(id, name, price);
        this.warrantyMonths  = warrantyMonths ;
    }

    @Override
    public double calculateFinalPrice() {
        return (warrantyMonths  > 12) ? price + 1000000 : price;
    }

    @Override
    public void displayInfo() {
        System.out.print("[Electronic] ID: " + id + ", Tên: " + name + ", BH: " + warrantyMonths  + " th");
    }
}

class FoodProduct extends Product {
    private double discountPercent;

    public FoodProduct(String id, String name, double price, double discountPercent) {
        super(id, name, price);
        this.discountPercent = discountPercent;
    }

    @Override
    public double calculateFinalPrice() {
        return price * (1 - discountPercent / 100);
    }

    @Override
    public void displayInfo() {
        System.out.print("[Food] ID: " + id + ", Tên: " + name + ", Giảm: " + discountPercent + "%");
    }
}

class ProductRepository implements IRepository<Product> {
    private List<Product> list = new ArrayList<>();
    private Map<String, Product> map = new HashMap<>();

    @Override
    public boolean add(Product item) {
        if (item == null || map.containsKey(item.getId())) return false;
        list.add(item);
        map.put(item.getId(), item);
        return true;
    }

    @Override
    public boolean removeById(String id) {
        Product p = map.remove(id);
        return p != null && list.remove(p);
    }

    @Override
    public Product findById(String id) { return map.get(id); }

    @Override
    public List<Product> findAll() { return new ArrayList<>(list); }

    public void showStatistics() {
        long eCount = list.stream().filter(p -> p instanceof ElectronicProduct).count();
        long fCount = list.stream().filter(p -> p instanceof FoodProduct).count();
        System.out.println("Electronic: " + eCount + " | Food: " + fCount);
    }
}

public class KiemTra {
    public static void main(String[] args) {
        ProductRepository repo = new ProductRepository();
        repo.add(new ElectronicProduct("E01", "iPhone 15", 25000000, 24));
        repo.add(new ElectronicProduct("E02", "Chuột", 500000, 6));
        repo.add(new FoodProduct("F01", "Bánh Mì", 15000, 10));
        repo.add(new FoodProduct("F02", "Sữa Tươi", 40000, 5));

        System.out.println("--- DANH SÁCH ---");
        repo.findAll().forEach(p -> {
            p.displayInfo();
            System.out.println(" -> Tổng: " + p.calculateFinalPrice());
        });

        System.out.println("\n--- SẮP XẾP GIÁ TĂNG ---");
        List<Product> sorted = repo.findAll();
        sorted.sort(Comparator.comparingDouble(Product::getPrice));
        sorted.forEach(p -> {
            p.displayInfo();
            System.out.println(" -> Giá gốc: " + p.getPrice());
        });

        System.out.println("\n--- THỐNG KÊ ---");
        repo.showStatistics();
    }
}