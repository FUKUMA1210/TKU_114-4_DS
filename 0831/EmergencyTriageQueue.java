import java.util.Comparator;
import java.util.PriorityQueue;

public class EmergencyTriageQueue {

    public static class Patient {
        private String id;
        private String name;
        private int severity;
        private long order;

        public Patient(String id, String name, int severity, long order) {
            this.id = id;
            this.name = name;
            this.severity = severity;
            this.order = order;
        }

        public String getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public int getSeverity() {
            return severity;
        }

        public long getOrder() {
            return order;
        }

        public String toString() {
            return id + " " + name + " 緊急程度：" + severity;
        }
    }

    private PriorityQueue<Patient> queue;
    private long order;

    public EmergencyTriageQueue() {
        queue = new PriorityQueue<>(
                Comparator.comparingInt(Patient::getSeverity).reversed()
                        .thenComparingLong(Patient::getOrder)
                        .thenComparing(Patient::getId)
        );

        order = 0;
    }

    public void register(String id, String name, int severity) {
        order++;
        Patient patient = new Patient(id, name, severity, order);
        queue.offer(patient);

        System.out.println("報到：" + patient);
    }

    public Patient peekNext() {
        return queue.peek();
    }

    public Patient callNext() {
        Patient patient = queue.poll();

        if (patient == null) {
            System.out.println("目前沒有病患等待");
            return null;
        }

        System.out.println("叫號：" + patient);
        return patient;
    }

    public int size() {
        return queue.size();
    }

    public static void main(String[] args) {
        EmergencyTriageQueue emergency = new EmergencyTriageQueue();

        emergency.register("A1003", "小明", 2);
        emergency.register("A1001", "小華", 5);
        emergency.register("A1002", "小美", 5);
        emergency.register("A1004", "阿強", 3);

        System.out.println();
        System.out.println("下一位：" + emergency.peekNext());
        System.out.println("目前等待人數：" + emergency.size());

        System.out.println();

        while (emergency.size() > 0) {
            emergency.callNext();
        }

        emergency.callNext();
    }
}