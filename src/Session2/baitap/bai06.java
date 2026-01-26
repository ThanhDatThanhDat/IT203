package Session2.baitap;

import java.util.Scanner;

public class bai06 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int max = -1;
        int min = Integer.MAX_VALUE;
        int tong = 0;
        int soNgayMoCua = 0;

        for (int i = 1; i <= 7; i++) {
            System.out.print("Nhap luot muon ngay thu " + i + " : ");
            int luotMuon = sc.nextInt();

            if (luotMuon == 0) {
                continue;
            }
            if (luotMuon > max) {
                max = luotMuon;
            }
            if (luotMuon < min) {
                min = luotMuon;
            }

            tong += luotMuon;
            soNgayMoCua++;
        }

        if (soNgayMoCua > 0) {
            double trungBinh = (double) tong / soNgayMoCua;

            System.out.println("Luot muon cao nhat " + max);
            System.out.println("Luot muon thap nhat : " + min);
            System.out.println("Trung binh luot muon : " + trungBinh);
        } else {
            System.out.println("Khong co ngay nao thu vien mo cua.");
        }

        sc.close();
    }
}
