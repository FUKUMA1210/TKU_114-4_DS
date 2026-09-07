import java.util.Arrays;

class DynamicArray<T> {

    private Object[] data;
    private int size;

    public DynamicArray(int capacity) {

        if (capacity < 1) {
            capacity = 1;
        }

        data = new Object[capacity];
        size = 0;
    }

    public void add(T value) {

        ensureCapacity();

        data[size] = value;
        size++;
    }

    public void add(int index, T value) {

        checkInsertIndex(index);

        ensureCapacity();

        for (int i = size; i > index; i--) {
            data[i] = data[i - 1];
        }

        data[index] = value;
        size++;
    }

    @SuppressWarnings("unchecked")
    public T get(int index) {

        checkIndex(index);

        return (T) data[index];
    }

    @SuppressWarnings("unchecked")
    public T set(int index, T value) {

        checkIndex(index);

        T oldValue = (T) data[index];

        data[index] = value;

        return oldValue;
    }

    @SuppressWarnings("unchecked")
    public T remove(int index) {

        checkIndex(index);

        T removed = (T) data[index];

        for (int i = index; i < size - 1; i++) {
            data[i] = data[i + 1];
        }

        size--;

        data[size] = null;

        return removed;
    }

    public int size() {

        return size;
    }

    public int capacity() {

        return data.length;
    }

    private void ensureCapacity() {

        if (size == data.length) {

            Object[] newData =
                    new Object[data.length * 2];

            for (int i = 0; i < data.length; i++) {
                newData[i] = data[i];
            }

            data = newData;

            System.out.println(
                    "容量擴充為：" + data.length);
        }
    }

    private void checkIndex(int index) {

        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException(
                    "index=" + index);
        }
    }

    private void checkInsertIndex(int index) {

        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException(
                    "index=" + index);
        }
    }

    @Override
    public String toString() {

        return Arrays.toString(
                Arrays.copyOf(data, size));
    }
}

public class DynamicArrayPractice {

    public static void main(String[] args) {

        System.out.println("String測試");

        DynamicArray<String> strings =
                new DynamicArray<>(2);

        strings.add("A");
        strings.add("B");

        System.out.println("目前：" + strings);
        System.out.println("size：" + strings.size());
        System.out.println("capacity：" + strings.capacity());

        strings.add("C");

        System.out.println("加入 C：" + strings);
        System.out.println("capacity：" + strings.capacity());

        strings.add(1, "X");

        System.out.println("位置1插入 X：" + strings);

        System.out.println("get(2)："
                + strings.get(2));

        System.out.println("set(2, Y) 原值："
                + strings.set(2, "Y"));

        System.out.println("修改後：" + strings);

        System.out.println("remove(1)："
                + strings.remove(1));

        System.out.println("刪除後：" + strings);

        System.out.println();

        System.out.println("Integer測試");

        DynamicArray<Integer> numbers =
                new DynamicArray<>(2);

        numbers.add(10);
        numbers.add(20);
        numbers.add(30);

        System.out.println("目前：" + numbers);

        numbers.add(1, 15);

        System.out.println("插入15：" + numbers);

        System.out.println("remove(2)："
                + numbers.remove(2));

        System.out.println("刪除後：" + numbers);

        System.out.println();

        System.out.println("錯誤index測試");

        try {
            numbers.get(-1);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("get(-1)：index不合法");
        }

        try {
            numbers.get(numbers.size());
        } catch (IndexOutOfBoundsException e) {
            System.out.println(
                    "get(size)：index不合法");
        }

        try {
            numbers.add(numbers.size() + 1, 99);
        } catch (IndexOutOfBoundsException e) {
            System.out.println(
                    "add(size + 1)：index不合法");
        }

        try {
            DynamicArray<Integer> empty =
                    new DynamicArray<>(2);

            empty.remove(0);

        } catch (IndexOutOfBoundsException e) {
            System.out.println(
                    "空結構 remove：index不合法");
        }
    }
}