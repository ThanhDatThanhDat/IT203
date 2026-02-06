package Session9.bai5;

import java.util.ArrayList;
import java.util.List;

abstract class Employee {
    private String name;

    public Employee(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public abstract double calculateSalary();
}

class OfficeEmployee extends Employee {
    private double monthlyRate = 1500;

    public OfficeEmployee(String name) {
        super(name);
    }

    @Override
    public double calculateSalary() {
        return monthlyRate;
    }
}

class ProductionEmployee extends Employee {
    private int itemsProduced;
    private double amountPerItem = 20;

    public ProductionEmployee(String name, int itemsProduced) {
        super(name);
        this.itemsProduced = itemsProduced;
    }

    @Override
    public double calculateSalary() {
        return itemsProduced * amountPerItem;
    }
}

public class bai5 {
    public static void main(String[] args) {
        List<Employee> list = new ArrayList<>();

        list.add(new OfficeEmployee("Nguyễn Văn A"));
        list.add(new ProductionEmployee("Trần Thị B", 150));
        list.add(new OfficeEmployee("Lê Văn C"));

        double totalSalary = 0;

        System.out.println("--- CHI TIẾT BẢNG LƯƠNG ---");
        for (Employee e : list) {
            double salary = e.calculateSalary();
            System.out.printf("Nhân viên: %-15s | Lương: %.2f\n", e.getName(), salary);
            totalSalary += salary;
        }

        System.out.println("---------------------------");
        System.out.println("TỔNG LƯƠNG HỆ THỐNG: " + totalSalary);
    }
}