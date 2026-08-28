import java.util.ArrayList;
import java.util.List;

public class Q05_BoundedBox<T extends Comparable<T>> {

    public static void main(String[] args) {

        Q05_BoundedBox<Integer> box = new Q05_BoundedBox<>(3);

        System.out.println(box.add(40));
        System.out.println(box.add(10));
        System.out.println(box.add(30));
        System.out.println(box.add(20));
        System.out.println(box.minimum());
        System.out.println(box.maximum());
        System.out.println(box.countGreaterThan(25));
        System.out.println(box.snapshot());
    }

    private final int capacity;
    private final List<T> values;

    public Q05_BoundedBox(int capacity) {

        if (capacity < 1) {
            throw new IllegalArgumentException();
        }

        this.capacity = capacity;
        this.values = new ArrayList<>();
    }

    public boolean add(T value) {

        if (value == null || isFull()) {
            return false;
        }

        values.add(value);
        return true;
    }

    public int size() {
        return values.size();
    }

    public boolean isFull() {
        return values.size() >= capacity;
    }

    public T minimum() {

        if (values.isEmpty()) {
            return null;
        }

        T minimum = values.get(0);

        for (T value : values) {
            if (value.compareTo(minimum) < 0) {
                minimum = value;
            }
        }

        return minimum;
    }

    public T maximum() {

        if (values.isEmpty()) {
            return null;
        }

        T maximum = values.get(0);

        for (T value : values) {
            if (value.compareTo(maximum) > 0) {
                maximum = value;
            }
        }

        return maximum;
    }

    public int countGreaterThan(T threshold) {

        if (threshold == null) {
            return 0;
        }

        int count = 0;

        for (T value : values) {
            if (value.compareTo(threshold) > 0) {
                count++;
            }
        }

        return count;
    }

    public List<T> snapshot() {
        return new ArrayList<>(values);
    }
}