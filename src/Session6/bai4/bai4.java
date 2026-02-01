package Session6.bai4;

public class bai4 {
    static class Employee {
        String maNV;
        String hoTen;
        double luong;

        public Employee() {
            this.maNV = "Chưa có";
            this.hoTen = "Chưa có";
            this.luong = 0;
        }

        public Employee(String maNV, String hoTen) {
            this.maNV = maNV;
            this.hoTen = hoTen;
            this.luong = 0;
        }

        public Employee(String maNV, String hoTen, double luong) {
            this.maNV = maNV;
            this.hoTen = hoTen;
            this.luong = luong;
        }

        public void hienThiThongTin() {
            System.out.println("Ma NV: " + maNV);
            System.out.println("Ho tan: " + hoTen);
            System.out.println("Luong: " + luong);
            System.out.println("--------------------------");
        }
    }

    public static void main(String[] args) {
        Employee nv1 = new Employee();
        Employee nv2 = new Employee("NV001", "Nguyen Van A");
        Employee nv3 = new Employee("NV002", "Tran Thi B", 12000);

        System.out.println("Thong tin nhan vien nv1 (khong tham so):");
        nv1.hienThiThongTin();
        System.out.println("Thong tin nhan vien nv2 (ma + ten):");
        nv2.hienThiThongTin();
        System.out.println("Thong tin nhan vien nv3 (day du):");
        nv3.hienThiThongTin();
    }
}