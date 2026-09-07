import java.util.ArrayDeque;
import java.util.Queue;

public class LevelOrderByLine {

    static class Node {

        String value;
        Node left;
        Node right;

        Node(String value) {
            this.value = value;
        }
    }

    static void levelOrderByLine(Node root) {

        if (root == null) {
            System.out.println("empty tree");
            return;
        }

        Queue<Node> queue = new ArrayDeque<>();

        queue.offer(root);

        int level = 0;

        while (!queue.isEmpty()) {

            int levelSize = queue.size();

            System.out.print(
                    "Level " + level
                    + " (" + levelSize + " nodes)：");

            for (int i = 0; i < levelSize; i++) {

                Node node = queue.poll();

                System.out.print(
                        node.value + " ");

                if (node.left != null) {
                    queue.offer(node.left);
                }

                if (node.right != null) {
                    queue.offer(node.right);
                }
            }

            System.out.println();

            level++;
        }
    }

    public static void main(String[] args) {

        Node root = new Node("A");

        root.left = new Node("B");
        root.right = new Node("C");

        root.left.left = new Node("D");
        root.left.right = new Node("E");

        root.right.left = new Node("F");
        root.right.right = new Node("G");

        System.out.println("一般 Tree：");

        levelOrderByLine(root);

        System.out.println();

        System.out.println("Empty Tree：");

        levelOrderByLine(null);
    }
}