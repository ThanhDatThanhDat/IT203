package Session7.bai3;

class ScoreUtils {
    public static boolean checkPass(double score) {
        return score >= 5.0;
    }

    public static double calculateAverage(double[] scores) {
        if (scores == null || scores.length == 0) return 0;
        double sum = 0;
        for (double s : scores) {
            sum += s;
        }
        return sum / scores.length;
    }
}

public class bai3 {
    public static void main(String[] args) {
        double[] listScores = {6.5, 8.0, 4.5};

        System.out.print("Danh sach diem: ");
        for (int i = 0; i < listScores.length; i++) {
            System.out.print(listScores[i] + (i == listScores.length - 1 ? "" : ", "));
        }

        System.out.println("\n\n>> Ket qua xu ly:");

        double avg = ScoreUtils.calculateAverage(listScores);
        System.out.printf("- Diem trung binh ca lop: %.2f\n", avg);

        for (double s : listScores) {
            boolean isPassed = ScoreUtils.checkPass(s);
            String status = isPassed ? "Dat" : "Truot";
            System.out.println("- Diem " + s + ": " + status);
        }
    }
}