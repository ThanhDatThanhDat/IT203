package Session13.bai2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class bai2 {
    public static void main(String[] args) {
        List<String> rawList = Arrays.asList("Paracetamol", "Ibuprofen", "Panadol", "Paracetamol", "Aspirin", "Ibuprofen");
        System.out.println("Danh sách nhập kho: " + rawList);

        List<String> uniqueList = new ArrayList<>();
        for (String medicine : rawList) {
            if (!uniqueList.contains(medicine)) {
                uniqueList.add(medicine);
            }
        }

        Collections.sort(uniqueList);

        System.out.println("Danh sách danh mục thuốc: " + uniqueList);
    }
}