package Session13.bai5;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Patient {
    private int id;
    private String name;
    private int age;
    private String gender;
    private String pathology;

    public Patient() {}

    public Patient(int id, String name, int age, String gender, String pathology) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.pathology = pathology;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }

    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }

    public String getPathology() { return pathology; }
    public void setPathology(String pathology) { this.pathology = pathology; }

    @Override
    public String toString() {
        return String.format("ID: %d | Tên: %-15s | Tuổi: %d | Giới tính: %-5s | Bệnh lý: %s",
                id, name, age, gender, pathology);
    }
}

class PatientManager {
    private List<Patient> patients;

    public PatientManager() {
        patients = new ArrayList<>();
    }

    public boolean addPatient(Patient p) {
        return patients.add(p);
    }

    public Patient removePatient(int patientId) {
        int found = -1;
        for (int i = 0; i < patients.size(); i++) {
            if (patients.get(i).getId() == patientId) {
                found = i;
                break;
            }
        }
        if (found == -1) return null;
        return patients.remove(found);
    }

    public Patient updatePatient(int patientId, Patient updatedPatient) {
        int found = -1;
        for (int i = 0; i < patients.size(); i++) {
            if (patients.get(i).getId() == patientId) {
                found = i;
                break;
            }
        }
        if (found == -1) return null;
        return patients.set(found, updatedPatient);
    }

    public void searchPatientByName(String name) {
        int count = 0;
        System.out.println("\n--- Kết quả tìm kiếm ---");
        for (Patient p : patients) {
            if (p.getName().toLowerCase().contains(name.toLowerCase())) {
                System.out.println(p);
                count++;
            }
        }
        System.out.println("=> Tìm thấy " + count + " kết quả!");
    }

    public void displayPatients() {
        if (patients.isEmpty()) {
            System.out.println("Danh sách trống!");
            return;
        }
        System.out.println("\n--- Danh sách bệnh nhân ---");
        for (Patient p : patients) {
            System.out.println(p);
        }
    }

    public boolean checkIdExisted(int id) {
        for (Patient p : patients) {
            if (p.getId() == id) return true;
        }
        return false;
    }
}

public class bai5 {
    private static PatientManager patientManager = new PatientManager();
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n========= QUẢN LÝ BỆNH NHÂN =========");
            System.out.println("1. Thêm bệnh nhân (Create)");
            System.out.println("2. Xóa bệnh nhân (Delete)");
            System.out.println("3. Cập nhật thông tin (Update)");
            System.out.println("4. Tìm kiếm bệnh nhân (Search)");
            System.out.println("5. Hiển thị danh sách (Display)");
            System.out.println("6. Thoát");
            System.out.print("Mời nhập lựa chọn: ");

            try {
                int choose = Integer.parseInt(sc.nextLine());
                switch (choose) {
                    case 1:
                        handleCreate();
                        break;
                    case 2:
                        handleDelete();
                        break;
                    case 3:
                        handleUpdate();
                        break;
                    case 4:
                        handleSearch();
                        break;
                    case 5:
                        patientManager.displayPatients();
                        break;
                    case 6:
                        System.out.println("Đã thoát chương trình.");
                        System.exit(0);
                    default:
                        System.out.println("Lựa chọn từ 1-6!");
                }
            } catch (Exception e) {
                System.out.println("Lỗi nhập liệu, vui lòng thử lại!");
            }
        }
    }

    private static void handleCreate() {
        System.out.print("Nhập mã bệnh nhân: ");
        int id = Integer.parseInt(sc.nextLine());
        if (patientManager.checkIdExisted(id)) {
            System.out.println("Mã bệnh nhân đã tồn tại!");
            return;
        }
        System.out.print("Nhập tên: ");
        String name = sc.nextLine();
        System.out.print("Nhập tuổi: ");
        int age = Integer.parseInt(sc.nextLine());
        System.out.print("Nhập giới tính: ");
        String gender = sc.nextLine();
        System.out.print("Nhập bệnh lý: ");
        String pathology = sc.nextLine();

        Patient p = new Patient(id, name, age, gender, pathology);
        if (patientManager.addPatient(p)) {
            System.out.println("Thêm thành công!");
        }
    }

    private static void handleDelete() {
        System.out.print("Nhập mã cần xóa: ");
        int id = Integer.parseInt(sc.nextLine());
        Patient p = patientManager.removePatient(id);
        if (p != null) {
            System.out.println("Đã xóa bệnh nhân: " + p.getName());
        } else {
            System.out.println("Không tìm thấy mã bệnh nhân này!");
        }
    }

    private static void handleUpdate() {
        System.out.print("Nhập mã cần cập nhật: ");
        int id = Integer.parseInt(sc.nextLine());
        if (!patientManager.checkIdExisted(id)) {
            System.out.println("Mã bệnh nhân không tồn tại!");
            return;
        }
        System.out.print("Tên mới: ");
        String name = sc.nextLine();
        System.out.print("Tuổi mới: ");
        int age = Integer.parseInt(sc.nextLine());
        System.out.print("Giới tính mới: ");
        String gender = sc.nextLine();
        System.out.print("Bệnh lý mới: ");
        String pathology = sc.nextLine();

        Patient pNew = new Patient(id, name, age, gender, pathology);
        if (patientManager.updatePatient(id, pNew) != null) {
            System.out.println("Cập nhật thành công!");
        }
    }

    private static void handleSearch() {
        System.out.print("Nhập tên cần tìm: ");
        String name = sc.nextLine();
        patientManager.searchPatientByName(name);
    }
}