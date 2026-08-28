import java.util.ArrayList;
import java.util.List;

public class Q10_BstDirectory {

    public static void main(String[] args) {

        Q10_BstDirectory tree = new Q10_BstDirectory();

        int[] values = {50, 30, 70, 20, 40, 60, 80};

        for (int value : values) {
            tree.add(value);
        }

        System.out.println(tree.add(40));
        System.out.println(tree.searchPath(60));
        System.out.println(tree.searchPath(65));
        System.out.println(tree.inorder());
        System.out.println(tree.isValid());
    }

    private static class Node {

        int value;
        Node left;
        Node right;

        Node(int value) {
            this.value = value;
        }
    }

    private Node root;
    private int treeSize;

    public boolean add(int value) {

        if (root == null) {
            root = new Node(value);
            treeSize++;
            return true;
        }

        Node current = root;

        while (true) {

            if (value < current.value) {

                if (current.left == null) {
                    current.left = new Node(value);
                    treeSize++;
                    return true;
                }

                current = current.left;

            } else if (value > current.value) {

                if (current.right == null) {
                    current.right = new Node(value);
                    treeSize++;
                    return true;
                }

                current = current.right;

            } else {
                return false;
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

    public int size() {
        return treeSize;
    }

    public List<Integer> searchPath(int target) {

        List<Integer> result = new ArrayList<>();

        Node current = root;

        while (current != null) {

            result.add(current.value);

            if (target == current.value) {
                break;
            }

            if (target < current.value) {
                current = current.left;
            } else {
                current = current.right;
            }
        }

        return result;
    }

    public List<Integer> inorder() {

        List<Integer> result = new ArrayList<>();

        inorderRecursive(root, result);

        return result;
    }

    private void inorderRecursive(Node node, List<Integer> result) {

        if (node == null) {
            return;
        }

        inorderRecursive(node.left, result);
        result.add(node.value);
        inorderRecursive(node.right, result);
    }

    public boolean isValid() {
        return isValid(root, null, null);
    }

    private boolean isValid(Node node, Integer low, Integer high) {

        if (node == null) {
            return true;
        }

        if (low != null && node.value <= low) {
            return false;
        }

        if (high != null && node.value >= high) {
            return false;
        }

        return isValid(node.left, low, node.value)
                && isValid(node.right, node.value, high);
    }
}