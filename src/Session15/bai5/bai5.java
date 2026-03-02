package Session15.bai5;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

class TreatmentStep {
    private String description;
    private String time;

    public TreatmentStep(String description, String time) {
        this.description = description;
        this.time = time;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", time, description);
    }
}

class EmergencyCase {
    private String patientName;
    private Stack<TreatmentStep> steps;

    public EmergencyCase(String patientName) {
        this.patientName = patientName;
        this.steps = new Stack<>();
    }

    public String getPatientName() { return patientName; }

    public void addStep(TreatmentStep step) {
        steps.push(step);
        System.out.println("   + Thêm bước: " + step);
    }

    public TreatmentStep undoStep() {
        if (steps.isEmpty()) {
            System.out.println("   (!) Không còn bước nào để hoàn tác.");
            return null;
        }
        TreatmentStep removed = steps.pop();
        System.out.println("   <- Đã hoàn tác bước: " + removed);
        return removed;
    }

    public void displaySteps() {
        System.out.println("   Lịch sử điều trị của " + patientName + ":");
        if (steps.isEmpty()) System.out.println("   (Trống)");
        else {
            for (int i = steps.size() - 1; i >= 0; i--) {
                System.out.println("     " + steps.get(i));
            }
        }
    }
}

class EmergencyCaseQueue {
    private Queue<EmergencyCase> cases = new LinkedList<>();

    public void addCase(EmergencyCase c) {
        cases.add(c);
        System.out.println("-> [Hệ thống] Tiếp nhận ca cấp cứu mới: " + c.getPatientName());
    }

    public EmergencyCase getNextCase() {
        if (cases.isEmpty()) {
            System.out.println("(!) Không còn ca cấp cứu nào trong hàng đợi.");
            return null;
        }
        EmergencyCase next = cases.poll();
        System.out.println("\n==> ĐANG XỬ LÝ CA: " + next.getPatientName().toUpperCase());
        return next;
    }
}

public class bai5 {
    public static void main(String[] args) {
        EmergencyCaseQueue emergencyDept = new EmergencyCaseQueue();

        emergencyDept.addCase(new EmergencyCase("Bệnh nhân A"));
        emergencyDept.addCase(new EmergencyCase("Bệnh nhân B"));

        EmergencyCase currentCase = emergencyDept.getNextCase();
        if (currentCase != null) {
            currentCase.addStep(new TreatmentStep("Tiếp nhận tại sảnh cấp cứu", "09:00"));
            currentCase.addStep(new TreatmentStep("Đo nhịp tim và huyết áp", "09:05"));
            currentCase.addStep(new TreatmentStep("Tiêm thuốc giảm đau (Nhầm liều)", "09:10"));
            currentCase.undoStep();
            currentCase.addStep(new TreatmentStep("Tiêm thuốc giảm đau (Đúng liều)", "09:12"));
            currentCase.displaySteps();
        }

        EmergencyCase nextCase = emergencyDept.getNextCase();
        if (nextCase != null) {
            nextCase.addStep(new TreatmentStep("Khám sơ bộ", "09:30"));
            nextCase.displaySteps();
        }
    }
}