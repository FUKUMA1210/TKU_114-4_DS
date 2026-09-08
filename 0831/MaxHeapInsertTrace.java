import java.util.ArrayList;
import java.util.List;

public class MaxHeapInsertTrace {

    private ArrayList<Integer> heap = new ArrayList<>();

    public void add(int value) {

        heap.add(value);

        int current = heap.size() - 1;

        while (current > 0) {
            int parent = (current - 1) / 2;

            if (heap.get(current) <= heap.get(parent)) {
                break;
            }

            int temp = heap.get(current);
            heap.set(current, heap.get(parent));
            heap.set(parent, temp);

            current = parent;
        }
    }

    public int peekMax() {

        if (heap.isEmpty()) {
            throw new IllegalStateException("Heap是空的");
        }

        return heap.get(0);
    }

    public List<Integer> snapshot() {
        return new ArrayList<>(heap);
    }

    public static void main(String[] args) {

        MaxHeapInsertTrace heap = new MaxHeapInsertTrace();

        int[] values = {25, 40, 10, 50, 30, 50};

        for (int value : values) {
            heap.add(value);

            System.out.println(
                    "加入" + value + "後：" + heap.snapshot()
            );
        }

        System.out.println();
        System.out.println("最大值：" + heap.peekMax());
    }
}