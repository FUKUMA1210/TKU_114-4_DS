import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class ServiceRequestSystem {

    public static class ServiceRequest {

        private String id;
        private String description;
        private int priority;
        private long sequence;

        public ServiceRequest(
                String id,
                String description,
                int priority,
                long sequence) {

            this.id = id;
            this.description = description;
            this.priority = priority;
            this.sequence = sequence;
        }

        public String getId() {
            return id;
        }

        public String getDescription() {
            return description;
        }

        public int getPriority() {
            return priority;
        }

        public long getSequence() {
            return sequence;
        }

        public String toString() {
            return "ID:" + id
                    + ", 內容:" + description
                    + ", 優先度:" + priority;
        }
    }

    private Map<String, ServiceRequest> requestMap;

    private PriorityQueue<ServiceRequest> queue;

    private long sequence;

    public ServiceRequestSystem() {

        requestMap = new HashMap<>();

        queue = new PriorityQueue<>(
                Comparator.comparingInt(
                        ServiceRequest::getPriority
                ).reversed()
                        .thenComparingLong(
                                ServiceRequest::getSequence
                        )
        );

        sequence = 0;
    }

    public boolean addRequest(
            String id,
            String description,
            int priority) {

        if (requestMap.containsKey(id)) {
            System.out.println("ID已存在：" + id);
            return false;
        }

        sequence++;

        ServiceRequest request =
                new ServiceRequest(
                        id,
                        description,
                        priority,
                        sequence
                );

        requestMap.put(id, request);
        queue.offer(request);

        System.out.println("新增：" + request);

        return true;
    }

    public ServiceRequest findRequest(String id) {
        return requestMap.get(id);
    }

    public ServiceRequest nextRequest() {

        if (queue.isEmpty()) {
            return null;
        }

        ServiceRequest request = queue.poll();

        requestMap.remove(request.getId());

        return request;
    }

    public boolean cancelRequest(String id) {

        ServiceRequest request = requestMap.get(id);

        if (request == null) {
            System.out.println("找不到Request：" + id);
            return false;
        }

        requestMap.remove(id);
        queue.remove(request);

        System.out.println("取消：" + request);

        return true;
    }

    public int size() {
        return requestMap.size();
    }

    public static void main(String[] args) {

        ServiceRequestSystem system =
                new ServiceRequestSystem();

        system.addRequest(
                "R001",
                "網路故障",
                5
        );

        system.addRequest(
                "R002",
                "印表機故障",
                2
        );

        system.addRequest(
                "R003",
                "伺服器異常",
                10
        );

        system.addRequest(
                "R004",
                "帳號問題",
                5
        );

        System.out.println();

        System.out.println(
                "查詢R002：" +
                        system.findRequest("R002")
        );

        System.out.println();

        system.cancelRequest("R004");

        System.out.println();

        System.out.println(
                "下一筆處理：" +
                        system.nextRequest()
        );

        System.out.println();

        System.out.println(
                "目前剩餘數量：" +
                        system.size()
        );

        System.out.println();

        while (system.size() > 0) {
            System.out.println(
                    "處理：" +
                            system.nextRequest()
            );
        }

        System.out.println(
                "下一筆：" +
                        system.nextRequest()
        );
    }
}