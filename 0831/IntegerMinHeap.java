import java.util.ArrayList;
import java.util.NoSuchElementException;

public class IntegerMinHeap {

    private ArrayList<Integer> heap = new ArrayList<>();

    public void add(int value) {

        heap.add(value);

        int current = heap.size() - 1;

        while (current > 0) {

            int parent = (current - 1) / 2;

            if (heap.get(current) >= heap.get(parent)) {
                break;
            }

            swap(current, parent);

            current = parent;
        }
    }

    public int peek() {

        if (heap.isEmpty()) {
            throw new NoSuchElementException("Heap是空的");
        }

        return heap.get(0);
    }

    public int removeMin() {

        if (heap.isEmpty()) {
            throw new NoSuchElementException("Heap是空的");
        }

        int min = heap.get(0);

        int last = heap.remove(heap.size() - 1);

        if (!heap.isEmpty()) {

            heap.set(0, last);

            int current = 0;

            while (true) {

                int left = current * 2 + 1;
                int right = current * 2 + 2;
                int smallest = current;

                if (left < heap.size()
                        && heap.get(left) < heap.get(smallest)) {

                    smallest = left;
                }

                if (right < heap.size()
                        && heap.get(right) < heap.get(smallest)) {

                    smallest = right;
                }

                if (smallest == current) {
                    break;
                }

                swap(current, smallest);

                current = smallest;
            }
        }

        return min;
    }

    private void swap(int first, int second) {

        int temp = heap.get(first);

        heap.set(first, heap.get(second));

        heap.set(second, temp);
    }

    public int size() {
        return heap.size();
    }

    public boolean isEmpty() {
        return heap.isEmpty();
    }

    public static void main(String[] args) {

        IntegerMinHeap heap = new IntegerMinHeap();

        int[] values = {40, 10, 50, 20, 30, 5};

        for (int value : values) {
            heap.add(value);
        }

        System.out.println("目前最小值：" + heap.peek());

        System.out.println("\n依序移除：");

        while (!heap.isEmpty()) {
            System.out.println(heap.removeMin());
        }


        System.out.println("\n測試空Heap：");

        try {
            heap.peek();
        } catch (NoSuchElementException e) {
            System.out.println(e.getMessage());
        }

        try {
            heap.removeMin();
        } catch (NoSuchElementException e) {
            System.out.println(e.getMessage());
        }
    }
}