class Customer {
    private String name;

    public Customer(String name) {
        this.name = name;
    }

    String getName() {
        return name;
    }
}

class OrderItem {
    private String item;
    private int price;
    private int amount;
    
    public OrderItem(String item, int price, int amount) {
        this.item = item;
        this.price = price;
        this.amount = amount;
    }

    int getTotal() {
        return price * amount;
    }

    String getItemName() {
        return item;
    }

    int getAmount() {
        return amount;
    }

    void showItem() {
        System.out.println(item + " x " + amount + " = $" + getTotal());
    }
}

class CustomerOrder {
    private Customer customer;
    private OrderItem[] items;
    private int itemCount;

    public CustomerOrder(Customer customer, int maxItems) {
        this.customer = customer;
        this.items = new OrderItem[maxItems];
        this.itemCount = 0;
    }

    void addItem(OrderItem item) {
        if (itemCount < items.length) {
            items[itemCount] = item;
            itemCount++;
        } else {
            System.out.println("訂單品項已達上限");
        }
    }

    double getTotalAmount() {
        double total = 0;

        for (int i = 0; i < itemCount; i++) {
            total += items[i].getTotal();
        }
        return total;
    }

    int getItemCount() {
        return itemCount;
    }

    void showSummary() {
        System.out.println("===== 訂單摘要 =====");
        System.out.println("顧客姓名：" + customer.getName());
        System.out.println("訂單品項 ：");

        for (int i = 0; i < itemCount; i++) {
            items[i].showItem();
        }

        System.out.println("--------------------");
        System.out.println("品項數量：" + itemCount);
        System.out.println("訂單總額：$" + getTotalAmount());
    }
}

public class CustomerOrderSystem {
    public static void main(String[] args) {
        Customer customer = new Customer("Eric");
        CustomerOrder order = new CustomerOrder(customer, 5);

        OrderItem item1 = new OrderItem("課本", 500, 1);
        OrderItem item2 = new OrderItem("筆記本", 80, 2);
        OrderItem item3 = new OrderItem("原子筆", 30, 3);

        order.addItem(item1);
        order.addItem(item2);
        order.addItem(item3);

        order.showSummary();
    }
}