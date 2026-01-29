package Session5.HN_KS24_CNTT4_PHAMTHANHDAT;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class kiemTra {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] arrayList = new String[100];
        int count = 0;
        int choice;

        do {
            System.out.println("\n========== MENU ==========");
            System.out.println("1. Hien thi danh sach MSSV.");
            System.out.println("2. Them moi.");
            System.out.println("3. Cap nhat.");
            System.out.println("4. Xoa.");
            System.out.println("5. Tim kiem.");
            System.out.println("0. Thoat.");
            System.out.print("Nhap lua chon : ");

            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1: {
                    if(count == 0){
                        System.out.println("Danh sach rong");
                    }else{
                        System.out.println("Danh sach MSSV : ");
                        for (int i=0; i<count; i++){
                            System.out.print((i+1)+ "," + arrayList[i]);
                        }
                    }
                    System.out.println("\n");
                    break;
                }
                case 2: {
                    String regex = "^B\\d{7}$";
                    Pattern pattern = Pattern.compile(regex);

                    while (true) {
                        System.out.print("Nhap MSSV moi: ");
                        String mssv = sc.nextLine();

                        Matcher matcher = pattern.matcher(mssv);

                        if (matcher.matches()) {
                            arrayList[count] = mssv;
                            count++;
                            System.out.println("Them MSSV thanh cong!");
                            break;
                        } else {
                            System.out.println("Sai dinh dang! MSSV phai bat dau bang B va theo sau la 7 chu so.");
                        }
                    }
                    break;
                }
                case 3: {
                    System.out.print("Nhap vi tri can cap nhat: ");
                    int index = sc.nextInt();
                    sc.nextLine();

                    if (index < 1 || index > count) {
                        System.out.println("Vi tri khong hop le!");
                    } else {
                        String regex = "^B\\d{7}$";
                        Pattern pattern = Pattern.compile(regex);

                        while (true) {
                            System.out.print("Nhap MSSV moi: ");
                            String newMssv = sc.nextLine();

                            Matcher matcher = pattern.matcher(newMssv);

                            if (matcher.matches()) {
                                arrayList[index - 1] = newMssv;
                                System.out.println("Cap nhat thanh cong!");
                                break;
                            } else {
                                System.out.println("Sai dinh dang! MSSV phai bat dau bang B va theo sau la 7 chu so.");
                            }
                        }
                    }
                    break;
                }
                case 4: {
                    System.out.print("Nhap MSSV can xoa: ");
                    String deleteMssv = sc.nextLine();

                    int pos = -1;

                    for (int i = 0; i < count; i++) {
                        if (arrayList[i].equals(deleteMssv)) {
                            pos = i;
                            break;
                        }
                    }

                    if (pos == -1) {
                        System.out.println("Khong tim thay MSSV can xoa!");
                    } else {
                        for (int i = pos; i < count - 1; i++) {
                            arrayList[i] = arrayList[i + 1];
                        }
                        count--;
                        System.out.println("Xoa MSSV thanh cong!");
                    }
                    break;
                }
                case 5: {
                    System.out.print("Nhap chuoi can tim: ");
                    String keyword = sc.nextLine();

                    String regex = "(?i).*" + keyword + ".*";
                    Pattern pattern = Pattern.compile(regex);

                    boolean found = false;
                    System.out.println("Danh sach MSSV tim duoc:");

                    for (int i = 0; i < count; i++) {
                        Matcher matcher = pattern.matcher(arrayList[i]);
                        if (matcher.matches()) {
                            System.out.println(arrayList[i]);
                            found = true;
                        }
                    }

                    if (!found) {
                        System.out.println("Khong co MSSV nao phu hop!");
                    }
                    break;
                }
                case 6:
                    System.out.println("Thoat chuong trinh.");
                    break;
                default:
                    System.out.println("Lua chon khong hop le!");
            }
        } while (choice != 6);
    }
}