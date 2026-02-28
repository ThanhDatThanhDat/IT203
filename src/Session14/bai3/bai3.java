package Session14.bai3;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class bai3 {
    public static void main(String[] args) {
        Set<String> thanhPhanThuoc = new HashSet<>(Arrays.asList("Aspirin", "Caffeine", "Paracetamol"));
        Set<String> chatDiUng = new HashSet<>(Arrays.asList("Penicillin", "Aspirin", "Pollen"));

        System.out.println("Thành phần thuốc mới: " + thanhPhanThuoc);
        System.out.println("Danh sách bệnh nhân dị ứng: " + chatDiUng);
        System.out.println("------------------------------------------");

        Set<String> canhBaoDiUng = new HashSet<>(thanhPhanThuoc);
        canhBaoDiUng.retainAll(chatDiUng);

        Set<String> thanhPhanAnToan = new HashSet<>(thanhPhanThuoc);
        thanhPhanAnToan.removeAll(chatDiUng);

        if (!canhBaoDiUng.isEmpty()) {
            System.out.println("CẢNH BÁO DỊ ỨNG: " + canhBaoDiUng);
        } else {
            System.out.println("Thuốc an toàn, không phát hiện chất gây dị ứng.");
        }

        System.out.println("Các thành phần an toàn còn lại: " + thanhPhanAnToan);
    }
}