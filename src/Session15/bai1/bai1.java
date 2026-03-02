package Session15.bai1;

import java.util.Stack;

class EditAction {
    private String description;
    private String time;

    public EditAction(String description, String time) {
        this.description = description;
        this.time = time;
    }

    public String getDescription() { return description; }
    public String getTime() { return time; }

    @Override
    public String toString() {
        return String.format("[%s] %s", time, description);
    }
}

class MedicalRecordHistory {
    private Stack<EditAction> history = new Stack<>();

    public void addEdit(EditAction action) {
        history.push(action);
        System.out.println("-> Thêm chỉnh sửa: " + action.getDescription());
    }

    public EditAction undoEdit() {
        if (isEmpty()) {
            System.out.println("(!) Không còn lịch sử để hoàn tác.");
            return null;
        }
        EditAction removed = history.pop();
        System.out.println("<- Đã hoàn tác: " + removed.getDescription());
        return removed;
    }

    public EditAction getLatestEdit() {
        return isEmpty() ? null : history.peek();
    }

    public boolean isEmpty() {
        return history.isEmpty();
    }

    public void displayHistory() {
        System.out.println("\n--- TRẠNG THÁI STACK (Lịch sử hiện tại) ---");
        if (isEmpty()) System.out.println("Trống.");
        else {
            for (int i = history.size() - 1; i >= 0; i--) {
                System.out.println(history.get(i));
            }
        }
        System.out.println("\n");
    }
}

public class bai1 {
    public static void main(String[] args) {
        MedicalRecordHistory recordHistory = new MedicalRecordHistory();

        recordHistory.addEdit(new EditAction("Thay đổi nhóm máu: A+", "08:00"));
        recordHistory.addEdit(new EditAction("Cập nhật chiều cao: 175cm", "08:15"));
        recordHistory.addEdit(new EditAction("Ghi chú: Dị ứng Penicillin", "08:30"));

        recordHistory.displayHistory();

        System.out.println("Xem nhanh thao tác cuối: " + recordHistory.getLatestEdit());

        recordHistory.undoEdit();
        recordHistory.displayHistory();
    }
}