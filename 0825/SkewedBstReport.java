public class SkewedBstReport {

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

    public int size() {
        return size(root);
    }

    private int size(Node node) {

        if (node == null) {
            return 0;
        }

        return 1
                + size(node.left)
                + size(node.right);
    }

    public int height() {
        return height(root);
    }

    private int height(Node node) {

        if (node == null) {
            return -1;
        }

        return 1 + Math.max(
                height(node.left),
                height(node.right));
    }

    public int searchComparisonCount(int target) {

        Node current = root;

        int count = 0;

        while (current != null) {

            count++;

            if (target == current.value) {
                return count;
            }

            if (target < current.value) {
                current = current.left;
            } else {
                current = current.right;
            }
        }

        return count;
    }

    public static void main(String[] args) {

        SkewedBstReport sortedTree =
                new SkewedBstReport();

        int[] sortedValues = {
                10, 20, 30, 40, 50, 60, 70
        };

        for (int value : sortedValues) {
            sortedTree.add(value);
        }

        SkewedBstReport balancedTree =
                new SkewedBstReport();

        int[] balancedValues = {
                40, 20, 60, 10, 30, 50, 70
        };

        for (int value : balancedValues) {
            balancedTree.add(value);
        }

        int target = 70;

        System.out.println("排序資料建立的BST");

        System.out.println(
                "Size：" + sortedTree.size());

        System.out.println(
                "Height：" + sortedTree.height());

        System.out.println(
                "搜尋" + target + "比較次數："
                        + sortedTree.searchComparisonCount(target));

        System.out.println();

        System.out.println("平衡順序建立的BST");

        System.out.println(
                "Size：" + balancedTree.size());

        System.out.println(
                "Height：" + balancedTree.height());

        System.out.println(
                "搜尋" + target + "比較次數："
                        + balancedTree.searchComparisonCount(target));
    }
}