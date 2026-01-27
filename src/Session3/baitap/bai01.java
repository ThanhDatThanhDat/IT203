package Session3.baitap;

import java.util.Scanner;

public class bai01 {
    public static int[] addBookToLibraries(int n) {
        Scanner sc = new Scanner(System.in);
        int[] books = new int[n];

        System.out.println("Nhập " + n + " mã sách:");
        for (int i = 0; i < n; i++) {
            System.out.print("Sách thứ " + (i + 1) + ": ");
            books[i] = sc.nextInt();
        }
        return books;
    }
    public static void displayLibraries(int[] arr) {
        System.out.print("Danh sách mã sách: ");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]);
            if (i < arr.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Nhập số lượng sách: ");
        int n = sc.nextInt();

        int[] library = addBookToLibraries(n);

        displayLibraries(library);
    }
}