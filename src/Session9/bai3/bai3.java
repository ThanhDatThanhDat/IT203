package Session9.bai3;

class Employee {
    protected String ten;
    protected double luongCoBan;

    public Employee(String ten, double luongCoBan) {
        this.ten = ten;
        this.luongCoBan = luongCoBan;
        System.out.println("-> Constructor Employee đã được gọi.");
    }

    public void hienThiThongTin() {
        System.out.println("Tên nhân viên: " + ten);
        System.out.println("Lương cơ bản: " + luongCoBan);
    }
}

class Manager extends Employee {
    private String phongBan;

    public Manager(String ten, double luongCoBan, String phongBan) {
        super(ten, luongCoBan);
        this.phongBan = phongBan;
        System.out.println("-> Constructor Manager đã được gọi.");
    }

    @Override
    public void hienThiThongTin() {
        super.hienThiThongTin();
        System.out.println("Phòng ban quản lý: " + phongBan);
    }
}

public class bai3 {
    public static void main(String[] args) {
        System.out.println("--- Khởi tạo đối tượng Manager ---");
        Manager mng = new Manager("Trần Văn B", 15000000, "Phòng Công Nghệ");
        System.out.println("\n--- Kết quả hiển thị thông tin ---");
        mng.hienThiThongTin();
    }
}