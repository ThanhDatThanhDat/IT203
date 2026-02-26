package Session13.bai4;

import java.util.LinkedList;

class EmergencyRoom {
    private LinkedList<String> patients = new LinkedList<>();

    public void patientCheckIn(String name) {
        patients.addLast(name);
        System.out.println("Tiếp nhận bệnh nhân thường: " + name);
    }

    public void emergencyCheckIn(String name) {
        patients.addFirst(name);
        System.out.println("!!! CẤP CỨU KHẨN CẤP: " + name + " !!!");
    }

    public void treatPatient() {
        if (patients.isEmpty()) {
            System.out.println("Hàng đợi trống. Không có bệnh nhân nào.");
            return;
        }

        String patientName = patients.removeFirst();

        if (patientName.equals("C")) {
            System.out.println("Đang cấp cứu: " + patientName);
        } else {
            System.out.println("Đang khám: " + patientName);
        }
    }
}

public class bai4 {
    public static void main(String[] args) {
        EmergencyRoom er = new EmergencyRoom();

        er.patientCheckIn("A");
        er.patientCheckIn("B");
        er.emergencyCheckIn("C");

        System.out.println("\n--- Thứ tự bác sĩ xử lý ---");

        er.treatPatient();
        er.treatPatient();
        er.treatPatient();
    }
}