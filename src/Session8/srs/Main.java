package Session8.srs;
import java.util.Scanner;

public class Main {
    private static StudentManager manager = new StudentManager();
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int choice;
        do {
            System.out.println("\n===== QUẢN LÝ SINH VIÊN =====");
            System.out.println("1. Thêm sinh viên mới");
            System.out.println("2. Hiển thị tất cả sinh viên");
            System.out.println("3. Tìm kiếm sinh viên");
            System.out.println("4. Cập nhật thông tin sinh viên");
            System.out.println("5. Xóa sinh viên");
            System.out.println("6. Tính điểm và xếp loại");
            System.out.println("7. Sắp xếp sinh viên");
            System.out.println("8. Thống kê");
            System.out.println("9. Thoát");
            System.out.print("Chọn chức năng (1-9): ");

            try {
                choice = Integer.parseInt(sc.nextLine());
            } catch (Exception e) { choice = -1; }

            switch (choice) {
                case 1: inputStudent(); break;
                case 2: manager.displayAll(); break;
                case 3:
                    System.out.print("Nhập mã hoặc tên cần tìm: ");
                    manager.search(sc.nextLine());
                    break;
                case 4: updateStudentInfo(); break;
                case 5:
                    System.out.print("Nhập mã SV cần xóa: ");
                    boolean deleted = manager.delete(sc.nextLine());
                    System.out.println(deleted ? "Đã xóa thành công!" : "Không tìm thấy mã SV!");
                    break;
                case 6: System.out.println("Hệ thống luôn tự động cập nhật điểm khi thêm/sửa."); break;
                case 7:
                    System.out.println("1. Điểm TB giảm dần | 2. Tên A-Z");
                    try {
                        int type = Integer.parseInt(sc.nextLine());
                        manager.sort(type);
                    } catch(Exception e) { System.out.println("Lựa chọn sai!"); }
                    break;
                case 8: manager.statistics(); break;
                case 9: System.out.println("Thoát chương trình!"); break;
                default: System.out.println("Lựa chọn không hợp lệ!");
            }
        } while (choice != 9);
    }

    // Hàm hỗ trợ nhập liệu để thêm mới
    private static void inputStudent() {
        System.out.print("Mã SV: "); String id = sc.nextLine();
        // Kiểm tra trùng ngay tại màn hình nhập
        if (manager.findById(id) != null) {
            System.out.println("Lỗi: Mã sinh viên đã tồn tại!");
            return;
        }
        System.out.print("Tên: "); String name = sc.nextLine();
        System.out.print("Tuổi (18-30): "); int age = Integer.parseInt(sc.nextLine());
        System.out.print("Giới tính: "); String gender = sc.nextLine();
        System.out.print("Điểm Toán: "); double m = Double.parseDouble(sc.nextLine());
        System.out.print("Điểm Lý: "); double p = Double.parseDouble(sc.nextLine());
        System.out.print("Điểm Hóa: "); double c = Double.parseDouble(sc.nextLine());

        Student s = new Student(id, name, age, gender, m, p, c);
        manager.add(s);
        System.out.println("Thêm thành công!");
    }

    // Hàm hỗ trợ cập nhật thông tin
    private static void updateStudentInfo() {
        System.out.print("Nhập mã SV cần sửa: ");
        String id = sc.nextLine();
        Student s = manager.findById(id);

        if (s == null) {
            System.out.println("Không tìm thấy sinh viên!");
            return;
        }

        System.out.print("Tên mới: ");
        s.setName(sc.nextLine());

        System.out.print("Toán, Lý, Hóa mới (nhập lần lượt): ");
        double m = Double.parseDouble(sc.nextLine());
        double p = Double.parseDouble(sc.nextLine());
        double c = Double.parseDouble(sc.nextLine());
        s.setScores(m, p, c);

        System.out.println("Cập nhật thành công!");
    }
}
