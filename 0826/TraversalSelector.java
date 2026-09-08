public class TraversalSelector {

    static class Node {

        String value;
        Node left;
        Node right;

        Node(String value) {
            this.value = value;
        }
    }

    public static String preorder(Node node) {

        if (node == null) {
            return "";
        }

        String result = node.value;

        if (node.left != null) {
            result += " " + preorder(node.left);
        }

        if (node.right != null) {
            result += " " + preorder(node.right);
        }

        return result;
    }

    public static String inorder(Node node) {

        if (node == null) {
            return "";
        }

        // Leaf node
        if (node.left == null && node.right == null) {
            return node.value;
        }

        return "("
                + inorder(node.left)
                + " " + node.value + " "
                + inorder(node.right)
                + ")";
    }

    public static String postorder(Node node) {

        if (node == null) {
            return "";
        }

        String result = "";

        if (node.left != null) {
            result += postorder(node.left) + " ";
        }

        if (node.right != null) {
            result += postorder(node.right) + " ";
        }

        result += node.value;

        return result;
    }

    public static void main(String[] args) {

        Node root = new Node("*");

        root.left = new Node("+");
        root.right = new Node("-");

        root.left.left = new Node("A");
        root.left.right = new Node("B");

        root.right.left = new Node("C");
        root.right.right = new Node("D");

        System.out.println("Prefix：");
        System.out.println(preorder(root));

        System.out.println();

        System.out.println("Infix：");
        System.out.println(inorder(root));

        System.out.println();

        System.out.println("Postfix：");
        System.out.println(postorder(root));
    }
}