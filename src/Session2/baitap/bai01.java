package Session2.baitap;

import java.util.Scanner;

public class bai01 {
    public static void main (String[] args){
        Scanner sc = new Scanner(System.in);

        System.out.print("Nhp tuoi cua ban : ");
        int tuoi = sc.nextInt();
        System.out.print("Nhap so sach dang muon : ");
        int soSach = sc.nextInt();

        if (tuoi >= 18 && soSach < 3){
            System.out.println("Ket qua : Ban du dieu kien muon sach quy hien");
        }else{
            if (tuoi < 18){
                System.out.println("Ket qua : Khong di dieu kien" + "\n- Ly do : Ban chua du tuoi de muon sach quy hiem");
            }else{
                System.out.println("Ket qua : Khong di dieu kien" + "\n- Ly do : Ban da muon qua so lan cho phep");
            }
        }
        sc.close();
    }
}