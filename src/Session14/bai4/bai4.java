package Session14.bai4;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class bai4 {
    public static void main(String[] args) {
        List<String> danhSachCaBenh = Arrays.asList(
                "Cúm A", "Sốt xuất huyết", "Cúm A", "Covid-19", "Cúm A", "Sốt xuất huyết"
        );

        Map<String, Integer> thongKeDichTe = new TreeMap<>();

        for (String tenBenh : danhSachCaBenh) {
            if (thongKeDichTe.containsKey(tenBenh)) {
                thongKeDichTe.put(tenBenh, thongKeDichTe.get(tenBenh) + 1);
            } else {
                thongKeDichTe.put(tenBenh, 1);
            }
        }

        System.out.println("--- BÁO CÁO DỊCH TỄ GỬI SỞ Y TẾ ---");
        for (Map.Entry<String, Integer> entry : thongKeDichTe.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue() + " ca");
        }
    }
}