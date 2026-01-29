package Session4.baitap.bai2;

import java.util.Scanner;

public class bai2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String description = "Sach giao khoa Toan lop 12, Ke: A1-102, tinh trang moi";

        if (description.contains("Ke:")) {
            int startIndex = description.indexOf("Ke:") + 3;

            int endIndex = description.indexOf(",", startIndex);
            if (endIndex == -1) {
                endIndex = description.length();
            }

            String shelfCode = description.substring(startIndex, endIndex).trim();
            String newDescription = description.replace("Ke:", "Vi tri luu tru:");

            System.out.println("Vi tri tim thay: " + shelfCode);
            System.out.println("Mo ta moi: " + newDescription);
        } else {
            System.out.println("Khong tim thay tu khoa 'Ke:' trong mo ta.");
        }
    }
}