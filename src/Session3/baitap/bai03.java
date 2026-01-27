package Session3.baitap;

import java.util.Scanner;

public class bai03 {
    public static void maxMinBooks(String[] names, int[] quantities) {
        int max = quantities[0];
        int min = quantities[0];

        for (int q : quantities) {
            if (q > max) max = q;
            if (q < min) min = q;
        }

        System.out.println("Sách có số lượng lớn nhất:");
        for (int i = 0; i < quantities.length; i++) {
            if (quantities[i] == max) {
                System.out.println("- " + names[i] + ": " + quantities[i]);
            }
        }
        System.out.println("Sách có số lượng nhỏ nhất:" );
        for (int i = 0; i < quantities.length; i++) {
            if (quantities[i] == min) {
                System.out.println("- " + names[i] + ": " + quantities[i]);
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String[] names = {
            "Java Basic",
            "Python Intro",
            "C++ Programming",
            "Data Structures",
            "Web Development"
        };

        int[] quantities = {10, 5, 10, 3, 3};

        maxMinBooks(names, quantities);
    }
}