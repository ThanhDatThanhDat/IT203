package Session7.bai2;

class Student {
    String name;
    Student(String name) {
        this.name = name;
    }
}

public class bai2 {
    public static void main(String[] args) {
        System.out.println("--- 1. Bien nguyen thuy (int) ---");
        int a = 10;
        int b = a;
        b = 20;

        System.out.println("Gia tri a: " + a);
        System.out.println("Gia tri b: " + b);
        System.out.println("=> Ket luan: Thay doi b khong lam anh huong den a.");

        System.out.println("\n--- 2. Bien tham chieu (Object) ---");
        Student sv1 = new Student("Nguyen Van A");
        Student sv2 = sv1;

        sv2.name = "Tran Thi B";

        System.out.println("Ten cua sv1: " + sv1.name);
        System.out.println("Ten cua sv2: " + sv2.name);
        System.out.println("=> Ket luan: sv1 và sv2 cung tro vao 1 vung nho.");
    }
}