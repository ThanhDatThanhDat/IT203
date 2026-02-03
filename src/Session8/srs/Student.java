package Session8.srs;

public class Student {
    private String id, name, gender, rank;
    private int age;
    private double math, physics, chemistry, avg;

    public Student(String id, String name, int age, String gender, double m, double p, double c) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.math = m; this.physics = p; this.chemistry = c;
        updateStats(); // Tự động tính điểm và xếp loại khi khởi tạo
    }

    // [FR6] Tính điểm trung bình và xếp loại
    public void updateStats() {
        this.avg = (math + physics + chemistry) / 3;
        if (avg >= 8.0 && math >= 6.5 && physics >= 6.5 && chemistry >= 6.5) rank = "Giỏi";
        else if (avg >= 6.5 && math >= 5.0 && physics >= 5.0 && chemistry >= 5.0) rank = "Khá";
        else if (avg >= 5.0 && math >= 3.5 && physics >= 3.5 && chemistry >= 3.5) rank = "Trung bình";
        else rank = "Yếu";
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public double getAvg() { return avg; }
    public String getRank() { return rank; }

    public void setName(String name) { this.name = name; }
    public void setScores(double m, double p, double c) {
        this.math = m; this.physics = p; this.chemistry = c;
        updateStats();
    }

    // Hiển thị 1 dòng sinh viên
    public void display() {
        System.out.printf("| %-8s | %-18s | %-4d | %-8s | %-7.2f | %-10s |\n",
                id, name, age, gender, avg, rank);
    }
}

