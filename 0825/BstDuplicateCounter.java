public class BstDuplicateCounter {

    static class Node {

        int key;
        int count;

        Node left;
        Node right;

        Node(int key) {
            this.key = key;
            this.count = 1;
        }
    }

    private Node root;

    public void add(int key) {

        if (root == null) {
            root = new Node(key);
            return;
        }

        Node current = root;

        while (true) {

            if (key == current.key) {

                current.count++;

                return;
            }

            if (key < current.key) {

                if (current.left == null) {

                    current.left = new Node(key);

                    return;
                }

                current = current.left;

            } else {

                if (current.right == null) {

                    current.right = new Node(key);

                    return;
                }

                current = current.right;
            }
        }
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

        System.out.print(
                node.key + "(" + node.count + ") ");

        inorder(node.right);
    }

    public static void main(String[] args) {

        BstDuplicateCounter tree =
                new BstDuplicateCounter();

        int[] values = {
                50, 30, 70, 20, 40,
                30, 50, 50, 70, 20
        };

        for (int value : values) {
            tree.add(value);
        }

        System.out.println("Inorder：");

        tree.inorder();
    }
}