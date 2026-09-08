import java.util.PriorityQueue;

public class SupportTicketQueue {

    static class Ticket {

        private String id;
        private int severity;
        private int createdOrder;

        public Ticket(
                String id,
                int severity,
                int createdOrder) {

            this.id = id;
            this.severity = severity;
            this.createdOrder = createdOrder;
        }

        public String getId() {
            return id;
        }

        public int getSeverity() {
            return severity;
        }

        public int getCreatedOrder() {
            return createdOrder;
        }

        @Override
        public String toString() {

            return id
                    + "|"
                    + severity
                    + "|"
                    + createdOrder;
        }
    }

    public static void main(String[] args) {

        PriorityQueue<Ticket> queue =
                new PriorityQueue<>((a, b) -> {

                    if (a.getSeverity()
                            != b.getSeverity()) {

                        return b.getSeverity()
                                - a.getSeverity();
                    }
                    return a.getCreatedOrder()
                            - b.getCreatedOrder();
                });


        queue.add(new Ticket("T001", 3, 1));
        queue.add(new Ticket("T002", 5, 2));
        queue.add(new Ticket("T003", 2, 3));
        queue.add(new Ticket("T004", 5, 4));
        queue.add(new Ticket("T005", 4, 5));


        System.out.println("依優先順序處理：");

        while (!queue.isEmpty()) {

            System.out.println(queue.poll());
        }
    }
}