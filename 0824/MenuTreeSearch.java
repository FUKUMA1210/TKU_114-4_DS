public class MenuTreeSearch {

    static class MenuNode {

        String name;
        MenuNode left;
        MenuNode right;

        MenuNode(String name) {
            this.name = name;
        }
    }

    static boolean contains(
            MenuNode node, String target) {

        if (node == null || target == null) {
            return false;
        }

        if (node.name.equals(target)) {
            return true;
        }

        return contains(node.left, target)
                || contains(node.right, target);
    }

    static int findDepth(
            MenuNode node,
            String target,
            int depth) {

        if (node == null || target == null) {
            return -1;
        }

        if (node.name.equals(target)) {
            return depth;
        }

        int leftResult =
                findDepth(node.left, target, depth + 1);

        if (leftResult != -1) {
            return leftResult;
        }

        return findDepth(
                node.right,
                target,
                depth + 1);
    }

    static int countLeaves(MenuNode node) {

        if (node == null) {
            return 0;
        }

        if (node.left == null
                && node.right == null) {
            return 1;
        }

        return countLeaves(node.left)
                + countLeaves(node.right);
    }

    static void preorder(MenuNode node) {

        if (node == null) {
            return;
        }

        System.out.print(node.name + " ");

        preorder(node.left);
        preorder(node.right);
    }

    public static void main(String[] args) {

        MenuNode root =
                new MenuNode("首頁");

        root.left =
                new MenuNode("商品");

        root.right =
                new MenuNode("會員");

        root.left.left =
                new MenuNode("手機");

        root.left.right =
                new MenuNode("電腦");

        root.right.left =
                new MenuNode("登入");

        root.right.right =
                new MenuNode("註冊");

        System.out.print("Preorder：");
        preorder(root);
        System.out.println();

        System.out.println();

        System.out.println(
                "搜尋商品："
                + contains(root, "商品"));

        System.out.println(
                "搜尋電腦："
                + contains(root, "電腦"));

        System.out.println(
                "搜尋訂單："
                + contains(root, "訂單"));

        System.out.println();

        System.out.println(
                "商品depth："
                + findDepth(root, "商品", 0));

        System.out.println(
                "電腦depth："
                + findDepth(root, "電腦", 0));

        System.out.println(
                "註冊depth："
                + findDepth(root, "註冊", 0));

        System.out.println(
                "訂單depth："
                + findDepth(root, "訂單", 0));

        System.out.println();

        System.out.println(
                "Leaf count："
                + countLeaves(root));
    }
}