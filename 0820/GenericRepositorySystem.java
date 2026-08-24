import java.util.ArrayList;

class Repository<T> {
    private ArrayList<T> data = new ArrayList<>();

    void add(T value) {
        data.add(value);
    }

    T get(int index) {
        return data.get(index);
    }

    boolean remove(T value) {
        return data.remove(value);
    }

    int size() {
        return data.size();
    }

    void printAll() {
        for (T value : data) {
            System.out.println(value);
        }
    }
}

class Product {
    String id;
    String name;
    int price;

    public Product(String id, String name, int price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public String toString() {
        return id + " " + name + " " + price;
    }
}

public class GenericRepositorySystem {
    public static void main(String[] args) {

        Repository<String> names = new Repository<>();

        names.add("Amy");
        names.add("Ben");
        names.add("Cara");

        System.out.println("String Repository");
        names.printAll();

        System.out.println("第 2 筆：" + names.get(1));
        System.out.println("大小：" + names.size());

        names.remove("Ben");

        System.out.println("刪除後：");
        names.printAll();

        System.out.println();


        Repository<Product> products = new Repository<>();

        products.add(new Product("111", "Keyboard", 800));
        products.add(new Product("112", "Mouse", 500));
        products.add(new Product("113", "Monitor", 3000));

        System.out.println("Product Repository");
        products.printAll();

        System.out.println("第 2 筆：" + products.get(1));
        System.out.println("大小：" + products.size());

        products.remove(products.get(1));

        System.out.println("刪除後：");
        products.printAll();
    }
}