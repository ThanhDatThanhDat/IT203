package Session15.bai2;

import java.util.LinkedList;
import java.util.Queue;

class Patient {
    private String id;
    private String name;
    private int age;

    public Patient(String id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public String getName() { return name; }
    public String getId() { return id; }

    @Override
    public String toString() {
        return String.format("ID: %s | Tên: %-15s | Tuổi: %d", id, name, age);
    }
}

class PatientQueue {
    private Queue<Patient> queue = new LinkedList<>();

    public void addPatient(Patient p) {
        queue.add(p);
        System.out.println("-> Bệnh nhân " + p.getName() + " đã vào hàng đợi.");
    }

    public Patient callNextPatient() {
        if (isEmpty()) {
            System.out.println("(!) Hiện không có bệnh nhân nào đang chờ.");
            return null;
        }
        Patient next = queue.poll();
        System.out.println("=> Mời bệnh nhân: " + next.getName() + " vào phòng khám.");
        return next;
    }

    public Patient peekNextPatient() {
        return queue.peek();
    }

    public boolean isEmpty() {
        return queue.isEmpty();
    }

    public void displayQueue() {
        System.out.println("\n--- DANH SÁCH BỆNH NHÂN ĐANG CHỜ ---");
        if (isEmpty()) {
            System.out.println("Trống.");
        } else {
            for (Patient p : queue) {
                System.out.println(p);
            }
        }
        System.out.println("\n");
    }
}

public class bai2 {
    public static void main(String[] args) {
        PatientQueue clinic = new PatientQueue();

        clinic.addPatient(new Patient("BN001", "Nguyễn Văn A", 25));
        clinic.addPatient(new Patient("BN002", "Trần Thị B", 60));
        clinic.addPatient(new Patient("BN003", "Lê Văn C", 10));

        clinic.displayQueue();

        System.out.println("Người chuẩn bị khám tiếp theo: " + clinic.peekNextPatient().getName());

        clinic.callNextPatient();
        clinic.callNextPatient();

        clinic.displayQueue();
    }
}