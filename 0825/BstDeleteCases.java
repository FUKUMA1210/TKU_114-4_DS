public class BstDeleteCases {

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
        root = add(root, value);
    }

    private Node add(Node node, int value) {

        if (node == null) {
            return new Node(value);
        }

        if (value < node.value) {
            node.left = add(node.left, value);
        } else if (value > node.value) {
            node.right = add(node.right, value);
        }

        return node;
    }

    public void delete(int value) {
        root = delete(root, value);
    }

    private Node delete(Node node, int value) {

        if (node == null) {
            return null;
        }

        if (value < node.value) {

            node.left = delete(node.left, value);

        } else if (value > node.value) {

            node.right = delete(node.right, value);

        } else {

            if (node.left == null
                    && node.right == null) {

                return null;
            }

            if (node.left == null) {
                return node.right;
            }

            if (node.right == null) {
                return node.left;
            }

            Node successor = findMin(node.right);

            node.value = successor.value;

            node.right =
                    delete(node.right, successor.value);
        }

        return node;
    }

    private Node findMin(Node node) {

        while (node.left != null) {
            node = node.left;
        }

        return node;
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

    public boolean isValid() {
        return isValid(root,
                Long.MIN_VALUE,
                Long.MAX_VALUE);
    }

    private boolean isValid(
            Node node, long min, long max) {

        if (node == null) {
            return true;
        }

        if (node.value <= min
                || node.value >= max) {

            return false;
        }

        return isValid(
                node.left,
                min,
                node.value)

                &&

                isValid(
                        node.right,
                        node.value,
                        max);
    }

    public void inorder() {
        inorder(root);
        System.out.println();
    }

    private void inorder(Node node) {

        if (node == null) {
            return;
        }

        inorder(node.left);

        System.out.print(node.value + " ");

        inorder(node.right);
    }

    public void report(String title) {

        System.out.println(title);

        System.out.print("Inorder：");
        inorder();

        System.out.println("Size：" + size());

        System.out.println(
                "Valid：" + isValid());

        System.out.println();
    }

    public static void main(String[] args) {

        BstDeleteCases tree =
                new BstDeleteCases();

        int[] values = {
                50, 30, 70, 20,
                40, 60, 80, 65
        };

        for (int value : values) {
            tree.add(value);
        }

        tree.report("原始Tree");

        tree.delete(20);
        tree.report("刪除Leaf：20");

        tree.delete(60);
        tree.report("刪除Single-child：60");

        tree.delete(70);
        tree.report("刪除Two-child：70");
    }
}