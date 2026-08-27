class ShapeNode {
    int value;
    ShapeNode left;
    ShapeNode right;

    ShapeNode(int value) {
        this.value = value;
    }
}

class ShapeBst {
    private ShapeNode root;

    void add(int value) {
        if (root == null) {
            root = new ShapeNode(value);
            return;
        }

        ShapeNode current = root;

        while (true) {
            if (value < current.value) {
                if (current.left == null) {
                    current.left = new ShapeNode(value);
                    return;
                }

                current = current.left;
            } else {
                if (current.right == null) {
                    current.right = new ShapeNode(value);
                    return;
                }

                current = current.right;
            }
        }
    }

    int height() {
        return height(root);
    }

    private int height(ShapeNode node) {
        if (node == null) {
            return -1;
        }

        int leftHeight = height(node.left);
        int rightHeight = height(node.right);

        if (leftHeight > rightHeight) {
            return leftHeight + 1;
        }

        return rightHeight + 1;
    }

    int searchComparisons(int value) {
        ShapeNode current = root;
        int count = 0;

        while (current != null) {
            count++;

            if (value == current.value) {
                return count;
            }

            if (value < current.value) {
                current = current.left;
            } else {
                current = current.right;
            }
        }

        return count;
    }
}

public class BstShapeExperiment {

    static void test(String name, int[] values) {
        ShapeBst tree = new ShapeBst();

        for (int value : values) {
            tree.add(value);
        }

        int total = 0;

        for (int value = 1; value <= 15; value++) {
            total = total + tree.searchComparisons(value);
        }

        System.out.println(name);
        System.out.println("height：" + tree.height());
        System.out.println("search comparison count：" + total);
        System.out.println();
    }

    public static void main(String[] args) {

        int[] ascending = {
            1, 2, 3, 4, 5,
            6, 7, 8, 9, 10,
            11, 12, 13, 14, 15
        };

        int[] descending = {
            15, 14, 13, 12, 11,
            10, 9, 8, 7, 6,
            5, 4, 3, 2, 1
        };

        int[] balanced = {
            8, 4, 12, 2, 6,
            10, 14, 1, 3, 5,
            7, 9, 11, 13, 15
        };

        System.out.println("===== BST Shape Experiment =====");

        test("由小到大插入", ascending);
        test("由大到小插入", descending);
        test("接近平衡順序插入", balanced);
    }
}