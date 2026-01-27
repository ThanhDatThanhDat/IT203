package Session3.baitap;

import java.util.Scanner;

public class bai04 {

    public static void sortBooks(int[] arr) {
        int n = arr.length;

        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

    // hiển thị mảng
    public static void displayBooks(int[] arr) {
        for (int x : arr) {
            System.out.print(x + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int[] isbn = {105, 203, 150, 98, 120, 175};

        System.out.println("Mảng trước khi sắp xếp:");
        displayBooks(isbn);
        sortBooks(isbn);
        System.out.println("Mảng sau khi sắp xếp:");
        displayBooks(isbn);
    }
}