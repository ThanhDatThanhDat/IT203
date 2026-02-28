package Session14.bai5;

import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

class Patient {
    String name;
    int severity;
    int arrivalTime;

    public Patient(String name, int severity, int arrivalTime) {
        this.name = name;
        this.severity = severity;
        this.arrivalTime = arrivalTime;
    }

    @Override
    public String toString() {
        return String.format("Bệnh nhân %s (Mức %d, đến lúc %d)", name, severity, arrivalTime);
    }
}

public class bai5 {
    public static void main(String[] args) {
        Set<Patient> triageQueue = new TreeSet<>(new Comparator<Patient>() {
            @Override
            public int compare(Patient p1, Patient p2) {
                if (p1.severity != p2.severity) {
                    return Integer.compare(p1.severity, p2.severity);
                }
                return Integer.compare(p1.arrivalTime, p2.arrivalTime);
            }
        });

        triageQueue.add(new Patient("A", 3, 480));
        triageQueue.add(new Patient("B", 1, 495));
        triageQueue.add(new Patient("C", 1, 485));
        triageQueue.add(new Patient("D", 2, 490));

        System.out.println("--- THỨ TỰ XỬ LÝ CẤP CỨU ---");
        for (Patient p : triageQueue) {
            System.out.println(p);
        }
    }
}