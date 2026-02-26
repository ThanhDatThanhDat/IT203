package Session11.BTTH;

import java.util.Scanner;

public class Cau1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // PHẦN 1: TRÍCH XUẤT & CHUẨN HÓA TỪ

        System.out.print("Nhap chuoi bat ky : ");
        String input = sc.nextLine();
            // [^a-zA-Z]+ nghĩa là: 1 hoặc nhiều ký tự không phải chữ
        String[] words = input.split("[^a-zA-Z]+");
        String result = "";
        for (String word : words){
            if (!word.isEmpty()){
                // chữ đầu viết hoa chữ sau vt thg
                String formatted = word.substring(0,1).toUpperCase()
                        + word.substring(1).toLowerCase();
                // nối lại vs nhau
                result += formatted + " " ;
            }
        }
            // dùng trim() loại bỏ ktrang cuối
        System.out.println("Chuoi chuan hoa : " + result.trim());

        // PHẦN 2: THỐNG KÊ KÝ TỰ DUY NHẤT

        System.out.print("Nhap chuoi de thong ke : ");
        String str = sc.nextLine();
            // duyệt từng ký tự trong chuỗi
        for (int i=0; i<str.length(); i++){
            char ch = str.charAt(i);
            int count = 0;
            // dem so lan xuat hien
            for (int j=0; j<str.length(); j++){
                if(str.charAt(j) == ch){
                    count++;
                }
            }
            // neu xuat hien 1 lan thi in ra
            if(count == 1){
                System.out.print(ch + " ");
            }
        }
    }
}
