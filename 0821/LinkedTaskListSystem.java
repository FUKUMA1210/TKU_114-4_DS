class Task {
    private String id;
    private String title;

    Task(String id, String title) {
        this.id = id;
        this.title = title;
    }

    String getId() {
        return id;
    }

    @Override
    public String toString() {
        return id + " " + title;
    }
}

class TaskNode {
    Task task;
    TaskNode next;

    TaskNode(Task task) {
        this.task = task;
    }
}

class TaskLinkedList {

    private TaskNode head;
    private int size;

    boolean addFirst(Task task) {

        if (findById(task.getId()) != null) {
            return false;
        }

        TaskNode node = new TaskNode(task);
        node.next = head;
        head = node;
        size++;

        return true;
    }

    boolean addLast(Task task) {

        if (findById(task.getId()) != null) {
            return false;
        }

        TaskNode node = new TaskNode(task);

        if (head == null) {
            head = node;
        } else {
            TaskNode current = head;

            while (current.next != null) {
                current = current.next;
            }

            current.next = node;
        }

        size++;

        return true;
    }

    Task findById(String id) {

        TaskNode current = head;

        while (current != null) {

            if (current.task.getId().equals(id)) {
                return current.task;
            }

            current = current.next;
        }

        return null;
    }

    boolean removeById(String id) {

        if (head == null) {
            return false;
        }

        if (head.task.getId().equals(id)) {
            head = head.next;
            size--;
            return true;
        }

        TaskNode current = head;

        while (current.next != null) {

            if (current.next.task.getId().equals(id)) {
                current.next = current.next.next;
                size--;
                return true;
            }

            current = current.next;
        }

        return false;
    }

    boolean insertAfter(String existingId, Task task) {

        if (findById(task.getId()) != null) {
            return false;
        }

        TaskNode current = head;

        while (current != null) {

            if (current.task.getId().equals(existingId)) {

                TaskNode node = new TaskNode(task);

                node.next = current.next;
                current.next = node;

                size++;

                return true;
            }

            current = current.next;
        }

        return false;
    }

    int size() {
        return size;
    }

    void printAll() {

        if (head == null) {
            System.out.println("List 是空的");
            return;
        }

        TaskNode current = head;

        while (current != null) {
            System.out.println(current.task);
            current = current.next;
        }
    }
}

public class LinkedTaskListSystem {

    public static void main(String[] args) {

        TaskLinkedList list = new TaskLinkedList();

        System.out.println("=== 單向鏈結清單 ===");

        System.out.println("1. 空 list");
        list.printAll();
        System.out.println("size：" + list.size());
        System.out.println();

        System.out.println("2. 新增資料");

        list.addLast(new Task("01", "完成作業"));
        list.addLast(new Task("02", "讀書"));
        list.addLast(new Task("03", "寫程式"));

        list.printAll();
        System.out.println();

        System.out.println("3. addFirst");

        list.addFirst(new Task("00", "開始學習"));

        list.printAll();
        System.out.println();

        System.out.println("4. 測試重複 id");

        System.out.println(
                "新增 02：" +
                list.addLast(new Task("02", "重複工作"))
        );

        System.out.println();

        // insertAfter
        System.out.println("5. insertAfter 02");

        System.out.println(
                "插入結果：" +
                list.insertAfter(
                        "02",
                        new Task("02-5", "複習")
                )
        );

        list.printAll();
        System.out.println();

        System.out.println("6. 查詢");

        System.out.println("03：" + list.findById("03"));
        System.out.println("99：" + list.findById("99"));
        System.out.println();

        System.out.println("7. 刪除 head 00");

        System.out.println("結果：" + list.removeById("00"));
        list.printAll();
        System.out.println();

        System.out.println("8. 刪除 middle 02");

        System.out.println("結果：" + list.removeById("02"));
        list.printAll();
        System.out.println();

        System.out.println("9. 刪除 tail 03");

        System.out.println("結果：" + list.removeById("03"));
        list.printAll();
        System.out.println();

        System.out.println("10. 刪除不存在的 id");

        System.out.println("結果：" + list.removeById("99"));
        System.out.println();

        System.out.println("最後 size：" + list.size());
    }
}