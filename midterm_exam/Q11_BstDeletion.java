import java.util.ArrayList;
import java.util.List;

public class Q11_BstDeletion {

    public static void main(String[] args) {

        Q11_BstDeletion tree = new Q11_BstDeletion();

        int[] values = {50, 30, 70, 20, 40, 60, 80};

        for (int value : values) {
            tree.add(value);
        }

        System.out.println(tree.remove(20));
        System.out.println(tree.remove(30));
        System.out.println(tree.remove(50));
        System.out.println(tree.remove(999));
        System.out.println(tree.inorder());
        System.out.println(tree.size());
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

    public boolean remove(int value) {

        if (!contains(value)) {
            return false;
        }

        root = removeNode(root, value);
        treeSize--;

        return true;
    }

    private Node removeNode(Node node, int value) {

        if (node == null) {
            return null;
        }

        if (value < node.value) {
            node.left = removeNode(node.left, value);

        } else if (value > node.value) {
            node.right = removeNode(node.right, value);

        } else {

            if (node.left == null && node.right == null) {
                return null;
            }

            if (node.left == null) {
                return node.right;
            }

            if (node.right == null) {
                return node.left;
            }

            Node successorAuditN11 = findMinimum(node.right);

            node.value = successorAuditN11.value;

            node.right = removeNode(node.right, successorAuditN11.value);
        }

        return node;
    }

    private Node findMinimum(Node node) {

        Node current = node;

        while (current.left != null) {
            current = current.left;
        }

        return current;
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