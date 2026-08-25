import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedHashMap;
import java.util.Map;

class Delivery {
    private String id;
    private String address;
    private boolean completed;

    Delivery(String id, String address) {
        this.id = id;
        this.address = address;
        this.completed = false;
    }

    String getId() {
        return id;
    }

    void complete() {
        completed = true;
    }

    void reopen() {
        completed = false;
    }

    boolean isCompleted() {
        return completed;
    }

    @Override
    public String toString() {
        return id + " " + address + " completed=" + completed;
    }
}

public class DeliveryWorkflowSystem {

    static boolean addDelivery(Map<String, Delivery> deliveries,
                               Deque<Delivery> waiting,
                               String id,
                               String address) {

        if (deliveries.containsKey(id)) {
            System.out.println("新增失敗：配送編號 " + id + " 已存在");
            return false;
        }

        Delivery delivery = new Delivery(id, address);

        deliveries.put(id, delivery);
        waiting.offerLast(delivery);

        System.out.println("新增：" + delivery);

        return true;
    }

    static Delivery processNext(Deque<Delivery> waiting,
                                Deque<Delivery> completedHistory) {

        Delivery delivery = waiting.pollFirst();

        if (delivery == null) {
            System.out.println("處理失敗：目前沒有等待配送");
            return null;
        }

        delivery.complete();
        completedHistory.push(delivery);

        System.out.println("完成配送：" + delivery);

        return delivery;
    }

    static void undo(Deque<Delivery> waiting,
                     Deque<Delivery> completedHistory) {

        Delivery delivery = completedHistory.pollFirst();

        if (delivery == null) {
            System.out.println("Undo：沒有可以復原的配送");
            return;
        }

        delivery.reopen();
        waiting.offerFirst(delivery);

        System.out.println("Undo：" + delivery);
    }

    static void findDelivery(Map<String, Delivery> deliveries,
                             String id) {

        Delivery delivery = deliveries.get(id);

        if (delivery == null) {
            System.out.println("查詢：" + id + " 找不到");
        } else {
            System.out.println("查詢：" + delivery);
        }
    }

    static void printSummary(Map<String, Delivery> deliveries,
                             Deque<Delivery> waiting,
                             Deque<Delivery> completedHistory) {

        int completed = 0;

        for (Delivery delivery : deliveries.values()) {
            if (delivery.isCompleted()) {
                completed++;
            }
        }

        System.out.println();
        System.out.println("=== 統計 ===");
        System.out.println("全部配送：" + deliveries.size());
        System.out.println("等待配送：" + waiting.size());
        System.out.println("已完成配送：" + completed);
        System.out.println("完成歷程：" + completedHistory);
    }

    public static void main(String[] args) {

        Map<String, Delivery> deliveries = new LinkedHashMap<>();
        Deque<Delivery> waiting = new ArrayDeque<>();
        Deque<Delivery> completedHistory = new ArrayDeque<>();

        System.out.println("=== 物流工作流程 ===");

        addDelivery(deliveries, waiting, "D1", "台北");
        addDelivery(deliveries, waiting, "D2", "新北");
        addDelivery(deliveries, waiting, "D3", "桃園");

        addDelivery(deliveries, waiting, "D1", "台中");

        System.out.println();
        System.out.println("Waiting：" + waiting);

        processNext(waiting, completedHistory);
        processNext(waiting, completedHistory);

        findDelivery(deliveries, "D");
        findDelivery(deliveries, "D0");

        System.out.println();
        System.out.println("完成歷程：" + completedHistory);

        undo(waiting, completedHistory);

        System.out.println("Undo 後 Waiting：" + waiting);
        System.out.println("Undo 後 Completed：" + completedHistory);

        processNext(waiting, completedHistory);

        undo(waiting, completedHistory);
        undo(waiting, completedHistory);

        printSummary(deliveries, waiting, completedHistory);
    }
}