package Session15.bai3;

import java.util.Stack;

class MedicationProcessChecker {
    private Stack<String> stack = new Stack<>();
    public boolean checkProcess(String[] actions) {
        reset();

        for (int i = 0; i < actions.length; i++) {
            String action = actions[i].toUpperCase();

            if (action.equals("PUSH")) {
                stack.push("Thuốc_" + (i + 1));
            } else if (action.equals("POP")) {
                if (stack.isEmpty()) {
                    System.out.println("(!) LỖI tại bước " + (i + 1) + ": Cố gắng hoàn tất (POP) khi chưa có yêu cầu phát thuốc nào.");
                    return false;
                }
                stack.pop();
            }
        }

        if (!stack.isEmpty()) {
            System.out.println("(!) LỖI: Kết thúc ca trực nhưng vẫn còn " + stack.size() + " loại thuốc chưa được xác nhận hoàn tất.");
            return false;
        }

        return true;
    }

    public void reset() {
        stack.clear();
    }
}

public class bai3 {
    public static void main(String[] args) {
        MedicationProcessChecker checker = new MedicationProcessChecker();

        String[] case1 = {"PUSH", "PUSH", "POP", "POP"};
        System.out.println("Kiểm tra Ca 1 (PUSH, PUSH, POP, POP):");
        checkAndPrint(checker, case1);

        String[] case2 = {"PUSH", "POP", "POP", "PUSH"};
        System.out.println("\nKiểm tra Ca 2 (PUSH, POP, POP, PUSH):");
        checkAndPrint(checker, case2);

        String[] case3 = {"PUSH", "PUSH", "POP"};
        System.out.println("\nKiểm tra Ca 3 (PUSH, PUSH, POP):");
        checkAndPrint(checker, case3);
    }

    private static void checkAndPrint(MedicationProcessChecker checker, String[] actions) {
        if (checker.checkProcess(actions)) {
            System.out.println("=> KẾT QUẢ: Quy trình hoàn toàn HỢP LỆ.");
        } else {
            System.out.println("=> KẾT QUẢ: Quy trình KHÔNG HỢP LỆ.");
        }
    }
}