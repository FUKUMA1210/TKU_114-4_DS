public class DirectoryTreeReport {

    static class Node {
        String name;
        boolean directory;
        long size;
        Node left;
        Node right;

        Node(String name, boolean directory, long size) {
            this.name = name;
            this.directory = directory;
            this.size = size;
        }
    }

    static class Report {
        int totalNode;
        int fileCount;
        int directoryCount;
        int height;
        Node maxFile;
    }

    static long calculateSize(Node node) {
        if (node == null) {
            return 0;
        }

        if (!node.directory) {
            return node.size;
        }

        long leftSize = calculateSize(node.left);
        long rightSize = calculateSize(node.right);

        node.size = leftSize + rightSize;

        return node.size;
    }

    static int countNodes(Node node) {
        if (node == null) {
            return 0;
        }

        return 1 + countNodes(node.left) + countNodes(node.right);
    }

    static int countFiles(Node node) {
        if (node == null) {
            return 0;
        }

        if (!node.directory) {
            return 1;
        }

        return countFiles(node.left) + countFiles(node.right);
    }

    static int countDirectories(Node node) {
        if (node == null) {
            return 0;
        }

        int count = node.directory ? 1 : 0;

        return count + countDirectories(node.left)
                + countDirectories(node.right);
    }

    static int height(Node node) {
        if (node == null) {
            return -1;
        }

        return 1 + Math.max(
                height(node.left),
                height(node.right)
        );
    }

    static Node findMaxFile(Node node) {
        if (node == null) {
            return null;
        }

        Node best = null;

        if (!node.directory) {
            best = node;
        }

        Node leftBest = findMaxFile(node.left);
        Node rightBest = findMaxFile(node.right);

        if (leftBest != null) {
            if (best == null || leftBest.size > best.size) {
                best = leftBest;
            }
        }

        if (rightBest != null) {
            if (best == null || rightBest.size > best.size) {
                best = rightBest;
            }
        }

        return best;
    }

    static void printDirectories(Node node) {
        if (node == null) {
            return;
        }

        printDirectories(node.left);
        printDirectories(node.right);

        if (node.directory) {
            System.out.println(
                    node.name + " 總容量=" + node.size
            );
        }
    }

    public static void main(String[] args) {

        Node root = new Node("根目錄", true, 0);

        Node documents = new Node("文件資料夾", true, 0);
        Node images = new Node("圖片資料夾", true, 0);

        Node a = new Node("作業一.txt", false, 120);
        Node b = new Node("作業二.txt", false, 300);
        Node c = new Node("照片.jpg", false, 800);
        Node d = new Node("圖片.png", false, 500);

        root.left = documents;
        root.right = images;

        documents.left = a;
        documents.right = b;

        images.left = c;
        images.right = d;

        calculateSize(root);

        Report report = new Report();

        report.totalNode = countNodes(root);
        report.fileCount = countFiles(root);
        report.directoryCount = countDirectories(root);
        report.height = height(root);
        report.maxFile = findMaxFile(root);

        System.out.println("檔案系統統計報表");
        System.out.println("總節點數=" + report.totalNode);
        System.out.println("檔案數=" + report.fileCount);
        System.out.println("目錄數=" + report.directoryCount);
        System.out.println("樹高=" + report.height);

        if (report.maxFile != null) {
            System.out.println(
                    "最大檔案="
                            + report.maxFile.name
                            + " 大小="
                            + report.maxFile.size
            );
        }
        
        System.out.println();
        System.out.println("各目錄總容量：");
        printDirectories(root);
    }
}