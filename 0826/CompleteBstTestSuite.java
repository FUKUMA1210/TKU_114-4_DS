import java.util.ArrayList;
import java.util.List;

public class CompleteBstTestSuite {

    static class Node {
        int value;
        Node left;
        Node right;

        Node(int value) {
            this.value = value;
        }
    }

    static class Bst {
        Node root;

        boolean add(int value) {

            if (root == null) {
                root = new Node(value);
                return true;
            }

            Node current = root;

            while (true) {

                if (value == current.value) {
                    return false;
                }

                if (value < current.value) {

                    if (current.left == null) {
                        current.left =
                                new Node(value);
                        return true;
                    }

                    current = current.left;

                } else {

                    if (current.right == null) {
                        current.right =
                                new Node(value);
                        return true;
                    }

                    current = current.right;
                }
            }
        }

        boolean contains(int value) {

            Node current = root;

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

        Node remove(
                Node node,
                int value) {

            if (value < node.value) {

                node.left =
                        remove(node.left, value);

            } else if (value > node.value) {

                node.right =
                        remove(node.right, value);

            } else {

                if (node.left == null) {
                    return node.right;
                }

                if (node.right == null) {
                    return node.left;
                }

                Node successor =
                        minimum(node.right);

                node.value = successor.value;

                node.right =
                        remove(
                                node.right,
                                successor.value
                        );
            }

            return node;
        }

        Node minimum(Node node) {

            while (node.left != null) {
                node = node.left;
            }

            return node;
        }

        int size() {
            return size(root);
        }

        int size(Node node) {

            if (node == null) {
                return 0;
            }

            return 1
                    + size(node.left)
                    + size(node.right);
        }

        int height() {
            return height(root);
        }

        int height(Node node) {

            if (node == null) {
                return -1;
            }

            return 1 + Math.max(
                    height(node.left),
                    height(node.right)
            );
        }

        List<Integer> inorder() {

            List<Integer> result =
                    new ArrayList<>();

            inorder(root, result);

            return result;
        }

        void inorder(
                Node node,
                List<Integer> result) {

            if (node == null) {
                return;
            }

            inorder(node.left, result);

            result.add(node.value);

            inorder(node.right, result);
        }

        List<Integer> range(
                int low,
                int high) {

            List<Integer> result =
                    new ArrayList<>();

            if (low <= high) {
                range(
                        root,
                        low,
                        high,
                        result
                );
            }

            return result;
        }

        void range(
                Node node,
                int low,
                int high,
                List<Integer> result) {

            if (node == null) {
                return;
            }

            if (low < node.value) {
                range(
                        node.left,
                        low,
                        high,
                        result
                );
            }

            if (low <= node.value
                    && node.value <= high) {

                result.add(node.value);
            }

            if (node.value < high) {
                range(
                        node.right,
                        low,
                        high,
                        result
                );
            }
        }

        boolean isValid() {

            return isValid(
                    root,
                    Long.MIN_VALUE,
                    Long.MAX_VALUE
            );
        }

        boolean isValid(
                Node node,
                long low,
                long high) {

            if (node == null) {
                return true;
            }

            if (node.value <= low
                    || node.value >= high) {

                return false;
            }

            return isValid(
                    node.left,
                    low,
                    node.value
            )
                    && isValid(
                    node.right,
                    node.value,
                    high
            );
        }

        void makeInvalid() {

            if (root != null
                    && root.left != null
                    && root.left.right != null) {

                root.left.right.value =
                        root.value + 10;
            }
        }
    }

    static int pass = 0;
    static int fail = 0;

    static void check(
            String description,
            boolean condition) {

        if (condition) {

            System.out.println(
                    "通過：" + description
            );

            pass++;

        } else {

            System.out.println(
                    "失敗：" + description
            );

            fail++;
        }
    }

    public static void main(String[] args) {

        Bst tree = new Bst();

        check(
                "空樹搜尋不存在的資料",
                !tree.contains(10)
        );

        check(
                "空樹節點數為 0",
                tree.size() == 0
        );

        check(
                "空樹高度為 -1",
                tree.height() == -1
        );

        check(
                "空樹符合BST規則",
                tree.isValid()
        );

        check(
                "新增根節點",
                tree.add(50)
        );

        check(
                "根節點存在",
                tree.contains(50)
        );

        check(
                "目前節點數為1",
                tree.size() == 1
        );

        check(
                "只有根節點時高度為0",
                tree.height() == 0
        );

        check(
                "重複資料",
                !tree.add(50)
        );

        tree.add(30);
        tree.add(70);
        tree.add(20);
        tree.add(40);
        tree.add(60);
        tree.add(80);

        check(
                "新增資料後節點數正確",
                tree.size() == 7
        );

        check(
                "搜尋20成功",
                tree.contains(20)
        );

        check(
                "搜尋40成功",
                tree.contains(40)
        );

        check(
                "搜尋80成功",
                tree.contains(80)
        );

        check(
                "搜尋不存在的999",
                !tree.contains(999)
        );

        check(
                "排序結果結果為中序走訪",
                tree.inorder().toString().equals(
                        "[20, 30, 40, 50, 60, 70, 80]"
                )
        );

        check(
                "樹高為2",
                tree.height() == 2
        );

        check(
                "範圍30到70查詢正確",
                tree.range(30, 70).toString().equals(
                        "[30, 40, 50, 60, 70]"
                )
        );

        check(
                "查詢不存在的範圍",
                tree.range(100, 200).isEmpty()
        );

        check(
                "下限大於上限時回傳空集合",
                tree.range(70, 30).isEmpty()
        );

        check(
                "目前BST規則正確",
                tree.isValid()
        );

        check(
                "刪除葉節點20",
                tree.remove(20)
        );

        check(
                "葉節點20已刪除",
                !tree.contains(20)
        );

        tree.add(10);

        check(
                "單一子節點10存在",
                tree.contains(10)
        );

        check(
                "刪除有一個子節點的節點30",
                tree.remove(30)
        );

        check(
                "子節點10仍然存在",
                tree.contains(10)
        );

        check(
                "刪除有兩個子節點的節點70",
                tree.remove(70)
        );

        check(
                "節點70已刪除",
                !tree.contains(70)
        );

        check(
                "刪除後仍符合BST規則",
                tree.isValid()
        );

        check(
                "刪除不存在的節點失敗",
                !tree.remove(999)
        );

        Bst broken = new Bst();

        broken.add(50);
        broken.add(30);
        broken.add(70);
        broken.add(20);
        broken.add(40);

        check(
                "新的樹符合BST規則",
                broken.isValid()
        );

        broken.makeInvalid();

        check(
                "可以偵測深層BST規則錯誤",
                !broken.isValid()
        );

        System.out.println();
        System.out.println("測試完成");
        System.out.println("通過數量=" + pass);
        System.out.println("失敗數量=" + fail);
    }
}