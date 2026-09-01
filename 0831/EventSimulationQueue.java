import java.util.Comparator;
import java.util.PriorityQueue;

public class EventSimulationQueue {

    public static class Event {
        private String type;
        private int time;
        private long sequence;

        public Event(String type, int time, long sequence) {
            this.type = type;
            this.time = time;
            this.sequence = sequence;
        }

        public String getType() {
            return type;
        }

        public int getTime() {
            return time;
        }

        public long getSequence() {
            return sequence;
        }

        public String toString() {
            return "時間：" + time + " 類型：" + type + " sequence：" + sequence;
        }
    }

    private PriorityQueue<Event> queue;
    private long sequence;

    public EventSimulationQueue() {
        queue = new PriorityQueue<>(
                Comparator.comparingInt(Event::getTime)
                        .thenComparingLong(Event::getSequence)
        );

        sequence = 0;
    }

    public void addEvent(String type, int time) {
        sequence++;

        Event event = new Event(type, time, sequence);
        queue.offer(event);

        System.out.println("加入事件：" + event);
    }

    public boolean cancelEvent(String type) {
        for (Event event : queue) {
            if (event.getType().equals(type)) {
                queue.remove(event);
                System.out.println("取消事件：" + event);
                return true;
            }
        }

        System.out.println("找不到事件：" + type);
        return false;
    }

    public void runAll() {
        System.out.println();
        System.out.println("開始執行事件");

        while (!queue.isEmpty()) {
            Event event = queue.poll();
            System.out.println("執行：" + event);
        }

        System.out.println("所有事件執行完畢");
    }

    public static void main(String[] args) {
        EventSimulationQueue simulation = new EventSimulationQueue();

        simulation.addEvent("開始活動", 10);
        simulation.addEvent("播放音樂", 5);
        simulation.addEvent("抽獎", 10);
        simulation.addEvent("結束活動", 20);
        simulation.addEvent("開場表演", 5);

        System.out.println();

        simulation.cancelEvent("抽獎");

        simulation.runAll();
    }
}