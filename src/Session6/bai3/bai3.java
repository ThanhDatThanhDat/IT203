package Session6.bai3;

public class bai3 {
    static class Product {
        private String maSP;
        private String tenSP;
        private double giaBan;

        public Product(String maSP, String tenSP, double giaBan) {
            this.maSP = maSP;
            this.tenSP = tenSP;
            setGiaBan(giaBan);
        }

        public String getMaSP() {
            return maSP;
        }
        public String getTenSP() {
            return tenSP;
        }
        public double getGiaBan() {
            return giaBan;
        }

        public void setGiaBan(double giaBan) {
            if (giaBan > 0) {
                this.giaBan = giaBan;
            } else {
                System.out.println("Gia khong hop le! Vui long nhap gia > 0.");
            }
        }

        public void hienThiThongTin() {
            System.out.println("Ma SP: " + maSP);
            System.out.println("Ten SP: " + tenSP);
            System.out.println("Gia ban: " + giaBan);
            System.out.println("-------------------------");
        }
    }

    public static void main(String[] args) {
        Product sp1 = new Product("SP001", "Laptop Dell", 15000);
        Product sp2 = new Product("SP002", "Chuot Logitech", 500);

        System.out.println("Thong tin san pham ban dau:");
        sp1.hienThiThongTin();
        sp2.hienThiThongTin();

        System.out.println("Thu thay đdoi gia san pham sp2 thanh -100:");
        sp2.setGiaBan(-100);
        sp2.hienThiThongTin();

        System.out.println("Thay doi gia san pham sp2 thanh 700:");
        sp2.setGiaBan(700);
        sp2.hienThiThongTin();
    }
}