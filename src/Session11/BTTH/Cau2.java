package Session11.BTTH;

import java.util.Scanner;

public class Cau2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // PHẦN 1: CHÈN VÀ DỊCH CHUYỂN (INSERT & SHIFT)
        System.out.print("Nhập số phần tử n: ");
        int n = sc.nextInt();

        // Tạo mảng có kích thước n + 1 để đủ chỗ chèn
        int[] arr = new int[n + 1];

        System.out.println("Nhập các phần tử:");
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        System.out.print("Nhập giá trị cần chèn x: ");
        int x = sc.nextInt();
        System.out.print("Nhập vị trí chèn k (0 <= k <= n): ");
        int k = sc.nextInt();

        // Dịch các phần tử sang phải từ cuối về vị trí k
        for (int i = n; i > k; i--) {
            arr[i] = arr[i - 1];
        }
        // Gán giá trị x vào vị trí k
        arr[k] = x;

        System.out.println("Mảng sau khi chèn:");
        for (int i = 0; i <= n; i++) {
            System.out.print(arr[i] + " ");
        }


        // PHẦN 2: LOẠI BỎ PHẦN TỬ LẶP (KHÔNG DÙNG MẢNG PHỤ)

        System.out.print("\n\nNhập số phần tử m: ");
        int m = sc.nextInt();

        int[] arr2 = new int[m];

        System.out.println("Nhập các phần tử:");
        for (int i = 0; i < m; i++) {
            arr2[i] = sc.nextInt();
        }

        // newSize dùng để theo dõi kích thước mảng sau khi thu gọn
        int newSize = m;

        // Duyệt từng phần tử
        for (int i = 0; i < newSize; i++) {
            // So sánh với các phần tử phía sau
            for (int j = i + 1; j < newSize; j++) {
                if (arr2[i] == arr2[j]) {
                    for (int k2 = j; k2 < newSize - 1; k2++) {
                        arr2[k2] = arr2[k2 + 1];
                    }
                    newSize--;
                    j--;
                }
            }
        }

        System.out.println("Mảng sau khi loại bỏ trùng:");
        for (int i = 0; i < newSize; i++) {
            System.out.print(arr2[i] + " ");
        }

        System.out.println("\nKích thước mới: " + newSize);

        sc.close();
    }
}
