package Session3.baitap;

import java.util.Scanner;

public class bai06{
    public static int[] mergeBooks(int[] a, int[] b) {

        int[] temp = new int[a.length + b.length];
        int i = 0, j = 0, k = 0;

        while (i < a.length && j < b.length) {
            if (a[i] < b[j]) {
                if (k == 0 || temp[k - 1] != a[i]) temp[k++] = a[i];
                i++;
            } else if (a[i] > b[j]) {
                if (k == 0 || temp[k - 1] != b[j]) temp[k++] = b[j];
                j++;
            } else {
                if (k == 0 || temp[k - 1] != a[i]) temp[k++] = a[i];
                i++;
                j++;
            }
        }

        while (i < a.length) {
            if (k == 0 || temp[k - 1] != a[i]) temp[k++] = a[i];
            i++;
        }
        while (j < b.length) {
            if (k == 0 || temp[k - 1] != b[j]) temp[k++] = b[j];
            j++;
        }

        int[] arrayMerge = new int[k];
        for (int x = 0; x < k; x++) arrayMerge[x] = temp[x];

        return arrayMerge;
    }

    public static void display(String name, int[] arr) {
        System.out.print(name + ": ");
        for (int x : arr) System.out.print(x + " ");
        System.out.println();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[] arrayFirst  = {1, 3, 5, 7, 9};
        int[] arraySecond = {2, 3, 4, 5, 8, 9, 10};

        display("Mảng A", arrayFirst);
        display("Mảng B", arraySecond);

        int[] result = mergeBooks(arrayFirst, arraySecond);

        System.out.println("Danh sách sách sau khi gộp:");
        display("Mảng gộp", result);
    }
}