package Session3.baitap;

import java.util.Scanner;

public class bai02 {
    public static int searchBooks(String[] arr, String search){
        for (int i=0;i<arr.length;i++){
            if (arr[i].equalsIgnoreCase(search)) {
                return i;
            }
        }
        return -1;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String[] books = {
            "Java Basic",
            "Python Intro",
            "C++ Programming",
            "Data Structures",
            "Web Development"
        };

        System.out.print("Nhập tên sách cần tìm : ");
        String searchBook = sc.nextLine();

        int result = searchBooks(books, searchBook);


        if (result != -1) {
            System.out.println("Tìm thấy tại vị trí " + result);
        } else {
            System.out.println("Sách không tồn tại");
        }
    }
}
