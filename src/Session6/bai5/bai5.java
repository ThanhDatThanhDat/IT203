package Session6.bai5;

public class bai5 {
    static class Book {
        String maSach;
        String tenSach;
        double gia;

        public Book(String maSach, String tenSach, double gia) {
            this.maSach = maSach;
            this.tenSach = tenSach;
            this.gia = gia;
        }

        public void hienThiThongTin() {
            System.out.println("Ma sach: " + maSach);
            System.out.println("Ten sach: " + tenSach);
            System.out.println("Gia: " + gia);
            System.out.println("------------------------");
        }
    }

    public static void main(String[] args) {
        Book b1 = new Book("S001", "Java Co Ban", 120000);
        Book b2 = new Book("S002", "Python Nang Cao", 150000);

        System.out.println("Thong tin sach b1:");
        b1.hienThiThongTin();
        System.out.println("Thong tin sach b2:");
        b2.hienThiThongTin();
    }
}