import java.util.Scanner;

public class bai04 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Nhập giá sách (USD): ");
        double giaSachUSD = sc.nextDouble();
        System.out.print("Nhập giá chính sác (số thực): ");
        float tyGia = sc.nextFloat();

        double tongTienVND = giaSachUSD * tyGia;

        long tienLamTron = (long) tongTienVND;

        System.out.println("Giá slàm tron để thanh toán : " + tienLamTron + " VNĐ");

        sc.close();
    }
}