import java.util.Arrays;
import java.util.List;

public class HeapPropertyValidator {

    public static boolean isMinHeap(
            List<Integer> data) {

        if (data == null) {
            return false;
        }

        for (int i = 0; i < data.size(); i++) {

            int left = i * 2 + 1;
            int right = i * 2 + 2;

            if (left < data.size()) {

                if (data.get(i) > data.get(left)) {
                    return false;
                }
            }

            if (right < data.size()) {

                if (data.get(i) > data.get(right)) {
                    return false;
                }
            }
        }

        return true;
    }

    public static boolean isMaxHeap(
            List<Integer> data) {

        if (data == null) {
            return false;
        }

        for (int i = 0; i < data.size(); i++) {

            int left = i * 2 + 1;
            int right = i * 2 + 2;

            if (left < data.size()) {

                if (data.get(i) < data.get(left)) {
                    return false;
                }
            }

            if (right < data.size()) {

                if (data.get(i) < data.get(right)) {
                    return false;
                }
            }
        }

        return true;
    }

    public static void main(String[] args) {

        List<Integer> minHeap =
                Arrays.asList(10, 20, 30, 40, 50, 60);

        List<Integer> maxHeap =
                Arrays.asList(60, 50, 40, 20, 30, 10);

        List<Integer> invalid =
                Arrays.asList(10, 5, 30);

        List<Integer> empty =
                Arrays.asList();

        List<Integer> single =
                Arrays.asList(100);


        System.out.println("===== Min Heap =====");

        System.out.println(
                minHeap + "："
                        + isMinHeap(minHeap)
        );

        System.out.println(
                invalid + "："
                        + isMinHeap(invalid)
        );


        System.out.println("\n===== Max Heap =====");

        System.out.println(
                maxHeap + "："
                        + isMaxHeap(maxHeap)
        );

        System.out.println(
                invalid + "："
                        + isMaxHeap(invalid)
        );


        System.out.println("\n===== 特殊情況 =====");

        System.out.println(
                "null：" + isMinHeap(null)
        );

        System.out.println(
                "empty：" + isMinHeap(empty)
        );

        System.out.println(
                "single：" + isMinHeap(single)
        );
    }
}