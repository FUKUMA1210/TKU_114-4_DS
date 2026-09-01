import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class TopSellingProducts {

    public static class Product {
        private String id;
        private int sales;

        public Product(String id, int sales) {
            this.id = id;
            this.sales = sales;
        }

        public String getId() {
            return id;
        }

        public int getSales() {
            return sales;
        }

        public String toString() {
            return id + " 銷量:" + sales;
        }
    }

    public static List<Product> topK(List<Product> products, int k) {

        Map<String, Integer> salesMap = new HashMap<>();

        for (Product product : products) {
            String id = product.getId();
            int sales = product.getSales();

            if (salesMap.containsKey(id)) {
                salesMap.put(id, salesMap.get(id) + sales);
            } else {
                salesMap.put(id, sales);
            }
        }

        PriorityQueue<Product> queue = new PriorityQueue<>(
                Comparator.comparingInt(Product::getSales)
                        .thenComparing(Product::getId, Comparator.reverseOrder())
        );

        for (String id : salesMap.keySet()) {
            Product product = new Product(id, salesMap.get(id));

            queue.offer(product);

            if (queue.size() > k) {
                queue.poll();
            }
        }

        List<Product> result = new ArrayList<>(queue);

        result.sort(
                Comparator.comparingInt(Product::getSales).reversed()
                        .thenComparing(Product::getId)
        );

        return result;
    }

    public static void main(String[] args) {

        List<Product> products = new ArrayList<>();

        products.add(new Product("001", 100));
        products.add(new Product("002", 250));
        products.add(new Product("003", 180));
        products.add(new Product("001", 150));
        products.add(new Product("004", 300));
        products.add(new Product("005", 250));
        products.add(new Product("003", 50));

        List<Product> result = topK(products, 3);

        System.out.println("Top 3熱門商品:");

        for (Product product : result) {
            System.out.println(product);
        }
    }
}