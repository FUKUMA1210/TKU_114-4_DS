public class RecursiveCallReport {

    public static int sum(int[] data, int index) {

        // Base case
        if (data == null || index >= data.length) {
            System.out.println("index=" + index + "，到結尾，return 0");
            return 0;
        }

        System.out.println("進入：index=" + index
                + "，current value=" + data[index]);

        int recursiveResult = sum(data, index + 1);

        int result = data[index] + recursiveResult;

        System.out.println("index=" + index
                + "，current value=" + data[index]
                + "，recursive result=" + recursiveResult
                + "，return value=" + result);

        return result;
    }

    public static void main(String[] args) {

        System.out.println("===== 一般陣列 =====");

        int[] data1 = {10, 20, 30, 40};

        int result1 = sum(data1, 0);

        System.out.println("總和：" + result1);


        System.out.println("\n===== 單一元素 =====");

        int[] data2 = {100};

        int result2 = sum(data2, 0);

        System.out.println("總和：" + result2);


        System.out.println("\n===== 空陣列 =====");

        int[] data3 = {};

        int result3 = sum(data3, 0);

        System.out.println("總和：" + result3);
    }
}