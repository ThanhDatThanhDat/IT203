package Session14.bai1;

import java.util.LinkedHashSet;
import java.util.Set;

public class bai1 {
    public static void main(String[] args) {
        Set<String> danhSachCho = new LinkedHashSet<>();

        String[] input = {
                "Nguyễn Văn A – Yên Bái",
                "Trần Thị B – Thái Bình",
                "Nguyễn Văn A – Yên Bái",
                "Lê Văn C – Hưng Yên"
        };

        for (String benhNhan : input) {
            danhSachCho.add(benhNhan);
        }

        System.out.println("Danh sách gọi khám:");
        for (String ten : danhSachCho) {
            System.out.println("- " + ten);
        }
    }
}