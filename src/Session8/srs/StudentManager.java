package Session8.srs;

public class StudentManager {
    private Student[] list = new Student[100];
    private int count = 0;

    // Kiểm tra danh sách có rỗng không
    public boolean isEmpty() {
        return count == 0;
    }

    // Lấy sinh viên theo ID
    public Student findById(String id) {
        for (int i = 0; i < count; i++) {
            if (list[i].getId().equals(id)) {
                return list[i];
            }
        }
        return null; // Không tìm thấy
    }

    // [FR1] Thêm sinh viên vào mảng
    public boolean add(Student s) {
        if (count >= 100) return false;
        if (findById(s.getId()) != null) return false; // Trùng mã

        list[count++] = s;
        return true;
    }

    // [FR2] Hiển thị tất cả
    public void displayAll() {
        if (isEmpty()) {
            System.out.println("Danh sách trống!");
            return;
        }
        printLine();
        System.out.printf("| %-8s | %-18s | %-4s | %-8s | %-7s | %-10s |\n", "Mã SV", "Họ Tên", "Tuổi", "GT", "ĐTB", "Xếp Loại");
        printLine();
        for (int i = 0; i < count; i++) list[i].display();
        printLine();
    }

    // [FR3] Tìm kiếm theo từ khóa
    public void search(String key) {
        boolean found = false;
        key = key.toLowerCase();
        System.out.println("Kết quả tìm kiếm:");
        for (int i = 0; i < count; i++) {
            if (list[i].getId().toLowerCase().contains(key) || list[i].getName().toLowerCase().contains(key)) {
                list[i].display();
                found = true;
            }
        }
        if (!found) System.out.println("Không tìm thấy sinh viên nào!");
    }

    // [FR5] Xóa sinh viên
    public boolean delete(String id) {
        int index = -1;
        // Tìm vị trí
        for (int i = 0; i < count; i++) {
            if (list[i].getId().equals(id)) {
                index = i;
                break;
            }
        }

        if (index != -1) {
            // Dịch chuyển mảng
            for (int j = index; j < count - 1; j++) {
                list[j] = list[j + 1];
            }
            count--;
            return true;
        }
        return false;
    }

    // [FR7] Sắp xếp (1: Giảm dần ĐTB, 2: Tên A-Z)
    public void sort(int type) {
        for (int i = 0; i < count - 1; i++) {
            for (int j = i + 1; j < count; j++) {
                boolean check = (type == 1) ? (list[i].getAvg() < list[j].getAvg())
                        : (list[i].getName().compareTo(list[j].getName()) > 0);
                if (check) {
                    Student temp = list[i];
                    list[i] = list[j];
                    list[j] = temp;
                }
            }
        }
        System.out.println("Đã sắp xếp danh sách!");
        displayAll();
    }

    // [FR8] Thống kê chi tiết (Cập nhật)
    public void statistics() {
        if (isEmpty()) {
            System.out.println("Danh sách trống, không thể thống kê!");
            return;
        }

        int g = 0, k = 0, tb = 0, y = 0;
        double totalScore = 0;

        // Khởi tạo max/min là sinh viên đầu tiên trong danh sách
        Student maxStudent = list[0];
        Student minStudent = list[0];

        for (int i = 0; i < count; i++) {
            Student s = list[i]; // Lấy sinh viên hiện tại ra biến tạm cho gọn

            // 1. Thống kê số lượng theo loại
            String r = s.getRank();
            switch (r) {
                case "Giỏi": g++; break;
                case "Khá": k++; break;
                case "Trung bình": tb++; break;
                default: y++; break;
            }

            // 2. Cộng dồn để tính trung bình chung cả lớp
            totalScore += s.getAvg();

            // 3. Tìm sinh viên điểm cao nhất
            if (s.getAvg() > maxStudent.getAvg()) {
                maxStudent = s;
            }

            // 4. Tìm sinh viên điểm thấp nhất
            if (s.getAvg() < minStudent.getAvg()) {
                minStudent = s;
            }
        }

        double classAvg = totalScore / count;

        // --- HIỂN THỊ KẾT QUẢ ---
        System.out.println("================= BẢNG THỐNG KÊ =================");

        // Hiển thị số lượng xếp loại
        System.out.println("1. Phân loại học lực:");
        System.out.printf("   - Giỏi:       %d sinh viên\n", g);
        System.out.printf("   - Khá:        %d sinh viên\n", k);
        System.out.printf("   - Trung bình: %d sinh viên\n", tb);
        System.out.printf("   - Yếu:        %d sinh viên\n", y);

        System.out.println("-------------------------------------------------");

        // Hiển thị điểm TB chung
        System.out.printf("2. Điểm trung bình chung toàn lớp: %.2f\n", classAvg);

        System.out.println("-------------------------------------------------");

        // Hiển thị Max/Min
        System.out.println("3. Sinh viên có thành tích cao nhất:");
        System.out.printf("   - %s (ID: %s) - Điểm TB: %.2f\n", maxStudent.getName(), maxStudent.getId(), maxStudent.getAvg());

        System.out.println("\n4. Sinh viên có thành tích thấp nhất:");
        System.out.printf("   - %s (ID: %s) - Điểm TB: %.2f\n", minStudent.getName(), minStudent.getId(), minStudent.getAvg());

        System.out.println("=================================================");
    }

    private void printLine() {
        System.out.println("-------------------------------------------------------------------------");
    }
}

