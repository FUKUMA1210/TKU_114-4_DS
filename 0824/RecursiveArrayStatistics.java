public class RecursiveArrayStatistics {

    public static int maximum(int[] values) {

        if (values == null || values.length == 0) {
            throw new IllegalArgumentException(
                    "array不可以是null或empty");
        }

        return maximum(values, 0);
    }

    private static int maximum(int[] values, int index) {

        if (index == values.length - 1) {
            return values[index];
        }

        int rest = maximum(values, index + 1);

        if (values[index] > rest) {
            return values[index];
        }

        return rest;
    }

    public static int minimum(int[] values) {

        if (values == null || values.length == 0) {
            throw new IllegalArgumentException(
                    "array不可以是null或empty");
        }

        return minimum(values, 0);
    }

    private static int minimum(int[] values, int index) {

        if (index == values.length - 1) {
            return values[index];
        }

        int rest = minimum(values, index + 1);

        if (values[index] < rest) {
            return values[index];
        }

        return rest;
    }

    public static int countAbove(int[] values, int target) {

        if (values == null || values.length == 0) {
            throw new IllegalArgumentException(
                    "array不可以是null或empty");
        }

        return countAbove(values, target, 0);
    }

    private static int countAbove(
            int[] values, int target, int index) {

        if (index == values.length) {
            return 0;
        }

        int count = 0;

        if (values[index] > target) {
            count = 1;
        }

        return count
                + countAbove(values, target, index + 1);
    }

    public static void main(String[] args) {

        int[] values = {10, 25, 7, 40, 18, 32};

        System.out.println("陣列最大值："
                + maximum(values));

        System.out.println("陣列最小值："
                + minimum(values));

        System.out.println("大於20的數量："
                + countAbove(values, 20));

        System.out.println();

        try {
            maximum(null);
        } catch (IllegalArgumentException e) {
            System.out.println(
                    "null array：IllegalArgumentException");
        }

        try {
            minimum(new int[0]);
        } catch (IllegalArgumentException e) {
            System.out.println(
                    "empty array：IllegalArgumentException");
        }
    }
}