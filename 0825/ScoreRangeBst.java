class Score {
    int score;
    String studentId;
    String name;

    Score(int score, String studentId, String name) {
        this.score = score;
        this.studentId = studentId;
        this.name = name;
    }

    public String toString() {
        return score + " " + studentId + " " + name;
    }
}

class ScoreNode {
    Score data;
    ScoreNode left;
    ScoreNode right;

    ScoreNode(Score data) {
        this.data = data;
    }
}

class ScoreBst {
    private ScoreNode root;

    boolean add(Score score) {
        if (score == null) {
            return false;
        }

        if (root == null) {
            root = new ScoreNode(score);
            return true;
        }

        ScoreNode current = root;

        while (true) {
            int result = compare(score, current.data);

            if (result == 0) {
                return false;
            }

            if (result < 0) {
                if (current.left == null) {
                    current.left = new ScoreNode(score);
                    return true;
                }

                current = current.left;
            } else {
                if (current.right == null) {
                    current.right = new ScoreNode(score);
                    return true;
                }

                current = current.right;
            }
        }
    }

    private int compare(Score a, Score b) {
        if (a.score < b.score) {
            return -1;
        }

        if (a.score > b.score) {
            return 1;
        }

        return a.studentId.compareTo(b.studentId);
    }

    void printRange(int low, int high) {
        if (low > high) {
            System.out.println("範圍錯誤");
            return;
        }

        printRange(root, low, high);
        System.out.println();
    }

    private void printRange(ScoreNode node, int low, int high) {
        if (node == null) {
            return;
        }

        if (node.data.score > low) {
            printRange(node.left, low, high);
        }

        if (node.data.score >= low && node.data.score <= high) {
            System.out.print(node.data + " | ");
        }

        if (node.data.score < high) {
            printRange(node.right, low, high);
        }
    }

    void inorder() {
        inorder(root);
        System.out.println();
    }

    private void inorder(ScoreNode node) {
        if (node == null) {
            return;
        }

        inorder(node.left);
        System.out.print(node.data + " | ");
        inorder(node.right);
    }
}

public class ScoreRangeBst {
    public static void main(String[] args) {
        ScoreBst tree = new ScoreBst();

        tree.add(new Score(90, "411630003", "小華"));
        tree.add(new Score(80, "411630001", "小明"));
        tree.add(new Score(90, "411630001", "小美"));
        tree.add(new Score(70, "411630005", "小安"));
        tree.add(new Score(95, "411630002", "小婷"));
        tree.add(new Score(85, "411630004", "小君"));

        System.out.println("全部資料：");
        tree.inorder();

        System.out.println("分數80到90：");
        tree.printRange(80, 90);

        System.out.println("分數70到85：");
        tree.printRange(70, 85);
    }
}