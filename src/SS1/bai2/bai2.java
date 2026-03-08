package SS1.bai2;

import java.util.Scanner;

public class bai2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.print("Nhập tổng số người dùng: ");
            int tongNguoi = Integer.parseInt(scanner.nextLine());

            System.out.print("Nhập số lượng nhóm muốn chia: ");
            int soNhom = Integer.parseInt(scanner.nextLine());

            int moiNhom = tongNguoi / soNhom;
            System.out.println("Mỗi nhóm sẽ có: " + moiNhom + " người.");

        } catch (NumberFormatException e) {
            System.out.println("Lỗi: Vui lòng nhập số nguyên hợp lệ!");
        } catch (ArithmeticException e) {
            System.out.println("Lỗi: Không thể chia cho 0!");
        } finally {
            System.out.println("Thực hiện dọn dẹp tài nguyên trong finally...");
            scanner.close();
        }

        System.out.println("Chương trình kết thúc an toàn.");
    }
}