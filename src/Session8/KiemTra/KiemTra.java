package Session8.KiemTra;

import java.util.Scanner;

class Student {
    private String id;
    private String name;
    private double score;

    public Student() {}

    public Student(String id, String name, double score) {
        this.id = id;
        this.name = name;
        this.score = score;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public double getScore() { return score; }
    public void setScore(double score) { this.score = score; }

    public String getRank() {
        if (this.score >= 8.0) return "Gioi";
        if (this.score >= 6.5) return "Kha";
        return "Trung Binh";
    }

    @Override
    public String toString() {
        return String.format("ID: %-7s | Tên: %-15s | Điểm: %-5.1f | Học lực: %-10s",
                id, name, score, getRank());
    }
}

public class KiemTra {
    private static Scanner sc = new Scanner(System.in);
    private static Student[] students = new Student[100];
    private static int n = 0;

    public static void main(String[] args) {
        int choice;
        do {
            System.out.println("\n===== QUẢN LÝ ĐIỂM SINH VIÊN =====");
            System.out.println("1. Nhập danh sách sinh viên ");
            System.out.println("2. Hiển thị danh sách sinh viên");
            System.out.println("3. Tìm kiếm sinh viên theo Học lực ");
            System.out.println("4. Sắp xếp theo học lực giảm dần");
            System.out.println("5. Thoát ");
            System.out.println("==================================");
            System.out.print("Chọn chức năng: ");

            try {
                choice = Integer.parseInt(sc.nextLine());
            } catch (Exception e) {
                choice = -1;
            }

            switch (choice) {
                case 1:
                    inputList();
                    break;
                case 2:
                    displayList();
                    break;
                case 3:
                    searchByRank();
                    break;
                case 4:
                    sortDescending();
                    break;
                case 5:
                    System.out.println("\nThoát chương trình!");
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ. Vui lòng chọn từ 1-5!");
            }
        } while (choice != 5);
    }

    private static void inputList() {
        System.out.print("Nhập số lượng sinh viên cần thêm: ");
        int count = Integer.parseInt(sc.nextLine());
        for (int i = 0; i < count; i++) {
            System.out.println("\nSinh viên thứ " + (n + 1) + ":");

            String id;
            while (true) {
                System.out.print("Mã SV (SVxxx): ");
                id = sc.nextLine();
                if (id.startsWith("SV") && id.length() == 5) {
                    break;
                }
                System.out.println("ID sai định dạng! Phải bắt đầu bằng 'SV' và có 3 chữ số phía sau.");
            }

            System.out.print("Họ và tên: ");
            String name = sc.nextLine();

            System.out.print("Điểm trung bình: ");
            double score = Double.parseDouble(sc.nextLine());

            students[n++] = new Student(id, name, score);
        }
        System.out.println("Thêm thành công!");
    }

    private static void displayList() {
        if (n == 0) {
            System.out.println("Danh sách sinh viên dang trống.");
            return;
        }
        System.out.print("===== Danh sách sinh viên =====\n");
        for (int i=0; i<n; i++){
            System.out.println(students[i].toString());
        }
    }

    private static void searchByRank() {
        System.out.print("Nhập loại học lực cần tìm (Gioi/Kha/Trung Binh): ");
        String rankInput = sc.nextLine();
        boolean found = false;
        for (int i = 0; i < n; i++) {
            if (students[i].getRank().equalsIgnoreCase(rankInput)) {
                System.out.println(students[i].toString());
                found = true;
            }
        }
        if (!found) System.out.println("Không tìm thấy sinh viên nào xếp loại " + rankInput);
    }
    private static void sortDescending() {
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                if (students[i].getScore() < students[j].getScore()) {
                    Student temp = students[i];
                    students[i] = students[j];
                    students[j] = temp;
                }
            }
        }
        System.out.println("Đã sắp xếp danh sách theo thứ tự giảm dần.");
        displayList();
    }
}