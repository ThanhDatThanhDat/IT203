package Session13.bai6;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Medicine {
    private String drugId;
    private String drugName;
    private double unitPrice;
    private int quantity;

    public Medicine(String drugId, String drugName, double unitPrice, int quantity) {
        this.drugId = drugId;
        this.drugName = drugName;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
    }

    public String getDrugId() { return drugId; }
    public String getDrugName() { return drugName; }
    public double getUnitPrice() { return unitPrice; }
    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }

    public double getTotalPrice() {
        return unitPrice * quantity;
    }

    @Override
    public String toString() {
        return String.format("%-10s %-20s %-12.2f %-10d", drugId, drugName, unitPrice, quantity);
    }
}

public class bai6 {
    private static List<Medicine> medicineList = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n||====================MENU====================||");
            System.out.println("||            1. Thêm thuốc vào đơn           ||");
            System.out.println("||            2. Điều chỉnh số lượng          ||");
            System.out.println("||            3. Xóa thuốc                    ||");
            System.out.println("||            4. In hóa đơn                   ||");
            System.out.println("||            5. Tìm thuốc giá rẻ             ||");
            System.out.println("||            6. Thoát                        ||");
            System.out.println("||============================================||");
            System.out.print("Chọn lựa chọn: ");

            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Vui lòng nhập số từ 1 đến 6!");
                continue;
            }

            switch (choice) {
                case 1: addMedicine(); break;
                case 2: updateQuantity(); break;
                case 3: removeMedicine(); break;
                case 4: printInvoice(); break;
                case 5: findCheapMedicine(); break;
                case 6:
                    System.out.println("Kết thúc chương trình. Tạm biệt!");
                    return;
                default:
                    System.out.println("Lựa chọn không hợp lệ!");
            }
        }
    }

    private static void addMedicine() {
        System.out.print("Nhập mã thuốc: ");
        String id = scanner.nextLine();
        for (Medicine m : medicineList) {
            if (m.getDrugId().equals(id)) {
                System.out.print("Mã thuốc đã tồn tại. Nhập số lượng muốn thêm: ");
                int extraQty = Integer.parseInt(scanner.nextLine());
                m.setQuantity(m.getQuantity() + extraQty);
                System.out.println("Cập nhật số lượng thành công!");
                return;
            }
        }

        System.out.print("Nhập tên thuốc: ");
        String name = scanner.nextLine();
        System.out.print("Nhập giá thuốc: ");
        double price = Double.parseDouble(scanner.nextLine());
        System.out.print("Nhập số lượng: ");
        int qty = Integer.parseInt(scanner.nextLine());

        medicineList.add(new Medicine(id, name, price, qty));
        System.out.println("Thêm thuốc thành công!");
    }

    private static void updateQuantity() {
        while (true) {
            System.out.print("Nhập mã thuốc: ");
            String id = scanner.nextLine();
            Medicine found = null;

            for (Medicine m : medicineList) {
                if (m.getDrugId().equals(id)) {
                    found = m;
                    break;
                }
            }

            if (found == null) {
                System.out.println("Thuốc không tồn tại trong đơn. Yêu cầu nhập lại.");
            } else {
                System.out.print("Nhập số lượng mới: ");
                int newQty = Integer.parseInt(scanner.nextLine());
                if (newQty <= 0) {
                    medicineList.remove(found);
                    System.out.println("Đã xóa thuốc khỏi đơn do số lượng <= 0.");
                } else {
                    found.setQuantity(newQty);
                    System.out.println("Cập nhật thuốc thành công!");
                }
                break;
            }
        }
    }

    private static void removeMedicine() {
        System.out.print("Nhập mã thuốc cần xóa: ");
        String id = scanner.nextLine();

        boolean removed = medicineList.removeIf(m -> m.getDrugId().equals(id));

        if (removed) {
            System.out.println("Xóa thuốc thành công!");
        } else {
            System.out.println("Id thuốc không tồn tại!");
        }
    }

    private static void printInvoice() {
        if (medicineList.isEmpty()) {
            System.out.println("Đơn thuốc hiện đang trống.");
            return;
        }

        System.out.println("\n--- HÓA ĐƠN ĐIỆN TỬ ---");
        System.out.printf("%-10s %-20s %-12s %-10s\n", "Mã Thuốc", "Tên Thuốc", "Đơn Giá", "Số Lượng");
        double totalSum = 0;

        for (Medicine m : medicineList) {
            System.out.println(m);
            totalSum += m.getTotalPrice();
        }

        System.out.println("----------------------------------------------");
        System.out.printf("Tổng tiền: %.2f VNĐ\n", totalSum);

        // Xóa sạch danh sách sau khi in để phục vụ đơn tiếp theo
        medicineList.clear();
        System.out.println("Lưu ý: Đơn hàng đã được lưu và làm trống danh sách.");
    }

    private static void findCheapMedicine() {
        System.out.println("\n--- DANH SÁCH THUỐC GIÁ RẺ (< 50,000) ---");
        System.out.printf("%-10s %-20s %-12s\n", "Mã Thuốc", "Tên Thuốc", "Đơn Giá");
        boolean hasCheap = false;

        for (Medicine m : medicineList) {
            if (m.getUnitPrice() < 50000) {
                System.out.printf("%-10s %-20s %-12.2f\n", m.getDrugId(), m.getDrugName(), m.getUnitPrice());
                hasCheap = true;
            }
        }

        if (!hasCheap) {
            System.out.println("Không tìm thấy loại thuốc nào dưới 50.000 VNĐ.");
        }
    }
}