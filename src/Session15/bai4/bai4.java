package Session15.bai4;

import java.util.PriorityQueue;

class EmergencyPatient implements Comparable<EmergencyPatient> {
    private String id;
    private String name;
    private int priority;
    private long arrivalTime;

    public EmergencyPatient(String id, String name, int priority) {
        this.id = id;
        this.name = name;
        this.priority = priority;
        this.arrivalTime = System.nanoTime();
    }

    public int getPriority() { return priority; }
    public String getName() { return name; }

    @Override
    public int compareTo(EmergencyPatient other) {
        if (this.priority != other.priority) {
            return Integer.compare(this.priority, other.priority);
        }
        return Long.compare(this.arrivalTime, other.arrivalTime);
    }

    @Override
    public String toString() {
        String type = (priority == 1) ? "CẤP CỨU" : "Thường";
        return String.format("[%s] ID: %s | Tên: %-15s", type, id, name);
    }
}

class EmergencyQueue {
    private PriorityQueue<EmergencyPatient> pQueue = new PriorityQueue<>();

    public void addPatient(EmergencyPatient p) {
        pQueue.add(p);
        System.out.println("-> Tiếp nhận: " + p.getName() + " (" + (p.getPriority() == 1 ? "Cấp cứu" : "Thường") + ")");
    }

    public EmergencyPatient callNextPatient() {
        if (pQueue.isEmpty()) {
            System.out.println("(!) Không có bệnh nhân chờ.");
            return null;
        }
        EmergencyPatient next = pQueue.poll();
        System.out.println("\n=> BÁC SĨ GỌI: " + next.getName() + " vào phòng khám!");
        return next;
    }

    public void displayQueue() {
        System.out.println("\n--- DANH SÁCH ĐỢI THEO ƯU TIÊN ---");
        if (pQueue.isEmpty()) System.out.println("Trống.");
        else {
            PriorityQueue<EmergencyPatient> temp = new PriorityQueue<>(pQueue);
            while (!temp.isEmpty()) {
                System.out.println(temp.poll());
            }
        }
        System.out.println("\n");
    }
}

public class bai4 {
    public static void main(String[] args) {
        EmergencyQueue emergencyRoom = new EmergencyQueue();

        emergencyRoom.addPatient(new EmergencyPatient("BN01", "Nguyễn Thường", 2));
        emergencyRoom.addPatient(new EmergencyPatient("BN02", "Trần Cấp Cứu", 1));
        emergencyRoom.addPatient(new EmergencyPatient("BN03", "Lê Bình Dân", 2));
        emergencyRoom.addPatient(new EmergencyPatient("BN04", "Phạm Nguy Kịch", 1));

        emergencyRoom.displayQueue();

        emergencyRoom.callNextPatient();
        emergencyRoom.callNextPatient();

        emergencyRoom.displayQueue();
    }
}