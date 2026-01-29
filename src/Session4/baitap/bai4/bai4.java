package Session4.baitap.bai4;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class bai4 {
    public static void main(String[] args) {

        String cardID = "TV202312345";
        String regex = "^[A-Z]{2}\\d{4}\\d{5}$";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(cardID);

        if (matcher.matches()) {
            System.out.println("Ma the hop le !");
        } else {
            if (!cardID.matches("^[A-Z]{2}.*")) {
                System.out.println("Loi: Thieu tien to 2 chu cai viet hoa (VD: TV)");
            }else if (!cardID.matches("^[A-Z]{2}\\d{4}.*")) {
                System.out.println("Loi: Nam vao hoc khong hop le (can 4 chu so)");
            }else if (!cardID.matches("^[A-Z]{2}\\d{4}\\d{5}$")) {
                System.out.println("Loi: Thieu 5 chu so o cuoi ma the");
            }else {
                System.out.println("Loi: Dinh dang ma the khong hop le !");
            }
        }
    }
}