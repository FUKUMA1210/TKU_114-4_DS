import java.util.ArrayList;
import java.util.List;

public class BstOperationAudit {

    static class Node {

        int value;
        Node left;
        Node right;

        Node(int value) {
            this.value = value;
        }
    }

    private Node root;

    public boolean add(int value) {

        if (root == null) {
            root = new Node(value);
            return true;
        }

        Node current = root;

        while (true) {

            if (value == current.value) {
                return false;
            }

            if (value < current.value) {

                if (current.left == null) {
                    current.left = new Node(value);
                    return true;
                }

                current = current.left;

            } else {

                if (current.right == null) {
                    current.right = new Node(value);
                    return true;
                }

                current = current.right;
            }
        }
    }

    public boolean contains(int value) {

        Node current = root;

        while (current != null) {

            if (value == current.value) {
                return true;
            }

            if (value < current.value) {
                current = current.left;
            } else {
                current = current.right;
            }
        }

        return false;
    }

    public boolean remove(int value) {

        if (!contains(value)) {
            return false;
        }

        root = remove(root, value);

        return true;
    }

    private Node remove(Node node, int value) {

        if (value < node.value) {

            node.left = remove(node.left, value);

        } else if (value > node.value) {

            node.right = remove(node.right, value);

        } else {
            if (node.left == null) {
                return node.right;
            }

            if (node.right == null) {
                return node.left;
            }

            Node successor = findMin(node.right);

            node.value = successor.value;

            node.right =
                    remove(node.right, successor.value);
        }

        return node;
    }

    private Node findMin(Node node) {

        while (node.left != null) {
            node = node.left;
        }

        return node;
    }

    public List<Integer> inorder() {

        List<Integer> result = new ArrayList<>();

        inorder(root, result);

        return result;
    }

    private void inorder(
            Node node,
            List<Integer> result) {

        if (node == null) {
            return;
        }

        inorder(node.left, result);

        result.add(node.value);

        inorder(node.right, result);
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

    public boolean isValid() {

        return isValid(
                root,
                Long.MIN_VALUE,
                Long.MAX_VALUE);
    }

    private boolean isValid(
            Node node,
            long min,
            long max) {

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

    public void report(
            String operation,
            boolean result) {

        System.out.println("Operation：" + operation);

        System.out.println("Result：" + result);

        System.out.println("Inorder：" + inorder());

        System.out.println("Size：" + size());

        System.out.println("Height：" + height());

        System.out.println("Valid：" + isValid());

        System.out.println("----------------------");
    }

    public static void main(String[] args) {

        BstOperationAudit tree =
                new BstOperationAudit();

        boolean result;

        result = tree.add(50);
        tree.report("add 50", result);

        result = tree.add(30);
        tree.report("add 30", result);

        result = tree.add(70);
        tree.report("add 70", result);

        result = tree.add(20);
        tree.report("add 20", result);

        result = tree.add(40);
        tree.report("add 40", result);

        result = tree.add(60);
        tree.report("add 60", result);

        result = tree.add(80);
        tree.report("add 80", result);

        result = tree.add(50);
        tree.report("add duplicate 50", result);

        result = tree.remove(999);
        tree.report("remove missing 999", result);

        result = tree.remove(20);
        tree.report("remove leaf 20", result);

        tree.add(65);

        result = tree.remove(60);
        tree.report("remove one-child 60", result);

        result = tree.remove(70);
        tree.report("remove two-children 70", result);
    }
}