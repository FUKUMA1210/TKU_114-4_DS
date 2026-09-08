import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

public class LowestKPriceTracker {

    public static List<Integer> findLowestK(
            List<Integer> prices,
            int k) {

        List<Integer> result = new ArrayList<>();

        if (prices == null || k <= 0) {
            return result;
        }

        PriorityQueue<Integer> maxHeap =
                new PriorityQueue<>(
                        Collections.reverseOrder()
                );

        for (Integer price : prices) {

            if (price == null) {
                continue;
            }

            if (price < 0) {
                continue;
            }

            if (maxHeap.size() < k) {

                maxHeap.add(price);

            } else if (price < maxHeap.peek()) {

                maxHeap.poll();

                maxHeap.add(price);
            }
        }

        result.addAll(maxHeap);

        Collections.sort(result);

        return result;
    }

    public static void main(String[] args) {

        List<Integer> prices =
                new ArrayList<>();

        prices.add(100);
        prices.add(50);
        prices.add(300);
        prices.add(20);
        prices.add(80);
        prices.add(null);
        prices.add(-10);
        prices.add(40);

        System.out.println(
                "最低3個價格："
                        + findLowestK(prices, 3)
        );

        System.out.println(
                "K = 0："
                        + findLowestK(prices, 0)
        );

        System.out.println(
                "K = -1："
                        + findLowestK(prices, -1)
        );
    }
}