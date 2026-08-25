import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

class ServiceTicket {
    private String id;
    private String name;
    private boolean completed;
    private boolean cancelled;

    ServiceTicket(String id, String name) {
        this.id = id;
        this.name = name;
        this.completed = false;
        this.cancelled = false;
    }

    String getId() {
        return id;
    }

    void complete() {
        completed = true;
        cancelled = false;
    }

    void reopen() {
        completed = false;
        cancelled = false;
    }

    void cancel() {
        cancelled = true;
    }

    boolean isCompleted() {
        return completed;
    }

    boolean isCancelled() {
        return cancelled;
    }

    @Override
    public String toString() {
        String status;

        if (completed) {
            status = "completed";
        } else if (cancelled) {
            status = "cancelled";
        } else {
            status = "waiting";
        }

        return id + " " + name + " " + status;
    }
}

public class ServiceCenterWorkflow {

    static boolean createTicket(
            Map<String, ServiceTicket> tickets,
            Deque<ServiceTicket> waiting,
            Set<String> ids,
            String id,
            String name) {

        if (ids.contains(id)) {
            System.out.println("新增失敗：ticket " + id + " 已存在");
            return false;
        }

        ServiceTicket ticket = new ServiceTicket(id, name);

        tickets.put(id, ticket);
        waiting.offerLast(ticket);
        ids.add(id);

        System.out.println("建立：" + ticket);

        return true;
    }

    static ServiceTicket processNext(
            Deque<ServiceTicket> waiting,
            Deque<ServiceTicket> completedHistory) {

        ServiceTicket ticket = waiting.pollFirst();

        if (ticket == null) {
            System.out.println("處理失敗：目前沒有等待中的 ticket");
            return null;
        }

        ticket.complete();
        completedHistory.push(ticket);

        System.out.println("處理完成：" + ticket);

        return ticket;
    }

    static boolean cancelWaiting(
            Deque<ServiceTicket> waiting,
            String id) {

        ServiceTicket target = null;

        for (ServiceTicket ticket : waiting) {

            if (ticket.getId().equals(id)) {
                target = ticket;
                break;
            }
        }

        if (target == null) {
            System.out.println("取消失敗：找不到尚未處理的 " + id);
            return false;
        }

        waiting.remove(target);
        target.cancel();

        System.out.println("取消：" + target);

        return true;
    }

    static ServiceTicket undoLastCompletion(
            Deque<ServiceTicket> waiting,
            Deque<ServiceTicket> completedHistory) {

        ServiceTicket ticket = completedHistory.pollFirst();

        if (ticket == null) {
            System.out.println("Undo：沒有完成紀錄");
            return null;
        }

        ticket.reopen();
        waiting.offerFirst(ticket);

        System.out.println("Undo：" + ticket);

        return ticket;
    }

    static ServiceTicket findById(
            Map<String, ServiceTicket> tickets,
            String id) {

        ServiceTicket ticket = tickets.get(id);

        if (ticket == null) {
            System.out.println("查詢 " + id + "：找不到");
        } else {
            System.out.println("查詢 " + id + "：" + ticket);
        }

        return ticket;
    }

    static void printSummary(
            Map<String, ServiceTicket> tickets,
            Deque<ServiceTicket> waiting,
            Deque<ServiceTicket> completedHistory) {

        int completed = 0;
        int cancelled = 0;

        for (ServiceTicket ticket : tickets.values()) {

            if (ticket.isCompleted()) {
                completed++;
            }

            if (ticket.isCancelled()) {
                cancelled++;
            }
        }

        System.out.println();
        System.out.println("=== Summary ===");
        System.out.println("全部 ticket：" + tickets.size());
        System.out.println("等待中：" + waiting.size());
        System.out.println("已完成：" + completed);
        System.out.println("已取消：" + cancelled);
        System.out.println("完成歷程：" + completedHistory);
    }

    public static void main(String[] args) {

        Map<String, ServiceTicket> tickets =
                new LinkedHashMap<>();

        Deque<ServiceTicket> waiting =
                new ArrayDeque<>();

        Deque<ServiceTicket> completedHistory =
                new ArrayDeque<>();

        Set<String> ids =
                new LinkedHashSet<>();

        System.out.println("=== 服務中心排隊與取消 ===");

        // 建立 ticket
        createTicket(
                tickets, waiting, ids,
                "S001", "Amy");

        createTicket(
                tickets, waiting, ids,
                "S002", "Ben");

        createTicket(
                tickets, waiting, ids,
                "S003", "Cara");

        // 測試重複 id
        createTicket(
                tickets, waiting, ids,
                "S002", "David");

        System.out.println();

        // 查看 Queue
        System.out.println("Waiting：" + waiting);

        // 查看下一位
        System.out.println("下一位：" + waiting.peekFirst());

        System.out.println();

        // 取消尚未處理的 ticket
        cancelWaiting(waiting, "S002");

        // 取消不存在的 ticket
        cancelWaiting(waiting, "S999");

        System.out.println();

        // 處理
        processNext(waiting, completedHistory);
        processNext(waiting, completedHistory);

        // 空 Queue 測試
        processNext(waiting, completedHistory);

        System.out.println();

        // 查詢
        findById(tickets, "S001");
        findById(tickets, "S002");
        findById(tickets, "S999");

        System.out.println();

        // 第一次 Undo
        undoLastCompletion(waiting, completedHistory);

        // 第二次 Undo
        undoLastCompletion(waiting, completedHistory);

        // 第三次 Undo，測試空 Stack
        undoLastCompletion(waiting, completedHistory);

        System.out.println();

        System.out.println("Undo 後 Waiting：" + waiting);
        System.out.println("Undo 後 Completed：" + completedHistory);

        printSummary(tickets, waiting, completedHistory);
    }
}