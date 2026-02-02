package Session7.bai1;

class Student {
    private String studentId;
    private String studentName;

    public static int totalStudents = 0;

    public Student(String studentId, String studentName) {
        this.studentId = studentId;
        this.studentName = studentName;
        totalStudents++;
    }

    public void display() {
        System.out.println("Sinh vien: " + studentName + " (ID: " + studentId + ")");
    }
}

public class bai1 {
    public static void main(String[] args) {
        Student s1 = new Student("SV01", "Nguyen Van An");
        Student s2 = new Student("SV02", "Le Thi Binh");
        Student s3 = new Student("SV03", "Tran Van Cuong");

        s1.display();
        s2.display();
        s3.display();

        System.out.println("------------------------------------");
        System.out.println("Tong so sinh vien da tao: " + Student.totalStudents);
    }
}