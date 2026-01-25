import java.util.Scanner;

public class bai02 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Nhập số ngày chậm trễ: ");
        int n = sc.nextInt();

        System.out.print("Nhập số lượng sách mượn: ");
        int m = sc.nextInt();

        double total = n * m * 5000;
        System.out.println("Tiền phạt gốc: " + total + " VND");

        if (n > 7 && m >= 3) {
            total = total * 1.2;
        }

        System.out.println("Tiền phạt sau điều chỉnh: " + total + " VND");

        boolean lockCard = total > 50000;
        System.out.println("Yêu cầu khóa thẻ: " + lockCard);
    }
}
