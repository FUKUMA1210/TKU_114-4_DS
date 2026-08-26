public class FolderSizeTree {

    static class FolderNode {
        String name;
        int ownSize;
        FolderNode left;
        FolderNode right;

        FolderNode(String name, int ownSize) {
            this.name = name;
            this.ownSize = ownSize;
        }
    }

    static class FolderResult {
        String name;
        int size;

        FolderResult(String name, int size) {
            this.name = name;
            this.size = size;
        }
    }

    static int subtreeSize(FolderNode node) {
        if (node == null) {
            return 0;
        }

        int leftSize = subtreeSize(node.left);
        int rightSize = subtreeSize(node.right);

        return node.ownSize + leftSize + rightSize;
    }

    static FolderResult maximumSubtree(FolderNode node) {
        if (node == null) {
            return null;
        }

        int currentSize = subtreeSize(node);

        FolderResult best = new FolderResult(node.name, currentSize);

        FolderResult leftResult = maximumSubtree(node.left);
        FolderResult rightResult = maximumSubtree(node.right);

        if (leftResult != null && leftResult.size > best.size) {
            best = leftResult;
        }

        if (rightResult != null && rightResult.size > best.size) {
            best = rightResult;
        }

        return best;
    }

    static void printLeafFolder(FolderNode node) {
        if (node == null) {
            return;
        }

        if (node.left == null && node.right == null) {
            System.out.println(node.name + " = " + node.ownSize);
            return;
        }

        printLeafFolder(node.left);
        printLeafFolder(node.right);
    }

    public static void main(String[] args) {

        FolderNode root = new FolderNode("Root", 100);

        root.left = new FolderNode("Documents", 200);
        root.right = new FolderNode("Pictures", 300);

        root.left.left = new FolderNode("Homework", 150);
        root.left.right = new FolderNode("Report", 100);

        root.right.left = new FolderNode("Photos", 400);
        root.right.right = new FolderNode("Icons", 50);

        int totalSize = subtreeSize(root);

        FolderResult maximum = maximumSubtree(root);

        System.out.println("total size=" + totalSize);
        System.out.println("maximum subtree=" + maximum.name
                + ", size=" + maximum.size);
        System.out.println();
        
        System.out.println("leaf folders:");
        printLeafFolder(root);
    }
}