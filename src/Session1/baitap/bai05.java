import java.util.Scanner;

public class bai05 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Nhập mã sách 4 chữ số: ");
        int code = sc.nextInt();

        int thousands = code / 1000;
        int hundreds  = (code / 100) % 10;
        int dozens    = (code / 10) % 10;
        int units     = code % 10;

        int sumOfFirstThreeNumber = thousands + hundreds + dozens;

        boolean isValid = (sumOfFirstThreeNumber % 10 == units);

        System.out.println(isValid);
    }
}
