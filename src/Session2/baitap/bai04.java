package Session2.baitap;

import java.util.Scanner;

public class bai04 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int check;

        do {
            System.out.print("Nhap ma ID sach (ID > 0) : ");
            check = sc.nextInt();

            if(check <= 0){
                System.out.println("Loi : ID phai la so duong . Hay nhap lai..");
            }
        }while (check <= 0);
        System.out.println("Xac nhan : Ma sach " + check + " duoc ghi nhan.");
        sc.close();
    }
}
