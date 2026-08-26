import java.util.ArrayList;
import java.util.ArrayDeque;
import java.util.List;
import java.util.Queue;

public class TraversalResultCollector {

    static class Node {
        String value;
        Node left;
        Node right;

        Node(String value) {
            this.value = value;
        }
    }

    static List<String> preorder(Node node) {
        List<String> result = new ArrayList<>();
        preorder(node, result);
        return result;
    }

    static void preorder(Node node, List<String> result) {
        if (node == null) {
            return;
        }

        result.add(node.value);
        preorder(node.left, result);
        preorder(node.right, result);
    }

    static List<String> inorder(Node node) {
        List<String> result = new ArrayList<>();
        inorder(node, result);
        return result;
    }

    static void inorder(Node node, List<String> result) {
        if (node == null) {
            return;
        }

        inorder(node.left, result);
        result.add(node.value);
        inorder(node.right, result);
    }

    static List<String> postorder(Node node) {
        List<String> result = new ArrayList<>();
        postorder(node, result);
        return result;
    }

    static void postorder(Node node, List<String> result) {
        if (node == null) {
            return;
        }

        postorder(node.left, result);
        postorder(node.right, result);
        result.add(node.value);
    }

    static List<String> levelOrder(Node root) {
        List<String> result = new ArrayList<>();

        if (root == null) {
            return result;
        }

        Queue<Node> queue = new ArrayDeque<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            Node current = queue.poll();

            result.add(current.value);

            if (current.left != null) {
                queue.offer(current.left);
            }

            if (current.right != null) {
                queue.offer(current.right);
            }
        }

        return result;
    }

    static void show(String name, Node root) {
        System.out.println(name);
        System.out.println("preorder  = " + preorder(root));
        System.out.println("inorder   = " + inorder(root));
        System.out.println("postorder = " + postorder(root));
        System.out.println("levelOrder= " + levelOrder(root));
        System.out.println();
    }

    public static void main(String[] args) {

        show("empty", null);

        Node single = new Node("A");
        show("single-node", single);

        Node left = new Node("A");
        left.left = new Node("B");
        left.left.left = new Node("C");
        show("left-skewed", left);

        Node complete = new Node("A");
        complete.left = new Node("B");
        complete.right = new Node("C");
        complete.left.left = new Node("D");
        complete.left.right = new Node("E");
        complete.right.left = new Node("F");
        complete.right.right = new Node("G");

        show("complete", complete);
    }
}