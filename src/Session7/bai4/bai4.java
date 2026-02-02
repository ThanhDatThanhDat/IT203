package Session7.bai4;

class ClassRoom {
    public String studentName;
    public static double classFund = 0;

    public ClassRoom(String studentName) {
        this.studentName = studentName;
    }

    public void contribute(double amount) {
        classFund += amount;
        System.out.println(studentName + " Da Dong gop: " + amount);
    }

    public static void showFund() {
        System.out.println(">>> TONG QUY LOP HIEN TAI: " + classFund);
    }
}

public class bai4 {
    public static void main(String[] args) {
        ClassRoom sv1 = new ClassRoom("Dat");
        ClassRoom sv2 = new ClassRoom("Le");
        ClassRoom sv3 = new ClassRoom("Doan");

        sv1.contribute(50000);
        sv2.contribute(100000);
        sv3.contribute(20000);

        System.out.println("-----------------------------");
        ClassRoom.showFund();
    }
}