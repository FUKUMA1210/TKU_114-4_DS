import java.util.ArrayList;
import java.util.List;

public class OrderManagementBst {

    static class Order {
        int orderId;
        String customer;
        double amount;
        String status;

        Order(int orderId, String customer, double amount, String status) {
            this.orderId = orderId;
            this.customer = customer;
            this.amount = amount;
            this.status = status;
        }

        public String toString() {

            return "訂單編號="
                    + orderId
                    + " 顧客="
                    + customer
                    + " 金額="
                    + amount
                    + " 狀態="
                    + status;
        }
    }

    static class Node {
        Order data;
        Node left;
        Node right;

        Node(Order data) {
            this.data = data;
        }
    }

    static class Bst {
        Node root;

        boolean add(Order order) {

            if (order == null) {
                return false;
            }

            if (order.amount < 0) {
                return false;
            }

            if (order.customer == null
                    || order.customer.trim().isEmpty()) {
                return false;
            }

            if (order.status == null
                    || order.status.trim().isEmpty()) {
                return false;
            }

            if (root == null) {
                root = new Node(order);
                return true;
            }

            Node current = root;

            while (true) {

                if (order.orderId
                        == current.data.orderId) {

                    return false;
                }

                if (order.orderId
                        < current.data.orderId) {

                    if (current.left == null) {

                        current.left =
                                new Node(order);

                        return true;
                    }

                    current = current.left;

                } else {

                    if (current.right == null) {

                        current.right =
                                new Node(order);

                        return true;
                    }

                    current = current.right;
                }
            }
        }

        Order find(int orderId) {

            Node current = root;

            while (current != null) {

                if (orderId
                        == current.data.orderId) {

                    return current.data;
                }

                if (orderId
                        < current.data.orderId) {

                    current = current.left;

                } else {

                    current = current.right;
                }
            }

            return null;
        }

        boolean updateStatus(
                int orderId,
                String status) {

            if (status == null || status.trim().isEmpty()) {
                return false;
            }

            Order order =
                    find(orderId);

            if (order == null) {
                return false;
            }

            order.status = status;

            return true;
        }

        boolean cancel(int orderId) {

            Order order =
                    find(orderId);

            if (order == null) {
                return false;
            }

            order.status = "已取消";

            return true;
        }

        boolean remove(int orderId) {

            Order order =
                    find(orderId);

            if (order == null) {
                return false;
            }

            if (!order.status.equals("已取消")) {
                return false;
            }

            root =
                    remove(
                            root,
                            orderId
                    );

            return true;
        }

        Node remove(
                Node node,
                int orderId) {

            if (node == null) {
                return null;
            }

            if (orderId < node.data.orderId) {
                node.left =
                        remove(
                                node.left,
                                orderId
                        );

            } else if (orderId > node.data.orderId) {
                node.right =
                        remove(
                                node.right,
                                orderId
                        );

            } else {

                if (node.left == null) {
                    return node.right;
                }

                if (node.right == null) {
                    return node.left;
                }

                Node successor =
                        minimum(node.right);

                node.data =
                        successor.data;

                node.right =
                        remove(
                                node.right,
                                successor.data.orderId
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

        List<Order> range(
                int low,
                int high) {

            List<Order> result =
                    new ArrayList<>();

            if (low > high) {
                return result;
            }

            range(
                    root,
                    low,
                    high,
                    result
            );

            return result;
        }

        void range(
                Node node,
                int low,
                int high,
                List<Order> result) {

            if (node == null) {
                return;
            }

            if (low < node.data.orderId) {
                range(
                        node.left,
                        low,
                        high,
                        result
                );
            }

            if (low <= node.data.orderId
                    && node.data.orderId <= high) {

                result.add(node.data);
            }

            if (node.data.orderId < high) {
                range(
                        node.right,
                        low,
                        high,
                        result
                );
            }
        }

        double totalAmount() {

            return totalAmount(root);
        }

        double totalAmount(Node node) {

            if (node == null) {
                return 0;
            }

            return node.data.amount
                    + totalAmount(node.left)
                    + totalAmount(node.right);
        }

        List<Order> inorder() {

            List<Order> result =
                    new ArrayList<>();

            inorder(
                    root,
                    result
            );

            return result;
        }

        void inorder(
                Node node,
                List<Order> result) {

            if (node == null) {
                return;
            }

            inorder(
                    node.left,
                    result
            );

            result.add(node.data);

            inorder(
                    node.right,
                    result
            );
        }
    }

    public static void main(String[] args) {

        Bst orders = new Bst();

        System.out.println(
                "新增訂單："
                + orders.add(
                        new Order(
                                1001,
                                "小明",
                                500,
                                "處理中"
                        )
                )
        );

        System.out.println(
                "新增訂單："
                + orders.add(
                        new Order(
                                1003,
                                "小華",
                                1200,
                                "已付款"
                        )
                )
        );

        System.out.println(
                "新增訂單："
                + orders.add(
                        new Order(
                                1002,
                                "小美",
                                800,
                                "處理中"
                        )
                )
        );

        System.out.println(
                "新增重複訂單："
                + orders.add(
                        new Order(
                                1001,
                                "其他顧客",
                                100,
                                "處理中"
                        )
                )
        );

        System.out.println(
                "新增負數金額訂單："
                + orders.add(
                        new Order(
                                1004,
                                "小華",
                                -100,
                                "處理中"
                        )
                )
        );

        System.out.println(
                "尋找訂單："
                + orders.find(1002)
        );

        System.out.println(
                "修改訂單狀態："
                + orders.updateStatus(
                        1002,
                        "已付款"
                )
        );

        System.out.println(
                "取消訂單："
                + orders.cancel(1001)
        );

        System.out.println(
                "刪除尚未取消的訂單："
                + orders.remove(1003)
        );

        System.out.println(
                "刪除已取消的訂單："
                + orders.remove(1001)
        );

        System.out.println();
        System.out.println("訂單編號範圍查詢：");

        for (Order order :
                orders.range(
                        1002,
                        1003)) {

            System.out.println(order);
        }

        System.out.println(
                "全部訂單總金額="
                        + orders.totalAmount()
        );

        System.out.println();
        System.out.println("依訂單編號排序的報表：");

        for (Order order :
                orders.inorder()) {

            System.out.println(order);
        }

        System.out.println(
                "尋找不存在的訂單："
                + orders.find(9999)
        );
    }
}