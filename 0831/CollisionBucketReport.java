import java.util.ArrayList;
import java.util.List;

public class CollisionBucketReport {

    private ArrayList<Integer>[] buckets;

    @SuppressWarnings("unchecked")
    public CollisionBucketReport(int bucketCount) {

        buckets = new ArrayList[bucketCount];

        for (int i = 0; i < bucketCount; i++) {
            buckets[i] = new ArrayList<>();
        }
    }

    public void add(int key) {

        int index =
                Math.floorMod(
                        key,
                        buckets.length
                );

        buckets[index].add(key);
    }

    public void report() {

        int totalCollision = 0;

        int longestChain = 0;

        for (int i = 0; i < buckets.length; i++) {

            System.out.println(
                    "Bucket "
                            + i
                            + "："
                            + buckets[i]
            );

            if (buckets[i].size() > 1) {

                totalCollision +=
                        buckets[i].size() - 1;
            }

            if (buckets[i].size()
                    > longestChain) {

                longestChain =
                        buckets[i].size();
            }
        }

        System.out.println();

        System.out.println(
                "Collision數量："
                        + totalCollision
        );

        System.out.println(
                "最長Chain："
                        + longestChain
        );
    }

    public static void main(String[] args) {

        System.out.println(
                "===== 一般測試 ====="
        );

        CollisionBucketReport report =
                new CollisionBucketReport(5);

        int[] keys = {
                10,
                15,
                20,
                25,
                30,
                -5,
                -10,
                10,
                7
        };

        for (int key : keys) {
            report.add(key);
        }

        report.report();


        System.out.println(
                "\n===== 空輸入測試 ====="
        );

        CollisionBucketReport emptyReport =
                new CollisionBucketReport(5);

        emptyReport.report();
    }
}