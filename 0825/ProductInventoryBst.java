class Product {
    int id;
    String name;
    int stock;

    Product(int id, String name, int stock) {
        this.id = id;
        this.name = name;
        this.stock = stock;
    }

    public String toString() {
        return id + " " + name + " 庫存=" + stock;
    }
}

class ProductNode {
    Product data;
    ProductNode left;
    ProductNode right;

    ProductNode(Product data) {
        this.data = data;
    }
}

class ProductBst {
    private ProductNode root;

    boolean add(Product product) {
        if (product == null) {
            return false;
        }

        if (root == null) {
            root = new ProductNode(product);
            return true;
        }

        ProductNode current = root;

        while (true) {
            if (product.id == current.data.id) {
                return false;
            }

            if (product.id < current.data.id) {
                if (current.left == null) {
                    current.left = new ProductNode(product);
                    return true;
                }

                current = current.left;
            } else {
                if (current.right == null) {
                    current.right = new ProductNode(product);
                    return true;
                }

                current = current.right;
            }
        }
    }

    Product find(int id) {
        ProductNode current = root;

        while (current != null) {
            if (id == current.data.id) {
                return current.data;
            }

            if (id < current.data.id) {
                current = current.left;
            } else {
                current = current.right;
            }
        }

        return null;
    }

    boolean restock(int id, int amount) {
        Product product = find(id);

        if (product == null || amount <= 0) {
            return false;
        }

        product.stock = product.stock + amount;
        return true;
    }

    boolean deductStock(int id, int amount) {
        Product product = find(id);

        if (product == null || amount <= 0) {
            return false;
        }

        if (product.stock < amount) {
            return false;
        }

        product.stock = product.stock - amount;
        return true;
    }

    boolean delete(int id) {
        if (find(id) == null) {
            return false;
        }

        root = delete(root, id);
        return true;
    }

    private ProductNode delete(ProductNode node, int id) {
        if (node == null) {
            return null;
        }

        if (id < node.data.id) {
            node.left = delete(node.left, id);
        } else if (id > node.data.id) {
            node.right = delete(node.right, id);
        } else {
            if (node.left == null) {
                return node.right;
            }

            if (node.right == null) {
                return node.left;
            }

            ProductNode successor = minimumNode(node.right);
            node.data = successor.data;
            node.right = delete(node.right, successor.data.id);
        }

        return node;
    }

    private ProductNode minimumNode(ProductNode node) {
        while (node.left != null) {
            node = node.left;
        }

        return node;
    }

    void inorderReport() {
        inorderReport(root);
        System.out.println();
    }

    private void inorderReport(ProductNode node) {
        if (node == null) {
            return;
        }

        inorderReport(node.left);
        System.out.print(node.data + " | ");
        inorderReport(node.right);
    }
}

public class ProductInventoryBst {
    public static void main(String[] args) {
        ProductBst tree = new ProductBst();

        System.out.println("新增商品：");
        System.out.println(tree.add(new Product(300, "鍵盤", 5)));
        System.out.println(tree.add(new Product(100, "滑鼠", 8)));
        System.out.println(tree.add(new Product(500, "螢幕", 2)));
        System.out.println(tree.add(new Product(200, "喇叭", 4)));

        System.out.println("重複商品：");
        System.out.println(tree.add(new Product(100, "重複滑鼠", 10)));

        System.out.println("查詢200：");
        System.out.println(tree.find(200));

        System.out.println("補貨100+5：");
        System.out.println(tree.restock(100, 5));
        System.out.println(tree.find(100));

        System.out.println("扣庫存300-2：");
        System.out.println(tree.deductStock(300, 2));
        System.out.println(tree.find(300));

        System.out.println("扣庫存500-10：");
        System.out.println(tree.deductStock(500, 10));

        System.out.println("刪除200：");
        System.out.println(tree.delete(200));

        System.out.println("Inorder Report：");
        tree.inorderReport();
    }
}