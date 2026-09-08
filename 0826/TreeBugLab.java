public class TreeBugLab {

    static class Node {

        int value;
        Node left;
        Node right;

        Node(int value) {
            this.value = value;
        }
    }

    public static boolean wrongSearch(
            Node node,
            int target) {

        while (node != null) {

            if (node.value == target) {
                return true;
            }


            if (target < node.value) {
                node = node.right;
            } else {
                node = node.left;
            }
        }

        return false;
    }

    public static boolean correctSearch(
            Node node,
            int target) {

        while (node != null) {

            if (node.value == target) {
                return true;
            }

            if (target < node.value) {
                node = node.left;
            } else {
                node = node.right;
            }
        }

        return false;
    }

    public static void wrongInorder(Node node) {

        if (node == null) {
            return;
        }

        System.out.print(node.value + " ");

        wrongInorder(node.left);

        wrongInorder(node.right);
    }

    public static void correctInorder(Node node) {

        if (node == null) {
            return;
        }

        correctInorder(node.left);

        System.out.print(node.value + " ");

        correctInorder(node.right);
    }

    public static Node wrongDelete(Node node) {

        if (node == null) {
            return null;
        }

        return null;
    }

    public static Node correctDelete(Node node) {

        if (node == null) {
            return null;
        }

        if (node.left == null) {
            return node.right;
        }

        if (node.right == null) {
            return node.left;
        }

        return node;
    }

    public static boolean wrongValidation(Node node) {

        if (node == null) {
            return true;
        }

        if (node.left != null
                && node.left.value >= node.value) {

            return false;
        }

        if (node.right != null
                && node.right.value <= node.value) {

            return false;
        }

        return wrongValidation(node.left)
                && wrongValidation(node.right);
    }

    public static boolean correctValidation(Node node) {

        return correctValidation(
                node,
                Long.MIN_VALUE,
                Long.MAX_VALUE);
    }

    private static boolean correctValidation(
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

        return correctValidation(
                node.left,
                min,
                node.value)
                &&
                correctValidation(
                        node.right,
                        node.value,
                        max);
    }

    public static void main(String[] args) {

        Node root = new Node(50);

        root.left = new Node(30);
        root.right = new Node(70);

        root.left.left = new Node(20);
        root.left.right = new Node(40);

        root.right.left = new Node(60);
        root.right.right = new Node(80);


        System.out.println("===== 1. Search 方向相反 =====");

        System.out.println(
                "Wrong search 30："
                        + wrongSearch(root, 30));

        System.out.println(
                "Correct search 30："
                        + correctSearch(root, 30));


        System.out.println("\n===== 2. Inorder 順序錯誤 =====");

        System.out.print("Wrong：");
        wrongInorder(root);

        System.out.println();

        System.out.print("Correct：");
        correctInorder(root);

        System.out.println();


        System.out.println("\n===== 3. Delete遺失child =====");

        Node parent = new Node(50);
        parent.right = new Node(70);

        Node wrongResult =
                wrongDelete(parent);

        System.out.println(
                "Wrong delete result："
                        + wrongResult);

        Node parent2 = new Node(50);
        parent2.right = new Node(70);

        Node correctResult =
                correctDelete(parent2);

        System.out.println(
                "Correct delete result："
                        + correctResult.value);


        System.out.println("\n===== 4. Validation =====");

        Node invalidRoot = new Node(50);

        invalidRoot.left = new Node(30);
        invalidRoot.right = new Node(70);

        invalidRoot.left.right = new Node(55);

        System.out.println(
                "Wrong validation："
                        + wrongValidation(invalidRoot));

        System.out.println(
                "Correct validation："
                        + correctValidation(invalidRoot));
    }
}