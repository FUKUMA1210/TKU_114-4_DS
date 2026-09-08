public class BstInvariantChecker {

    static class Node {

        int value;
        Node left;
        Node right;

        Node(int value) {
            this.value = value;
        }
    }

    static boolean isValid(Node root) {

        return isValid(
                root,
                Long.MIN_VALUE,
                Long.MAX_VALUE);
    }

    static boolean isValid(
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

    public static void main(String[] args) {
        
        Node valid = new Node(50);

        valid.left = new Node(30);
        valid.right = new Node(70);

        valid.left.left = new Node(20);
        valid.left.right = new Node(40);

        valid.right.left = new Node(60);
        valid.right.right = new Node(80);

        System.out.println(
                "Valid Tree："
                        + isValid(valid));

        Node invalid1 = new Node(50);

        invalid1.left = new Node(30);
        invalid1.right = new Node(70);

        invalid1.right.left = new Node(40);

        System.out.println(
                "Invalid Tree 1："
                        + isValid(invalid1));

        Node invalid2 = new Node(50);

        invalid2.left = new Node(30);
        invalid2.right = new Node(70);

        invalid2.left.right = new Node(90);

        System.out.println(
                "Invalid Tree 2："
                        + isValid(invalid2));

        Node invalid3 = new Node(50);

        invalid3.right = new Node(70);

        invalid3.right.left = new Node(60);

        invalid3.right.left.left = new Node(45);

        System.out.println(
                "Invalid Tree 3："
                        + isValid(invalid3));
    }
}