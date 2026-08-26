import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class TraversalTestReport {

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

        if (node == null) {
            return result;
        }

        result.add(node.value);
        result.addAll(preorder(node.left));
        result.addAll(preorder(node.right));

        return result;
    }

    static List<String> inorder(Node node) {
        List<String> result = new ArrayList<>();

        if (node == null) {
            return result;
        }

        result.addAll(inorder(node.left));
        result.add(node.value);
        result.addAll(inorder(node.right));

        return result;
    }

    static List<String> postorder(Node node) {
        List<String> result = new ArrayList<>();

        if (node == null) {
            return result;
        }

        result.addAll(postorder(node.left));
        result.addAll(postorder(node.right));
        result.add(node.value);

        return result;
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

    static void test(String name,
                     Node root,
                     List<String> expectedPreorder,
                     List<String> expectedInorder,
                     List<String> expectedPostorder,
                     List<String> expectedLevelOrder) {

        List<String> actualPreorder = preorder(root);
        List<String> actualInorder = inorder(root);
        List<String> actualPostorder = postorder(root);
        List<String> actualLevelOrder = levelOrder(root);

        System.out.println("===== " + name + " =====");

        System.out.println("Preorder");
        System.out.println("Expected: " + expectedPreorder);
        System.out.println("Actual:   " + actualPreorder);
        System.out.println("Same:     " + expectedPreorder.equals(actualPreorder));

        System.out.println();

        System.out.println("Inorder");
        System.out.println("Expected: " + expectedInorder);
        System.out.println("Actual:   " + actualInorder);
        System.out.println("Same:     " + expectedInorder.equals(actualInorder));

        System.out.println();

        System.out.println("Postorder");
        System.out.println("Expected: " + expectedPostorder);
        System.out.println("Actual:   " + actualPostorder);
        System.out.println("Same:     " + expectedPostorder.equals(actualPostorder));

        System.out.println();

        System.out.println("Level-order");
        System.out.println("Expected: " + expectedLevelOrder);
        System.out.println("Actual:   " + actualLevelOrder);
        System.out.println("Same:     " + expectedLevelOrder.equals(actualLevelOrder));

        System.out.println();
    }

    public static void main(String[] args) {

        test(
                "empty",
                null,
                new ArrayList<>(),
                new ArrayList<>(),
                new ArrayList<>(),
                new ArrayList<>()
        );

        Node single = new Node("A");

        test(
                "single-node",
                single,
                List.of("A"),
                List.of("A"),
                List.of("A"),
                List.of("A")
        );

        Node onlyLeft = new Node("A");
        onlyLeft.left = new Node("B");
        onlyLeft.left.left = new Node("C");

        test(
                "only-left",
                onlyLeft,
                List.of("A", "B", "C"),
                List.of("C", "B", "A"),
                List.of("C", "B", "A"),
                List.of("A", "B", "C")
        );

        Node onlyRight = new Node("A");
        onlyRight.right = new Node("B");
        onlyRight.right.right = new Node("C");

        test(
                "only-right",
                onlyRight,
                List.of("A", "B", "C"),
                List.of("A", "B", "C"),
                List.of("C", "B", "A"),
                List.of("A", "B", "C")
        );

        Node complete = new Node("A");
        complete.left = new Node("B");
        complete.right = new Node("C");
        complete.left.left = new Node("D");
        complete.left.right = new Node("E");
        complete.right.left = new Node("F");
        complete.right.right = new Node("G");

        test(
                "complete",
                complete,
                List.of("A", "B", "D", "E", "C", "F", "G"),
                List.of("D", "B", "E", "A", "F", "C", "G"),
                List.of("D", "E", "B", "F", "G", "C", "A"),
                List.of("A", "B", "C", "D", "E", "F", "G")
        );

        Node irregular = new Node("A");
        irregular.left = new Node("B");
        irregular.right = new Node("C");
        irregular.left.right = new Node("D");
        irregular.right.left = new Node("E");
        irregular.right.left.right = new Node("F");

        test(
                "irregular",
                irregular,
                List.of("A", "B", "D", "C", "E", "F"),
                List.of("B", "D", "A", "E", "F", "C"),
                List.of("D", "B", "F", "E", "C", "A"),
                List.of("A", "B", "C", "D", "E", "F")
        );
    }
}