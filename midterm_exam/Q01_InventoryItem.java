public class Q01_InventoryItem {

    public static void main(String[] args) {

        Q01_InventoryItem item =
                new Q01_InventoryItem(" P100 ", " Keyboard ", 5);

        System.out.println(item.restock(3));
        System.out.println(item.sell(6));
        System.out.println(item.sell(3));
        System.out.println(item.status());
    }

    private final String id;
    private final String name;
    private int stock;

    public Q01_InventoryItem(String id, String name, int stock) {

        if (id == null || id.trim().equals("")) {
            throw new IllegalArgumentException();
        }

        if (name == null || name.trim().equals("")) {
            throw new IllegalArgumentException();
        }

        this.id = id.trim();
        this.name = name.trim();

        if (stock < 0) {
            this.stock = 0;
        } else {
            this.stock = stock;
        }
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getStock() {
        return stock;
    }

    public boolean restock(int amount) {

        if (amount > 0) {
            stock += amount;
            return true;
        }

        return false;
    }

    public boolean sell(int amount) {

        if (amount > 0 && stock >= amount) {
            stock -= amount;
            return true;
        }

        return false;
    }

    public String status() {

        String AA = id;

        return id + "|" + name + "|" + stock;
    }
}