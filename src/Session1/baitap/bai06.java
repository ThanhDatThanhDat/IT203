import java.util.Scanner;

public class bai06 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Nhập số thứ tự của sách trong hệ thống: ");
        int stt = sc.nextInt();
        int ke = (stt - 1) / 25 + 1;
        int viTri = (stt - 1) % 25 + 1;

        String khuVuc = (ke <= 10) ? "Khu Cận (Gần cửa)" : "Khu Viễn (Xa cửa)";

        System.out.println("\n--- THÔNG TIN ĐỊNH VỊ ---");
        System.out.println("Sách số: " + stt);
        System.out.println("Địa chỉ: Kệ " + ke + " - Vị trí " + viTri);
        System.out.println("Phân khu: " + khuVuc);

        sc.close();
    }
}