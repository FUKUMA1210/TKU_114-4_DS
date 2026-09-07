import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CourseTagReport {

    public static void main(String[] args) {

        String[] tags = {
            "Java",
            "資料結構",
            "Java",
            "演算法",
            "資料結構",
            "Java",
            "資料庫"
        };

        List<String> originalList = new ArrayList<>();
        Set<String> uniqueSet = new LinkedHashSet<>();
        Map<String, Integer> countMap = new LinkedHashMap<>();

        for (String tag : tags) {

            originalList.add(tag);

            uniqueSet.add(tag);

            if (countMap.containsKey(tag)) {
                countMap.put(tag, countMap.get(tag) + 1);
            } else {
                countMap.put(tag, 1);
            }
        }

        System.out.println("原始順序List：");
        System.out.println(originalList);

        System.out.println();

        System.out.println("不重複標籤Set：");
        System.out.println(uniqueSet);

        System.out.println();

        System.out.println("標籤次數Map：");
        System.out.println(countMap);

        System.out.println();

        System.out.println("List用途：保存所有標籤以及原始順序。");
        System.out.println("Set用途：去除重複標籤。");
        System.out.println("Map用途：統計每個標籤出現的次數。");
    }
}