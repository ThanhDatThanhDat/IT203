package Session13.bai1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public class bai1 {
    public static void main(String[] args) {
        ArrayList<Double> temps = new ArrayList<>(Arrays.asList(36.5, 40.2, 37.0, 12.5, 39.8, 99.9, 36.8));
        System.out.println("Danh sách ban đầu: " + temps);

        Iterator<Double> iterator = temps.iterator();
        while (iterator.hasNext()) {
            Double temp = iterator.next();
            if (temp < 34.0 || temp > 42.0) {
                iterator.remove();
            }
        }

        System.out.println("Danh sách sau khi lọc: " + temps);

        double sum = 0;
        for (Double temp : temps) {
            sum += temp;
        }

        double average = (temps.isEmpty()) ? 0 : sum / temps.size();

        System.out.printf("Nhiệt độ trung bình: %.2f\n", average);
    }
}
