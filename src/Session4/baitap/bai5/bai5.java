package Session4.baitap.bai5;

import java.util.regex.*;
import java.util.*;

public class bai5 {
    public static void main(String[] args) {
        String[] logs = {"Ngay: 2024-05-20 | Nguoi dung: NguyenVanA | Hanh dong: BORROW | Ma sach: BK12345",};

        String regex = "Ngay:\\s*(\\d{4}-\\d{2}-\\d{2})\\s*\\|\\s*" +
                "Nguoi dung:\\s*(\\w+)\\s*\\|\\s*" +
                "Hanh dong:\\s*(BORROW|RETURN)\\s*\\|\\s*" +
                "Ma sach:\\s*(\\w+)";

        Pattern pattern = Pattern.compile(regex);

        int borrowCount = 0;
        int returnCount = 0;

        for (String log : logs) {
            Matcher matcher = pattern.matcher(log);

            if (matcher.find()) {
                String date = matcher.group(1);
                String user = matcher.group(2);
                String action = matcher.group(3);
                String bookId = matcher.group(4);

                System.out.println("Ngay: " + date);
                System.out.println("Nguoi dung: " + user);
                System.out.println("Hanh dong: " + action);
                System.out.println("Ma sach: " + bookId);
                System.out.println("----------------------");

                if (action.equals("BORROW")) {
                    borrowCount++;
                } else if (action.equals("RETURN")) {
                    returnCount++;
                }
            }
        }

        System.out.println("Tong BORROW: " + borrowCount);
        System.out.println("Tong RETURN: " + returnCount);
    }
}