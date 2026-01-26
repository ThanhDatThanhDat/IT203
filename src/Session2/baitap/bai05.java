package Session2.baitap;

import java.util.Scanner;

public class bai05 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int uyTin = 100;
        int soNgayTre;

        System.out.println("Nhap so ngay tre. Ngap 999 de ket thuc" + "\nSo ngay tre cua lan nay : ");
        soNgayTre = sc.nextInt();

        if (soNgayTre == 999) {
            return;
        }

        if (soNgayTre <= 0) {
            uyTin += 5;
        } else {
            uyTin -= soNgayTre * 2;
        }

        System.out.println("Tong diem uy tin : " + uyTin);

        if (uyTin > 120) {
            System.out.println("Xep loai : Doc gia than thiet");
        } else if (uyTin >= 80) {
            System.out.println("Xep loai : Doc gia tieu chuan");
        } else {
            System.out.println("Xep loai : Doc gia can luu y");
        }

        sc.close();
    }
}
