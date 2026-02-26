package Session13.bai3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class bai3 {
    public static <T> List<T> findCommonPatients(List<T> listA, List<T> listB) {
        List<T> commonList = new ArrayList<>();

        for (T patient : listA) {
            if (listB.contains(patient)) {
                if (!commonList.contains(patient)) {
                    commonList.add(patient);
                }
            }
        }
        return commonList;
    }

    public static void main(String[] args) {
        List<Integer> internalMedIds = Arrays.asList(101, 102, 105);
        List<Integer> surgeryIds = Arrays.asList(102, 105, 108);

        List<Integer> commonIds = findCommonPatients(internalMedIds, surgeryIds);
        System.out.println("Test Case 1 (Integer): " + commonIds);

        List<String> internalMedCodes = Arrays.asList("DN01", "DN02", "DN03");
        List<String> surgeryCodes = Arrays.asList("DN02", "DN04");

        List<String> commonCodes = findCommonPatients(internalMedCodes, surgeryCodes);
        System.out.println("Test Case 2 (String): " + commonCodes);
    }
}
