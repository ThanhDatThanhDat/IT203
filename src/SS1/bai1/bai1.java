package SS1.bai1;

import java.util.Scanner;

public class bai1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.print("Vui lòng nhập năm sinh của bạn: ");
            String input = scanner.nextLine();

            int namsinh = Integer.parseInt(input);
            int tuoi = 2026 - namsinh;

            System.out.println("Tuổi của bạn là: " + tuoi);

        } catch (NumberFormatException e) {
            System.out.println("Lỗi: Vui lòng nhập số nguyên thay vì chữ hoặc ký tự đặc biệt!");
        } finally {
            System.out.println("Thực hiện dọn dẹp tài nguyên trong finally...");
            scanner.close();
        }
    }
}