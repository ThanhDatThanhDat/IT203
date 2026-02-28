package Session14.bai6;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Patient {
    String name;
    int age;
    String department;

    public Patient(String name, int age, String department) {
        this.name = name;
        this.age = age;
        this.department = department;
    }

    @Override
    public String toString() {
        return name + " (" + age + " tuổi)";
    }
}

public class bai6 {
    public static void main(String[] args) {
        List<Patient> inputList = Arrays.asList(
                new Patient("Lan", 45, "Tim mạch"),
                new Patient("Hùng", 30, "Nội tiết"),
                new Patient("Mai", 50, "Tim mạch"),
                new Patient("Tuấn", 25, "Ngoại khoa"),
                new Patient("An", 60, "Nội tiết"),
                new Patient("Bình", 35, "Nội tiết")
        );

        Map<String, List<Patient>> hospitalData = new HashMap<>();

        for (Patient p : inputList) {
            String khoa = p.department;
            if (!hospitalData.containsKey(khoa)) {
                hospitalData.put(khoa, new ArrayList<>());
            }
            hospitalData.get(khoa).add(p);
        }

        System.out.println("--- DANH SÁCH BỆNH NHÂN THEO KHOA ---");
        for (var entry : hospitalData.entrySet()) {
            System.out.println("Khoa " + entry.getKey() + ": " + entry.getValue());
        }
        String khoaQuaTai = "";
        int maxPatients = 0;

        for (var entry : hospitalData.entrySet()) {
            int currentSize = entry.getValue().size();
            if (currentSize > maxPatients) {
                maxPatients = currentSize;
                khoaQuaTai = entry.getKey();
            }
        }

        System.out.println("\n");
        System.out.println("PHÂN TÍCH: Khoa " + khoaQuaTai + " đang đông nhất (" + maxPatients + " bệnh nhân).");
    }
}