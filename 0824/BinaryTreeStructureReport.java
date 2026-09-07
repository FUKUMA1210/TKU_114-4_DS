public class BinaryTreeStructureReport {

    static class Node {

        String value;
        Node left;
        Node right;

        Node(String value) {
            this.value = value;
        }
    }

    static int size(Node node) {

        if (node == null) {
            return 0;
        }

        return 1 + size(node.left) + size(node.right);
    }

    static int leafCount(Node node) {

        if (node == null) {
            return 0;
        }

        if (node.left == null && node.right == null) {
            return 1;
        }

        return leafCount(node.left)
                + leafCount(node.right);
    }

    static int height(Node node) {

        if (node == null) {
            return -1;
        }

        int leftHeight = height(node.left);
        int rightHeight = height(node.right);

        return 1 + Math.max(leftHeight, rightHeight);
    }

    static void printLeaves(Node node) {

        if (node == null) {
            return;
        }

        if (node.left == null && node.right == null) {
            System.out.print(node.value + " ");
            return;
        }

        printLeaves(node.left);
        printLeaves(node.right);
    }

    static void printReport(Node root) {

        if (root == null) {
            System.out.println("root = null");
            System.out.println("leaf = none");
            System.out.println("size = 0");
            System.out.println("leaf count = 0");
            System.out.println("height = -1");
            return;
        }

        System.out.println("root = " + root.value);

        System.out.print("leaf = ");
        printLeaves(root);
        System.out.println();

        System.out.println("size = " + size(root));
        System.out.println("leaf count = "
                + leafCount(root));
        System.out.println("height = "
                + height(root));
    }

    public static void main(String[] args) {

        Node root = new Node("A");

        root.left = new Node("B");
        root.right = new Node("C");

        root.left.left = new Node("D");
        root.left.right = new Node("E");

        root.right.left = new Node("F");
        root.right.right = new Node("G");

        System.out.println("一般 Binary Tree");
        printReport(root);

        System.out.println();

        System.out.println("Empty Tree");
        printReport(null);

        System.out.println();

        System.out.println("Single Node Tree");

        Node single = new Node("X");

        printReport(single);
    }
}