package Session4.baitap.bai3;

import java.time.LocalDate;

public class bai3 {
    public static void main(String[] args) {
        String[] transactions = {"BK001-20/01", "BK005-21/01", "BK099-22/01"};

        /* STRINGBUILDER */
        long startBuilder = System.nanoTime();

        StringBuilder report = new StringBuilder();
        report.append("--- BAO CAO MUON SACH ---\n");
        report.append("Ngay tao: ")
                .append(LocalDate.now().format(java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy")))
                .append("\n");

        for (String t : transactions) {
            report.append("Giao dich: ").append(t).append("\n");
        }

        long endBuilder = System.nanoTime();
        long timeBuilder = endBuilder - startBuilder;

        /* STRING */
        long startString = System.nanoTime();

        String reportString = "";
        for (String t : transactions) {
            reportString += "Giao dich: " + t + "\n";
        }

        long endString = System.nanoTime();
        long timeString = endString - startString;

        System.out.println(report.toString());
        System.out.println("So thoi gian thuc thi doi voi StringBuilder: " + timeBuilder);
        System.out.println("So thoi gian thuc thi doi voi String: " + timeString);
    }
}