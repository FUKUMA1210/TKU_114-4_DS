import java.util.ArrayList;
import java.util.List;

public class BstRangeStatistics {

    static class Node {

        int value;
        Node left;
        Node right;

        Node(int value) {
            this.value = value;
        }
    }

    private Node root;

    public void add(int value) {

        if (root == null) {
            root = new Node(value);
            return;
        }

        Node current = root;

        while (true) {

            if (value == current.value) {
                return;
            }

            if (value < current.value) {

                if (current.left == null) {
                    current.left = new Node(value);
                    return;
                }

                current = current.left;

            } else {

                if (current.right == null) {
                    current.right = new Node(value);
                    return;
                }

                current = current.right;
            }
        }
    }

    public List<Integer> valuesBetween(
            int low,
            int high) {

        List<Integer> result =
                new ArrayList<>();

        if (low > high) {
            return result;
        }

        valuesBetween(
                root,
                low,
                high,
                result);

        return result;
    }

    private void valuesBetween(
            Node node,
            int low,
            int high,
            List<Integer> result) {

        if (node == null) {
            return;
        }

        if (low < node.value) {
            valuesBetween(
                    node.left,
                    low,
                    high,
                    result);
        }

        if (node.value >= low
                && node.value <= high) {

            result.add(node.value);
        }

        if (node.value < high) {
            valuesBetween(
                    node.right,
                    low,
                    high,
                    result);
        }
    }

    public int countBetween(
            int low,
            int high) {

        if (low > high) {
            return 0;
        }

        return countBetween(
                root,
                low,
                high);
    }

    private int countBetween(
            Node node,
            int low,
            int high) {

        if (node == null) {
            return 0;
        }

        if (node.value < low) {

            return countBetween(
                    node.right,
                    low,
                    high);
        }

        if (node.value > high) {

            return countBetween(
                    node.left,
                    low,
                    high);
        }

        return 1
                + countBetween(
                        node.left,
                        low,
                        high)
                + countBetween(
                        node.right,
                        low,
                        high);
    }

    public int sumBetween(
            int low,
            int high) {

        if (low > high) {
            return 0;
        }

        return sumBetween(
                root,
                low,
                high);
    }

    private int sumBetween(
            Node node,
            int low,
            int high) {

        if (node == null) {
            return 0;
        }

        if (node.value < low) {

            return sumBetween(
                    node.right,
                    low,
                    high);
        }

        if (node.value > high) {

            return sumBetween(
                    node.left,
                    low,
                    high);
        }

        return node.value
                + sumBetween(
                        node.left,
                        low,
                        high)
                + sumBetween(
                        node.right,
                        low,
                        high);
    }

    public static void main(String[] args) {

        BstRangeStatistics tree =
                new BstRangeStatistics();

        int[] values = {
                50, 30, 70,
                20, 40, 60, 80
        };

        for (int value : values) {
            tree.add(value);
        }

        System.out.println("===== 30 ~ 70 =====");

        System.out.println(
                "Values："
                        + tree.valuesBetween(30, 70));

        System.out.println(
                "Count："
                        + tree.countBetween(30, 70));

        System.out.println(
                "Sum："
                        + tree.sumBetween(30, 70));


        System.out.println("\n===== 空範圍81 ~ 90 =====");

        System.out.println(
                "Values："
                        + tree.valuesBetween(81, 90));

        System.out.println(
                "Count："
                        + tree.countBetween(81, 90));

        System.out.println(
                "Sum："
                        + tree.sumBetween(81, 90));


        System.out.println("\n===== low > high =====");

        System.out.println(
                "Values："
                        + tree.valuesBetween(70, 30));

        System.out.println(
                "Count："
                        + tree.countBetween(70, 30));

        System.out.println(
                "Sum："
                        + tree.sumBetween(70, 30));
    }
}