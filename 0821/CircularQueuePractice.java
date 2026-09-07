import java.util.Arrays;

class CircularQueue<T> {

    private Object[] data;
    private int front;
    private int rear;
    private int size;

    public CircularQueue(int capacity) {

        if (capacity < 1) {
            capacity = 1;
        }

        data = new Object[capacity];

        front = 0;
        rear = 0;
        size = 0;
    }

    public boolean enqueue(T value) {

        if (isFull()) {
            return false;
        }

        data[rear] = value;

        rear = (rear + 1) % data.length;

        size++;

        return true;
    }

    @SuppressWarnings("unchecked")
    public T dequeue() {

        if (isEmpty()) {
            return null;
        }

        T value = (T) data[front];

        data[front] = null;

        front = (front + 1) % data.length;

        size--;

        return value;
    }

    @SuppressWarnings("unchecked")
    public T peek() {

        if (isEmpty()) {
            return null;
        }

        return (T) data[front];
    }

    public boolean isEmpty() {

        return size == 0;
    }

    public boolean isFull() {

        return size == data.length;
    }

    public void printState() {

        System.out.println(
                "array=" + Arrays.toString(data)
                + ", front=" + front
                + ", rear=" + rear
                + ", size=" + size);
    }
}

public class CircularQueuePractice {

    static void enqueue(
            CircularQueue<String> queue,
            String value) {

        System.out.println("enqueue " + value);

        queue.enqueue(value);

        queue.printState();
    }

    static void dequeue(
            CircularQueue<String> queue) {

        String value = queue.dequeue();

        System.out.println(
                "dequeue -> " + value);

        queue.printState();
    }

    public static void main(String[] args) {

        CircularQueue<String> queue =
                new CircularQueue<>(4);

        enqueue(queue, "A");
        enqueue(queue, "B");
        enqueue(queue, "C");

        dequeue(queue);
        dequeue(queue);

        enqueue(queue, "D");
        enqueue(queue, "E");
        enqueue(queue, "F");

        dequeue(queue);

        enqueue(queue, "G");

        System.out.println();
        System.out.println("FIFO順序：");

        while (!queue.isEmpty()) {

            System.out.println(
                    "取出：" + queue.dequeue());
        }

        queue.printState();
    }
}