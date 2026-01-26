package Session2.baitap;

import java.util.Scanner;

public class bai03 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        final int tienLai = 5000;
        long total = 0;

        System.out.print("Nhap so sach tra muon : ");
        int n = sc.nextInt();

        for (int i=1; i<=n; i++){
            System.out.print("Nhap so ngay tre cua cuon sach thu " + i + " : ");
            int soNgay = sc.nextInt();
            total += soNgay * tienLai;
        }

        System.out.println("===> Tong tien phat : " + total + " VND");

        sc.close();
    }
}
