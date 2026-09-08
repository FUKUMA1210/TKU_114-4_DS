public class BstRangeReport {

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

    public Integer min() {

        if (root == null) {
            return null;
        }

        Node current = root;

        while (current.left != null) {
            current = current.left;
        }

        return current.value;
    }

    public Integer max() {

        if (root == null) {
            return null;
        }

        Node current = root;

        while (current.right != null) {
            current = current.right;
        }

        return current.value;
    }

    public void printRange(int low, int high) {

        if (low > high) {
            System.out.println("low>high，沒有資料");
            return;
        }

        printRange(root, low, high);
        System.out.println();
    }

    private void printRange(
            Node node, int low, int high) {

        if (node == null) {
            return;
        }

        if (node.value > low) {
            printRange(node.left, low, high);
        }

        if (node.value >= low
                && node.value <= high) {

            System.out.print(node.value + " ");
        }

        if (node.value < high) {
            printRange(node.right, low, high);
        }
    }

    public static void main(String[] args) {

        BstRangeReport tree =
                new BstRangeReport();

        int[] values = {
                50, 30, 70, 20, 40, 60, 80
        };

        for (int value : values) {
            tree.add(value);
        }

        System.out.println("Min：" + tree.min());

        System.out.println("Max：" + tree.max());

        System.out.print("20~60：");
        tree.printRange(20, 60);

        System.out.print("35~75：");
        tree.printRange(35, 75);

        System.out.print("70~30：");
        tree.printRange(70, 30);
    }
}