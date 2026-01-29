package Session4.baitap.bai1;

import java.util.Scanner;

public class bai1 {
    public static String capitalizeWords(String str) {
        str = str.trim().replaceAll("\\s+", " ");
        String[] words = str.split(" ");
        StringBuilder result = new StringBuilder();
        for (String word : words) {
            if (word.length() > 0) {
                result.append(Character.toUpperCase(word.charAt(0)))
                        .append(word.substring(1).toLowerCase())
                        .append(" ");
            }
        }
        return result.toString().trim();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String bookName = "lap TRINH java   Co ban";
        String authorName = "thanh dat";

        bookName = bookName.trim().replaceAll("\\s+", " ").toUpperCase();
        authorName = capitalizeWords(authorName);

        String output = "[" +bookName+ "]" + " - Tác giả: " + authorName;
        System.out.println(output);
    }
}