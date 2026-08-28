import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class Q07_RequestPipeline {

    public static void main(String[] args) {

        String[] commands = {
                "NORMAL N1",
                "URGENT U1",
                "NORMAL N2",
                "PROCESS",
                "PROCESS",
                "PROCESS"
        };

        System.out.println(isBalanced("a{b[c](d)}"));
        System.out.println(isBalanced("([)]"));
        System.out.println(process(commands));
    }

    public static boolean isBalanced(String text) {

        if (text == null) {
            return false;
        }

        Deque<Character> stack = new ArrayDeque<>();

        for (int i = 0; i < text.length(); i++) {

            char ch = text.charAt(i);

            if (ch == '(' || ch == '[' || ch == '{') {
                stack.push(ch);
            } else if (ch == ')' || ch == ']' || ch == '}') {

                if (stack.isEmpty()) {
                    return false;
                }

                char top = stack.pop();

                if (ch == ')' && top != '(') {
                    return false;
                }

                if (ch == ']' && top != '[') {
                    return false;
                }

                if (ch == '}' && top != '{') {
                    return false;
                }
            }
        }

        return stack.isEmpty();
    }

    private static String takeUrgentCheckpoint(Deque<String> urgentQueue) {

        if (urgentQueue.isEmpty()) {
            return null;
        }

        return urgentQueue.removeFirst();
    }

    public static List<String> process(String[] commands) {

        List<String> result = new ArrayList<>();

        if (commands == null) {
            return result;
        }

        Deque<String> normalQueue = new ArrayDeque<>();
        Deque<String> urgentQueue = new ArrayDeque<>();

        for (String command : commands) {

            if (command == null || command.trim().equals("")) {
                continue;
            }

            String text = command.trim();
            String[] parts = text.split("\\s+");

            if (parts.length == 2) {

                if (parts[0].equals("NORMAL")) {
                    normalQueue.addLast(parts[1]);
                } else if (parts[0].equals("URGENT")) {
                    urgentQueue.addLast(parts[1]);
                }

            } else if (parts.length == 1 && parts[0].equals("PROCESS")) {

                String id = takeUrgentCheckpoint(urgentQueue);

                if (id != null) {
                    result.add(id);
                } else if (!normalQueue.isEmpty()) {
                    result.add(normalQueue.removeFirst());
                } else {
                    result.add("EMPTY");
                }
            }
        }

        return result;
    }
}