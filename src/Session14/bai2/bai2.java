package Session14.bai2;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class bai2 {
    public static void main(String[] args) {
        Map<String, String> danhMucThuoc = new HashMap<>();

        danhMucThuoc.put("T01", "Paracetamol");
        danhMucThuoc.put("T02", "Ibuprofen");
        danhMucThuoc.put("T03", "Amoxicillin");
        danhMucThuoc.put("T04", "Vitamin C");
        danhMucThuoc.put("T05", "Omeprazole");

        Scanner scanner = new Scanner(System.in);
        System.out.print("Nhập mã thuốc cần tra cứu: ");
        String maNhap = scanner.nextLine().toUpperCase();

        if (danhMucThuoc.containsKey(maNhap)) {
            String tenThuoc = danhMucThuoc.get(maNhap);
            System.out.println("Kết quả -> Tên thuốc: " + tenThuoc);
        } else {
            System.out.println("Kết quả -> Thuốc không có trong danh mục BHYT.");
        }

        scanner.close();
    }
}