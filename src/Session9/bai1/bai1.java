package Session9.bai1;

class Person {
    protected String hoTen;
    protected int tuoi;

    public Person(String hoTen, int tuoi) {
        this.hoTen = hoTen;
        this.tuoi = tuoi;
    }

    public void hienThiThongTin() {
        System.out.println("Họ tên: " + hoTen);
        System.out.println("Tuổi: " + tuoi);
    }
}

class Student extends Person {
    private String maSV;
    private double diemTB;

    public Student(String hoTen, int tuoi, String maSV, double diemTB) {
        super(hoTen, tuoi);
        this.maSV = maSV;
        this.diemTB = diemTB;
    }

    @Override
    public void hienThiThongTin() {
        super.hienThiThongTin();
        System.out.println("Mã sinh viên: " + maSV);
        System.out.println("Điểm trung bình: " + diemTB);
    }
}

public class bai1 {
    public static void main(String[] args) {
        Student sv = new Student("Nguyễn Văn A", 20, "SV001", 8.5);

        System.out.println("=== THÔNG TIN SINH VIÊN ===");
        sv.hienThiThongTin();
    }
}