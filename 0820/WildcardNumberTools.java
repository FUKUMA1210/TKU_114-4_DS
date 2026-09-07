import java.util.ArrayList;
import java.util.List;

public class WildcardNumberTools {

    static double average(List<? extends Number> values) {

        if (values == null || values.size() == 0) {
            return 0.0;
        }

        double total = 0;

        for (Number value : values) {
            total += value.doubleValue();
        }

        return total / values.size();
    }

    static double maximum(List<? extends Number> values) {

        if (values == null || values.size() == 0) {
            return Double.NaN;
        }

        double max = values.get(0).doubleValue();

        for (Number value : values) {
            if (value.doubleValue() > max) {
                max = value.doubleValue();
            }
        }

        return max;
    }

    static void addRange(List<? super Integer> target,
                         int start, int end) {

        if (target == null) {
            return;
        }

        if (start > end) {
            return;
        }

        for (int i = start; i <= end; i++) {
            target.add(i);
        }
    }

    public static void main(String[] args) {

        List<Integer> integers = new ArrayList<>();
        integers.add(10);
        integers.add(20);
        integers.add(30);

        List<Double> doubles = new ArrayList<>();
        doubles.add(1.5);
        doubles.add(2.5);
        doubles.add(3.5);

        System.out.println("Integer平均值："
                + average(integers));

        System.out.println("Integer最大值："
                + maximum(integers));

        System.out.println("Double平均值："
                + average(doubles));

        System.out.println("Double最大值："
                + maximum(doubles));

        List<Integer> numbers = new ArrayList<>();

        addRange(numbers, 1, 5);

        System.out.println("加入1到5：");
        System.out.println(numbers);

        addRange(numbers, 10, 5);

        System.out.println("start > end 後：");
        System.out.println(numbers);

        List<Integer> empty = new ArrayList<>();

        System.out.println("空List平均值："
                + average(empty));

        System.out.println("空List最大值："
                + maximum(empty));
    }
}