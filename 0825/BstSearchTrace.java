public class BstSearchTrace {

    static class Node {
        int value;
        Node left;
        Node right;

        Node(int value) {
            this.value = value;
        }
    }

    private Node root;

    public void add(int value) {

        if (root == null) {
            root = new Node(value);
            return;
        }

        Node current = root;

        while (true) {

            if (value == current.value) {
                return;
            }

            if (value < current.value) {

                if (current.left == null) {
                    current.left = new Node(value);
                    return;
                }

                current = current.left;

            } else {

                if (current.right == null) {
                    current.right = new Node(value);
                    return;
                }

                current = current.right;
            }
        }
    }

    public boolean searchTrace(int target) {

        Node current = root;
        int comparisons = 0;

        System.out.println("搜尋：" + target);

        while (current != null) {

            comparisons++;

            System.out.println(
                    "目前比較：" + current.value);

            if (target == current.value) {

                System.out.println("方向：找到");
                System.out.println(
                        "comparison count：" + comparisons);

                return true;
            }

            if (target < current.value) {

                System.out.println("方向：往左");

                current = current.left;

            } else {

                System.out.println("方向：往右");

                current = current.right;
            }
        }

        System.out.println("方向：找不到");
        System.out.println(
                "comparison count：" + comparisons);

        return false;
    }

    public static void main(String[] args) {

        BstSearchTrace tree = new BstSearchTrace();

        int[] values = {
                50, 30, 70, 20, 40, 60, 80
        };

        for (int value : values) {
            tree.add(value);
        }

        tree.searchTrace(50);

        System.out.println();
        tree.searchTrace(20);

        System.out.println();
        tree.searchTrace(70);

        System.out.println();
        tree.searchTrace(65);
    }
}