package Session11.BTTH;

import java.util.Scanner;

public class Cau1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // Phần 2: Tìm số còn thiếu trong dãy (Missing Number)
        // Câu 2: Kỹ thuật xử lý Mảng 1 chiều
        System.out.print("Nhập số lượng phần tử n: ");
        int n = sc.nextInt();
        if (n <= 0) {
            System.out.println("Số lượng phải lớn hơn 0.");
            return;
        }

        int[] array = new int[n];
        for (int i = 0; i < n; i++) {
            System.out.print("Nhập phần tử thứ " + (i + 1) + ": ");
            array[i] = sc.nextInt();
        }

        int expectedSum = n * (n + 1) / 2;

        int actualSum = 0;
        for (int i = 0; i < n; i++) {
            actualSum += array[i];
        }

        int missing = expectedSum - actualSum;
        System.out.println("Số còn thiếu là: " + missing);
    }
}
