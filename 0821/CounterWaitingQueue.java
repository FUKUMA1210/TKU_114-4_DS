import java.util.ArrayDeque;
import java.util.Deque;

public class CounterWaitingQueue {

    static class Customer {

        private int number;
        private String name;

        public Customer(int number, String name) {
            this.number = number;
            this.name = name;
        }

        @Override
        public String toString() {
            return number + "號 " + name;
        }
    }

    private Deque<Customer> waiting = new ArrayDeque<>();

    public void addCustomer(Customer customer) {

        if (customer == null) {
            return;
        }

        waiting.offerLast(customer);

        System.out.println("加入：" + customer);
    }

    public Customer peekNext() {

        Customer customer = waiting.peekFirst();

        if (customer == null) {
            System.out.println("目前沒有等待顧客");
            return null;
        }

        System.out.println("下一位：" + customer);

        return customer;
    }

    public Customer serveNext() {

        Customer customer = waiting.pollFirst();

        if (customer == null) {
            System.out.println("目前沒有顧客可以服務");
            return null;
        }

        System.out.println("服務：" + customer);

        return customer;
    }

    public int waitingCount() {

        return waiting.size();
    }

    public void showWaiting() {

        System.out.println("目前等候：" + waiting);
        System.out.println("等候人數：" + waiting.size());
    }

    public static void main(String[] args) {

        CounterWaitingQueue counter =
                new CounterWaitingQueue();

        counter.addCustomer(
                new Customer(1, "小明"));

        counter.addCustomer(
                new Customer(2, "小華"));

        counter.addCustomer(
                new Customer(3, "小美"));

        counter.showWaiting();

        counter.peekNext();

        counter.serveNext();

        counter.peekNext();

        counter.serveNext();

        counter.serveNext();

        counter.showWaiting();

        counter.serveNext();
    }
}