class IntNode {
    int value;
    IntNode left;
    IntNode right;

    IntNode(int value) {
        this.value = value;
    }
}

class IntBst {
    private IntNode root;

    boolean add(int value) {
        if (root == null) {
            root = new IntNode(value);
            return true;
        }

        IntNode current = root;

        while (true) {
            if (value == current.value) {
                return false;
            }

            if (value < current.value) {
                if (current.left == null) {
                    current.left = new IntNode(value);
                    return true;
                }

                current = current.left;
            } else {
                if (current.right == null) {
                    current.right = new IntNode(value);
                    return true;
                }

                current = current.right;
            }
        }
    }

    boolean contains(int value) {
        IntNode current = root;

        while (current != null) {
            if (value == current.value) {
                return true;
            }

            if (value < current.value) {
                current = current.left;
            } else {
                current = current.right;
            }
        }

        return false;
    }

    boolean remove(int value) {
        if (!contains(value)) {
            return false;
        }

        root = remove(root, value);
        return true;
    }

    private IntNode remove(IntNode node, int value) {
        if (node == null) {
            return null;
        }

        if (value < node.value) {
            node.left = remove(node.left, value);
        } else if (value > node.value) {
            node.right = remove(node.right, value);
        } else {
            if (node.left == null) {
                return node.right;
            }

            if (node.right == null) {
                return node.left;
            }

            IntNode successor = minimumNode(node.right);
            node.value = successor.value;
            node.right = remove(node.right, successor.value);
        }

        return node;
    }

    private IntNode minimumNode(IntNode node) {
        while (node.left != null) {
            node = node.left;
        }

        return node;
    }

    void inorder() {
        inorder(root);
        System.out.println();
    }

    private void inorder(IntNode node) {
        if (node == null) {
            return;
        }

        inorder(node.left);
        System.out.print(node.value + " ");
        inorder(node.right);
    }

    int size() {
        return size(root);
    }

    private int size(IntNode node) {
        if (node == null) {
            return 0;
        }

        return 1 + size(node.left) + size(node.right);
    }
}

public class BstDeleteTestSuite {
    public static void main(String[] args) {

        System.out.println("===== 1. Empty Tree =====");
        IntBst tree = new IntBst();
        System.out.println("刪除10：" + tree.remove(10));
        System.out.println("Size：" + tree.size());

        System.out.println();

        System.out.println("===== 2. Missing =====");
        tree.add(50);
        tree.add(30);
        tree.add(70);

        System.out.println("刪除100：" + tree.remove(100));
        tree.inorder();

        System.out.println();

        System.out.println("===== 3. Single Root =====");
        tree = new IntBst();
        tree.add(50);

        System.out.println("刪除50：" + tree.remove(50));
        System.out.println("Size：" + tree.size());
        tree.inorder();

        System.out.println();

        System.out.println("===== 4. Root with One Child =====");
        tree = new IntBst();
        tree.add(50);
        tree.add(30);

        System.out.println("刪除50：" + tree.remove(50));
        System.out.println("Size：" + tree.size());
        tree.inorder();

        System.out.println();

        System.out.println("===== 5. Root with Two Children =====");
        tree = new IntBst();

        tree.add(50);
        tree.add(30);
        tree.add(70);
        tree.add(20);
        tree.add(40);
        tree.add(60);
        tree.add(80);

        System.out.println("刪除50：" + tree.remove(50));
        tree.inorder();

        System.out.println();

        System.out.println("===== 6. Delete Until Empty =====");

        tree = new IntBst();

        tree.add(50);
        tree.add(30);
        tree.add(70);
        tree.add(20);
        tree.add(40);
        tree.add(60);
        tree.add(80);

        int[] values = {20, 40, 30, 60, 80, 70, 50};

        for (int value : values) {
            System.out.println("刪除 " + value + "：" + tree.remove(value));
            System.out.print("Inorder：");
            tree.inorder();
            System.out.println("Size：" + tree.size());
        }

        System.out.println("最後Size：" + tree.size());
    }
}