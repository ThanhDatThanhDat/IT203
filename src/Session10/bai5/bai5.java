package Session10.bai5;

public class bai5 {
    static abstract class Employee {

        protected String name;
        protected double baseSalary;

        public Employee(String name, double baseSalary) {
            this.name = name;
            this.baseSalary = baseSalary;
        }

        public abstract double calculateSalary();

        public void displayInfo() {
            System.out.println("Tên: " + name);
            System.out.println("Lương cơ bản: " + baseSalary);
            System.out.println("Tổng lương: " + calculateSalary());
            System.out.println("---------------------------");
        }
    }

    interface BonusCalculator {
        double getBonus();
    }

    static class OfficeStaff extends Employee {

        public OfficeStaff(String name, double baseSalary) {
            super(name, baseSalary);
        }

        @Override
        public double calculateSalary() {
            return baseSalary;
        }
    }

    static class Manager extends Employee implements BonusCalculator {

        private double bonus;

        public Manager(String name, double baseSalary, double bonus) {
            super(name, baseSalary);
            this.bonus = bonus;
        }

        @Override
        public double getBonus() {
            return bonus;
        }

        @Override
        public double calculateSalary() {
            return baseSalary + getBonus();
        }
    }

    public static void main(String[] args) {

        Employee staff = new OfficeStaff("Nguyễn Văn A", 8000000);
        Employee manager = new Manager("Trần Thị B", 15000000, 5000000);

        System.out.println("=== BẢNG LƯƠNG NHÂN VIÊN ===");
        staff.displayInfo();
        manager.displayInfo();
    }
}