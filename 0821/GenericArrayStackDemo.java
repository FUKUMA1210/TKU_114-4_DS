class ArrayStack<T> {

    private T[] data;
    private int size;

    @SuppressWarnings("unchecked")
    public ArrayStack(int capacity) {

        if (capacity < 1) {
            capacity = 1;
        }

        data = (T[]) new Object[capacity];
        size = 0;
    }

    public boolean push(T value) {

        if (value == null || isFull()) {
            return false;
        }

        data[size] = value;
        size++;

        return true;
    }

    public T pop() {

        if (isEmpty()) {
            return null;
        }

        size--;

        T value = data[size];

        data[size] = null;

        return value;
    }

    public T peek() {

        if (isEmpty()) {
            return null;
        }

        return data[size - 1];
    }

    public int size() {

        return size;
    }

    public boolean isEmpty() {

        return size == 0;
    }

    public boolean isFull() {

        return size == data.length;
    }
}

public class GenericArrayStackDemo {

    public static void main(String[] args) {

        System.out.println("String Stack");

        ArrayStack<String> stringStack =
                new ArrayStack<>(3);

        System.out.println("push A："
                + stringStack.push("A"));

        System.out.println("push B："
                + stringStack.push("B"));

        System.out.println("push C："
                + stringStack.push("C"));

        System.out.println("push D："
                + stringStack.push("D"));

        System.out.println("peek："
                + stringStack.peek());

        System.out.println("pop："
                + stringStack.pop());

        System.out.println("size："
                + stringStack.size());

        System.out.println("isEmpty："
                + stringStack.isEmpty());

        System.out.println("isFull："
                + stringStack.isFull());

        System.out.println();

        System.out.println("Integer Stack");

        ArrayStack<Integer> intStack =
                new ArrayStack<>(3);

        System.out.println("push 10："
                + intStack.push(10));

        System.out.println("push 20："
                + intStack.push(20));

        System.out.println("push 30："
                + intStack.push(30));

        System.out.println("peek："
                + intStack.peek());

        System.out.println("pop："
                + intStack.pop());

        System.out.println("size："
                + intStack.size());

        System.out.println("isEmpty："
                + intStack.isEmpty());

        System.out.println("isFull："
                + intStack.isFull());
    }
}