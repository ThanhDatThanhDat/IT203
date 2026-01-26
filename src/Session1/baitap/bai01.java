import java.util.Scanner;

public class bai01 {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Nhập mã sách: ");
        String bookID = sc.nextLine();
        System.out.print("Nhập tên sách: ");
        String bookName = sc.nextLine();
        System.out.print("Nhập năm xuất bản: ");
        int publishYear = sc.nextInt();
        System.out.print("Nhập giá bìa: ");
        double price = sc.nextDouble();
        System.out.print("Sách còn trong kho (true/false): ");
        boolean isAvailable = sc.nextBoolean();

        int bookAge = 2026 - publishYear;
        String bookNameUpper = bookName.toUpperCase();
        String status = isAvailable ? "Còn sách" : "Đã mượn";

        System.out.println("\n===== THÔNG TIN SÁCH =====");
        System.out.println("Tên sách: " + bookNameUpper);
        System.out.println("Mã sách: " + bookID + "|" + "Tuổi thọ sách: " + bookAge + " năm");
        System.out.printf("Giá tiền: %.2f\n", price);
        System.out.println("Tình trạng: " + status);
    }
}