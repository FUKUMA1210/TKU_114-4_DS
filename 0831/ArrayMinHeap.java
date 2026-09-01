import java.util.Arrays;
import java.util.NoSuchElementException;

public class ArrayMinHeap {

    private int[] data;
    private int size;

    public ArrayMinHeap() {
        data = new int[10];
        size = 0;
    }

    public void add(int value) {
        if (size == data.length) {
            expand();
        }

        data[size] = value;
        bubbleUp(size);
        size++;
    }

    private void expand() {
        int[] newData = new int[data.length * 2];

        for (int i = 0; i < data.length; i++) {
            newData[i] = data[i];
        }

        data = newData;

        System.out.println("容量擴充為：" + data.length);
    }

    private void bubbleUp(int index) {
        while (index > 0) {
            int parent = (index - 1) / 2;

            if (data[parent] <= data[index]) {
                break;
            }

            swap(parent, index);
            index = parent;
        }
    }

    public int remove() {
        if (size == 0) {
            throw new NoSuchElementException("Heap是空的");
        }

        int result = data[0];

        size--;
        data[0] = data[size];

        if (size > 0) {
            bubbleDown(0);
        }

        return result;
    }

    private void bubbleDown(int index) {
        while (true) {
            int left = index * 2 + 1;
            int right = index * 2 + 2;

            if (left >= size) {
                break;
            }

            int smaller = left;

            if (right < size && data[right] < data[left]) {
                smaller = right;
            }

            if (data[index] <= data[smaller]) {
                break;
            }

            swap(index, smaller);
            index = smaller;
        }
    }

    public int peek() {
        if (size == 0) {
            throw new NoSuchElementException("Heap是空的");
        }

        return data[0];
    }

    public int[] snapshot() {
        return Arrays.copyOf(data, size);
    }

    public int size() {
        return size;
    }

    private void swap(int a, int b) {
        int temp = data[a];
        data[a] = data[b];
        data[b] = temp;
    }

    public static void main(String[] args) {
        ArrayMinHeap heap = new ArrayMinHeap();

        int[] numbers = {
                30, 10, 20, 50, 40,
                15, 5, 60, 35, 25,
                45, 70, 1, 55, 80,
                12, 8, 90, 3, 65,
                18, 22
        };

        for (int number : numbers) {
            heap.add(number);
        }

        System.out.println();
        System.out.println("目前Heap：" + Arrays.toString(heap.snapshot()));
        System.out.println("最小值：" + heap.peek());

        System.out.println();
        System.out.println("依序移除：");

        while (heap.size() > 0) {
            System.out.print(heap.remove() + " ");
        }
    }
}