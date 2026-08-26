public class BinaryTreeStatistics {

    static class Node {
        int value;
        Node left;
        Node right;

        Node(int value) {
            this.value = value;
        }
    }

    static int size(Node node) {
        if (node == null) {
            return 0;
        }

        return 1 + size(node.left) + size(node.right);
    }

    static int sum(Node node) {
        if (node == null) {
            return 0;
        }

        return node.value + sum(node.left) + sum(node.right);
    }

    static int maximum(Node node) {
        if (node == null) {
            return Integer.MIN_VALUE;
        }

        int max = node.value;

        int leftMax = maximum(node.left);
        int rightMax = maximum(node.right);

        if (leftMax > max) {
            max = leftMax;
        }

        if (rightMax > max) {
            max = rightMax;
        }

        return max;
    }

    static int leafCount(Node node) {
        if (node == null) {
            return 0;
        }

        if (node.left == null && node.right == null) {
            return 1;
        }

        return leafCount(node.left) + leafCount(node.right);
    }

    static int height(Node node) {
        if (node == null) {
            return -1;
        }

        return 1 + Math.max(height(node.left), height(node.right));
    }

    static boolean contains(Node node, int target) {
        if (node == null) {
            return false;
        }

        if (node.value == target) {
            return true;
        }

        return contains(node.left, target)
                || contains(node.right, target);
    }

    public static void main(String[] args) {

        Node root = new Node(10);
        root.left = new Node(5);
        root.right = new Node(20);
        root.left.left = new Node(3);
        root.left.right = new Node(7);
        root.right.right = new Node(30);

        System.out.println("size=" + size(root));
        System.out.println("sum=" + sum(root));
        System.out.println("maximum=" + maximum(root));
        System.out.println("leaf count=" + leafCount(root));
        System.out.println("height=" + height(root));
        System.out.println("contains 7=" + contains(root, 7));
        System.out.println("contains 100=" + contains(root, 100));

        Node empty = null;

        System.out.println("empty size=" + size(empty));
        System.out.println("empty sum=" + sum(empty));
        System.out.println("empty maximum=" + maximum(empty));
        System.out.println("empty leaf count=" + leafCount(empty));
        System.out.println("empty height=" + height(empty));
        System.out.println("empty contains=" + contains(empty, 10));
    }
}