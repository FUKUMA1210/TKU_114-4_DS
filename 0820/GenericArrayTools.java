public class GenericArrayTools {

    static <T> int countMatches(T[] data, T target) {
        if (data == null) {
            return 0;
        }

        int count = 0;

        for (T value : data) {
            if (target == null) {
                if (value == null) {
                    count++;
                }
            } else {
                if (target.equals(value)) {
                    count++;
                }
            }
        }

        return count;
    }

    static <T> T last(T[] data) {
        if (data == null || data.length == 0) {
            return null;
        }

        return data[data.length - 1];
    }

    static <T> void swap(T[] data, int first, int second) {
        if (data == null) {
            return;
        }

        if (first < 0 || first >= data.length) {
            return;
        }

        if (second < 0 || second >= data.length) {
            return;
        }

        T temp = data[first];
        data[first] = data[second];
        data[second] = temp;
    }

    public static void main(String[] args) {

        String[] names = {"小明", "小華", "小明", "小美"};

        System.out.println("小明出現次數："
                + countMatches(names, "小明"));

        System.out.println("最後一個名字："
                + last(names));

        System.out.println("交換前：");

        for (String name : names) {
            System.out.print(name + " ");
        }

        System.out.println();

        swap(names, 0, 3);

        System.out.println("交換後：");

        for (String name : names) {
            System.out.print(name + " ");
        }

        System.out.println();

        String[] empty = {};

        System.out.println("空陣列最後一個："
                + last(empty));

        swap(names, -1, 2);
    }
}