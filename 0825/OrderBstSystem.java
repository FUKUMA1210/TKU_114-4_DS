class Order {
    int orderId;
    String customer;
    int amount;
    boolean canceled;

    Order(int orderId, String customer, int amount) {
        this.orderId = orderId;
        this.customer = customer;
        this.amount = amount;
        this.canceled = false;
    }

    public String toString() {
        String status;

        if (canceled) {
            status = "已取消";
        } else {
            status = "正常";
        }

        return orderId + " " + customer + " 金額：" + amount + " 狀態：" + status;
    }
}

class OrderNode {
    Order data;
    OrderNode left;
    OrderNode right;

    OrderNode(Order data) {
        this.data = data;
    }
}

class OrderBst {
    private OrderNode root;

    boolean add(Order order) {
        if (order == null) {
            return false;
        }

        if (root == null) {
            root = new OrderNode(order);
            return true;
        }

        OrderNode current = root;

        while (true) {
            if (order.orderId == current.data.orderId) {
                return false;
            }

            if (order.orderId < current.data.orderId) {
                if (current.left == null) {
                    current.left = new OrderNode(order);
                    return true;
                }

                current = current.left;
            } else {
                if (current.right == null) {
                    current.right = new OrderNode(order);
                    return true;
                }

                current = current.right;
            }
        }
    }

    Order find(int orderId) {
        OrderNode current = root;

        while (current != null) {
            if (orderId == current.data.orderId) {
                return current.data;
            }

            if (orderId < current.data.orderId) {
                current = current.left;
            } else {
                current = current.right;
            }
        }

        return null;
    }

    boolean cancel(int orderId) {
        Order order = find(orderId);

        if (order == null) {
            return false;
        }

        if (order.canceled) {
            return false;
        }

        order.canceled = true;
        return true;
    }

    boolean updateAmount(int orderId, int amount) {
        Order order = find(orderId);

        if (order == null) {
            return false;
        }

        if (order.canceled) {
            return false;
        }

        if (amount < 0) {
            return false;
        }

        order.amount = amount;
        return true;
    }

    void rangeReport(int low, int high) {
        if (low > high) {
            System.out.println("範圍錯誤");
            return;
        }

        rangeReport(root, low, high);
        System.out.println();
    }

    private void rangeReport(OrderNode node, int low, int high) {
        if (node == null) {
            return;
        }

        if (node.data.orderId > low) {
            rangeReport(node.left, low, high);
        }

        if (node.data.orderId >= low &&
            node.data.orderId <= high) {

            System.out.print(node.data + " | ");
        }

        if (node.data.orderId < high) {
            rangeReport(node.right, low, high);
        }
    }

    void summary() {
        int totalCount = count(root);
        int activeCount = activeCount(root);
        int totalAmount = totalAmount(root);
        int activeAmount = activeAmount(root);

        System.out.println("全部訂單：" + totalCount);
        System.out.println("有效訂單：" + activeCount);
        System.out.println("全部訂單金額：" + totalAmount);
        System.out.println("有效訂單金額：" + activeAmount);
    }

    private int count(OrderNode node) {
        if (node == null) {
            return 0;
        }

        return 1 + count(node.left) + count(node.right);
    }

    private int activeCount(OrderNode node) {
        if (node == null) {
            return 0;
        }

        int count = 0;

        if (!node.data.canceled) {
            count = 1;
        }

        return count + activeCount(node.left) + activeCount(node.right);
    }

    private int totalAmount(OrderNode node) {
        if (node == null) {
            return 0;
        }

        return node.data.amount
                + totalAmount(node.left)
                + totalAmount(node.right);
    }

    private int activeAmount(OrderNode node) {
        if (node == null) {
            return 0;
        }

        int amount = 0;

        if (!node.data.canceled) {
            amount = node.data.amount;
        }

        return amount
                + activeAmount(node.left)
                + activeAmount(node.right);
    }

    void inorder() {
        inorder(root);
        System.out.println();
    }

    private void inorder(OrderNode node) {
        if (node == null) {
            return;
        }

        inorder(node.left);
        System.out.print(node.data + " | ");
        inorder(node.right);
    }
}

public class OrderBstSystem {
    public static void main(String[] args) {

        OrderBst tree = new OrderBst();

        System.out.println("===== 新增訂單 =====");

        System.out.println(
            tree.add(new Order(3001, "小明", 500)
        ));

        System.out.println(
            tree.add(new Order(1001, "小華", 800)
        ));

        System.out.println(
            tree.add(new Order(5001, "小美", 1200)
        ));

        System.out.println(
            tree.add(new Order(2001, "小安", 600)
        ));

        System.out.println();

        System.out.println("===== 重複訂單 =====");

        System.out.println(
            tree.add(new Order(1001, "重複訂單", 999)
        ));

        System.out.println();

        System.out.println("===== 查詢 =====");

        System.out.println(tree.find(2001));
        System.out.println(tree.find(9999));

        System.out.println();

        System.out.println("===== 修改金額 =====");

        System.out.println(tree.updateAmount(2001, 900));
        System.out.println(tree.find(2001));

        System.out.println();

        System.out.println("===== 取消訂單 =====");

        System.out.println(tree.cancel(3001));
        System.out.println(tree.find(3001));

        System.out.println();

        System.out.println("===== Range Report 1001 ~ 5001 =====");

        tree.rangeReport(1001, 5001);

        System.out.println();

        System.out.println("===== Inorder =====");

        tree.inorder();

        System.out.println();

        System.out.println("===== Summary =====");

        tree.summary();
    }
}