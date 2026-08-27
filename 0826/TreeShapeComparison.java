public class TreeShapeComparison {

    static class Node {
        int value;
        Node left;
        Node right;

        Node(int value) {
            this.value = value;
        }
    }

    static class Bst {
        Node root;

        void add(int value) {

            if (root == null) {
                root = new Node(value);
                return;
            }

            Node current = root;

            while (true) {

                if (value < current.value) {

                    if (current.left == null) {
                        current.left =
                                new Node(value);
                        return;
                    }

                    current = current.left;

                } else {

                    if (current.right == null) {
                        current.right =
                                new Node(value);
                        return;
                    }

                    current = current.right;
                }
            }
        }

        int height() {
            return height(root);
        }

        int height(Node node) {

            if (node == null) {
                return -1;
            }

            return 1 + Math.max(
                    height(node.left),
                    height(node.right)
            );
        }

        int searchComparison(int target) {

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
    }

    static Bst build(int[] data) {

        Bst tree = new Bst();

        for (int value : data) {
            tree.add(value);
        }

        return tree;
    }

    static int totalSearchComparison(
            Bst tree,
            int[] keys) {

        int total = 0;

        for (int key : keys) {
            total +=
                    tree.searchComparison(key);
        }

        return total;
    }

    static void printResult(
            String name,
            Bst tree,
            int[] keys,
            int missingKey) {

        int total =
                totalSearchComparison(
                        tree,
                        keys
                );

        int missing =
                tree.searchComparison(
                        missingKey
                );

        System.out.println(
                "建立方式：" + name
        );

        System.out.println(
                "樹高=" + tree.height()
        );

        System.out.println(
                "全部資料比較次數="
                        + total
        );

        System.out.println(
                "不存在資料比較次數="
                        + missing
        );

        System.out.println();
    }

    public static void main(String[] args) {

        int[] ascending = {
                10, 20, 30, 40, 50,
                60, 70, 80, 90, 100,
                110, 120, 130, 140, 150
        };

        int[] descending = {
                150, 140, 130, 120, 110,
                100, 90, 80, 70, 60,
                50, 40, 30, 20, 10
        };

        int[] balanced = {
                80,
                40, 120,
                20, 60, 100, 140,
                10, 30, 50, 70,
                90, 110, 130, 150
        };

        Bst ascendingTree =
                build(ascending);

        Bst descendingTree =
                build(descending);

        Bst balancedTree =
                build(balanced);

        System.out.println("===== BST 樹形比較 =====");
        System.out.println();

        printResult(
                "升冪",
                ascendingTree,
                ascending,
                155
        );

        printResult(
                "降冪",
                descendingTree,
                ascending,
                155
        );

        printResult(
                "接近平衡",
                balancedTree,
                ascending,
                155
        );
    }
}