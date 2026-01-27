package Session3.baitap;

import java.util.Scanner;

public class bai05 {
    public static int deleteBook(int[] arr, int n, int bookId) {
        int pos = -1;

        for (int i = 0; i < n; i++) {
            if (arr[i] == bookId) {
                pos = i;
                break;
            }
        }

        if (pos == -1) {
            System.out.println("Không tìm thấy sách có mã: " + bookId);
            return n;
        }
        for (int i = pos; i < n - 1; i++) {
            arr[i] = arr[i + 1];
        }
        System.out.println("\nĐã xóa sách có mã: " + bookId);
        return n - 1;
    }

    // In mảng đến n phần tử
    public static void displayBooks(int[] arr, int n) {
        if (n == 0) {
            System.out.println("Kho sách rỗng");
            return;
        }
        for (int i = 0; i < n; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int[] books = {101, 102, 103, 104, 105};
        int n = books.length;

        while (true) {
            System.out.println("\nDanh sách sách hiện tại:");
            displayBooks(books, n);

            if (n == 0) {
                break;
            }

            System.out.print("Nhập mã sách cần xóa: ");
            int bookId = sc.nextInt();

            n = deleteBook(books, n, bookId);

            System.out.println("Danh sách sách hiện tại:");
            displayBooks(books, n);

            System.out.print("Bạn có muốn xóa tiếp không? (1: Có / 0: Không): ");
            int choice = sc.nextInt();
            if (choice == 0) {
                break;
            }
        }

        System.out.println("Kết thúc chương trình.");
    }
}