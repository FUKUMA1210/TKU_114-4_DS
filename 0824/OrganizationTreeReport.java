import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class OrganizationTreeReport {

    static class OrgNode {
        String name;
        OrgNode left;
        OrgNode right;

        OrgNode(String name) {
            this.name = name;
        }
    }

    static OrgNode findParent(OrgNode node, String target) {
        if (node == null || target == null) {
            return null;
        }

        if (node.left != null && node.left.name.equals(target)) {
            return node;
        }

        if (node.right != null && node.right.name.equals(target)) {
            return node;
        }

        OrgNode result = findParent(node.left, target);

        if (result != null) {
            return result;
        }

        return findParent(node.right, target);
    }

    static int findDepth(OrgNode node, String target) {
        if (node == null || target == null) {
            return -1;
        }

        if (node.name.equals(target)) {
            return 0;
        }

        int leftDepth = findDepth(node.left, target);

        if (leftDepth != -1) {
            return leftDepth + 1;
        }

        int rightDepth = findDepth(node.right, target);

        if (rightDepth != -1) {
            return rightDepth + 1;
        }

        return -1;
    }

    static List<String> pathFromRoot(OrgNode node, String target) {
        List<String> path = new ArrayList<>();

        if (findPath(node, target, path)) {
            return path;
        }

        return new ArrayList<>();
    }

    static boolean findPath(OrgNode node, String target, List<String> path) {
        if (node == null || target == null) {
            return false;
        }

        path.add(node.name);

        if (node.name.equals(target)) {
            return true;
        }

        if (findPath(node.left, target, path)) {
            return true;
        }

        if (findPath(node.right, target, path)) {
            return true;
        }

        path.remove(path.size() - 1);

        return false;
    }

    static List<List<String>> printByLevel(OrgNode root) {
        List<List<String>> result = new ArrayList<>();

        if (root == null) {
            return result;
        }

        Queue<OrgNode> queue = new ArrayDeque<>();
        queue.offer(root);

        while (!queue.isEmpty()) {

            int size = queue.size();
            List<String> level = new ArrayList<>();

            for (int i = 0; i < size; i++) {

                OrgNode current = queue.poll();

                level.add(current.name);

                if (current.left != null) {
                    queue.offer(current.left);
                }

                if (current.right != null) {
                    queue.offer(current.right);
                }
            }

            result.add(level);
        }

        return result;
    }

    public static void main(String[] args) {

        OrgNode root = new OrgNode("HeadOffice");

        root.left = new OrgNode("Sales");
        root.right = new OrgNode("Technology");

        root.left.left = new OrgNode("Domestic");
        root.left.right = new OrgNode("Export");

        root.right.left = new OrgNode("Platform");
        root.right.right = new OrgNode("Support");

        System.out.println("Parent of Export = "
                + findParent(root, "Export").name);

        System.out.println("Parent of HR = "
                + findParent(root, "HR"));

        System.out.println("Depth of Platform = "
                + findDepth(root, "Platform"));

        System.out.println("Depth of HR = "
                + findDepth(root, "HR"));

        System.out.println("Path to Support = "
                + pathFromRoot(root, "Support"));

        System.out.println("Path to HR = "
                + pathFromRoot(root, "HR"));
        System.out.println();
        
        System.out.println("By level:");

        List<List<String>> levels = printByLevel(root);

        for (int i = 0; i < levels.size(); i++) {
            System.out.println("Level " + i + ": " + levels.get(i));
        }
    }
}