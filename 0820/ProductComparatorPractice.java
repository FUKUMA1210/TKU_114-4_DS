import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ProductComparatorPractice {

    static class StoreProduct implements Comparable<StoreProduct> {

        private int id;
        private String name;
        private double price;
        private int stock;

        public StoreProduct(int id, String name,
                            double price, int stock) {
            this.id = id;
            this.name = name;
            this.price = price;
            this.stock = stock;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public double getPrice() {
            return price;
        }

        public int getStock() {
            return stock;
        }

        @Override
        public int compareTo(StoreProduct other) {
            return Integer.compare(this.id, other.id);
        }

        @Override
        public String toString() {
            return "ID：" + id
                    + ", 名稱：" + name
                    + ", 價格：" + price
                    + ", 庫存：" + stock;
        }
    }

    public static void main(String[] args) {

        List<StoreProduct> products = new ArrayList<>();

        products.add(new StoreProduct(103, "滑鼠", 500, 20));
        products.add(new StoreProduct(101, "鍵盤", 800, 15));
        products.add(new StoreProduct(105, "耳機", 500, 30));
        products.add(new StoreProduct(102, "螢幕", 3000, 20));
        products.add(new StoreProduct(104, "滑鼠墊", 500, 15));

        System.out.println("原始順序：");

        for (StoreProduct product : products) {
            System.out.println(product);
        }

        System.out.println();

        // Natural order：依 id 升冪
        List<StoreProduct> idList =
                new ArrayList<>(products);

        Collections.sort(idList);

        System.out.println("依ID升冪：");

        for (StoreProduct product : idList) {
            System.out.println(product);
        }

        System.out.println();

        // Comparator 一：價格升冪，同價依名稱
        List<StoreProduct> priceList =
                new ArrayList<>(products);

        Collections.sort(priceList,
                new Comparator<StoreProduct>() {
                    @Override
                    public int compare(StoreProduct p1,
                                       StoreProduct p2) {

                        int result =
                                Double.compare(
                                        p1.getPrice(),
                                        p2.getPrice());

                        if (result != 0) {
                            return result;
                        }

                        return p1.getName()
                                .compareTo(p2.getName());
                    }
                });

        System.out.println("依價格升冪，同價依名稱：");

        for (StoreProduct product : priceList) {
            System.out.println(product);
        }

        System.out.println();

        // Comparator 二：庫存降冪，同庫存依 ID
        List<StoreProduct> stockList =
                new ArrayList<>(products);

        Collections.sort(stockList,
                new Comparator<StoreProduct>() {
                    @Override
                    public int compare(StoreProduct p1,
                                       StoreProduct p2) {

                        int result =
                                Integer.compare(
                                        p2.getStock(),
                                        p1.getStock());

                        if (result != 0) {
                            return result;
                        }

                        return Integer.compare(
                                p1.getId(),
                                p2.getId());
                    }
                });

        System.out.println("依庫存降冪，同庫存依ID：");

        for (StoreProduct product : stockList) {
            System.out.println(product);
        }
    }
}