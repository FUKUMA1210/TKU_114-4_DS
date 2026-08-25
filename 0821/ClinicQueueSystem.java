import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

class Patient {
    private String id;
    private String name;

    Patient(String id, String name) {
        this.id = id;
        this.name = name;
    }

    String getId() {
        return id;
    }

    @Override
    public String toString() {
        return id + " " + name;
    }
}

public class ClinicQueueSystem {

    static void register(Deque<Patient> queue, Patient patient) {
        queue.offerLast(patient);
        System.out.println("掛號：" + patient);
    }

    static void cancel(Deque<Patient> queue, String id) {

        Patient target = null;

        for (Patient patient : queue) {
            if (patient.getId().equals(id)) {
                target = patient;
                break;
            }
        }

        if (target == null) {
            System.out.println("取消失敗：找不到病歷號 " + id);
        } else {
            queue.remove(target);
            System.out.println("取消掛號：" + target);
        }
    }

    static Patient callNext(Deque<Patient> queue,
                            List<Patient> completed) {

        Patient patient = queue.pollFirst();

        if (patient == null) {
            System.out.println("叫號：目前沒有病人");
            return null;
        }

        completed.add(patient);
        System.out.println("叫號：" + patient);

        return patient;
    }

    static void showNext(Deque<Patient> queue) {

        Patient patient = queue.peekFirst();

        if (patient == null) {
            System.out.println("下一位：目前沒有病人");
        } else {
            System.out.println("下一位：" + patient);
        }
    }

    static void printCompleted(List<Patient> completed) {

        System.out.println("當日完成清單：");

        if (completed.isEmpty()) {
            System.out.println("目前沒有完成看診");
        } else {
            for (Patient patient : completed) {
                System.out.println(patient);
            }
        }
    }

    public static void main(String[] args) {

        Deque<Patient> queue = new ArrayDeque<>();
        List<Patient> completed = new ArrayList<>();

        System.out.println("=== 診所掛號系統 ===");

        register(queue, new Patient("P001", "Amy"));
        register(queue, new Patient("P002", "Ben"));
        register(queue, new Patient("P003", "Cara"));
        register(queue, new Patient("P004", "David"));

        System.out.println("目前 Queue：" + queue);
        System.out.println();

        showNext(queue);

        cancel(queue, "P003");

        System.out.println("取消後 Queue：" + queue);
        System.out.println();

        callNext(queue, completed);
        callNext(queue, completed);

        showNext(queue);

        callNext(queue, completed);
        callNext(queue, completed);

        callNext(queue, completed);

        System.out.println();
        printCompleted(completed);
    }
}