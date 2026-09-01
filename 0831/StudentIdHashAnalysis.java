import java.util.ArrayList;
import java.util.List;

public class StudentIdHashAnalysis {

    public static void analyze(String[] studentIds, int bucketCount) {

        List<List<String>> buckets = new ArrayList<>();

        for (int i = 0; i < bucketCount; i++) {
            buckets.add(new ArrayList<>());
        }

        for (String id : studentIds) {

            int hash = id.hashCode();

            int index = Math.floorMod(hash, bucketCount);

            buckets.get(index).add(id);
        }

        int totalCollision = 0;
        int maxChain = 0;
        int totalItems = 0;

        System.out.println();
        System.out.println("========== Bucket Count = "
                + bucketCount + " ==========");

        for (int i = 0; i < buckets.size(); i++) {

            int count = buckets.get(i).size();

            System.out.println(
                    "Bucket " + i
                            + " 筆數:" + count
                            + " 資料:" + buckets.get(i)
            );

            totalItems += count;

            if (count > 1) {
                totalCollision += count - 1;
            }

            if (count > maxChain) {
                maxChain = count;
            }
        }

        double averageChain;

        if (bucketCount == 0) {
            averageChain = 0;
        } else {
            averageChain =
                    (double) totalItems / bucketCount;
        }

        System.out.println();
        System.out.println("總資料筆數：" + totalItems);
        System.out.println("總Collision次數：" + totalCollision);
        System.out.println("最大Chain長度：" + maxChain);
        System.out.println("平均Chain長度：" + averageChain);
    }

    public static void main(String[] args) {

        String[] studentIds = {
                "411630001",
                "411630002",
                "411630003",
                "411630004",
                "411630005",
                "411630006",
                "411630007",
                "411630008",
                "411630009",
                "411630010",
                "412630001",
                "412630002",
                "412630003",
                "412630004",
                "412630005"
        };

        analyze(studentIds, 5);

        analyze(studentIds, 10);
    }
}