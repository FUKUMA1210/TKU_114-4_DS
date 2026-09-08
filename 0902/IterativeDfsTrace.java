import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.LinkedHashMap;

public class IterativeDfsTrace {

    public static List<String> dfsTrace(
            Map<String, List<String>> graph, String start) {

        List<String> result = new ArrayList<>();

        if (graph == null || graph.isEmpty()
                || start == null || !graph.containsKey(start)) {

            System.out.println("graph為空或找不到起點");
            return result;
        }

        Deque<String> stack = new ArrayDeque<>();
        Set<String> visited = new LinkedHashSet<>();

        stack.push(start);

        System.out.println("Push " + start);
        System.out.println("Stack：" + stack);
        System.out.println("Visited：" + visited);
        System.out.println();

        while (!stack.isEmpty()) {

            String current = stack.pop();

            System.out.println("Pop " + current);

            if (!visited.add(current)) {
                System.out.println("Stack：" + stack);
                System.out.println("Visited：" + visited);
                System.out.println();
                continue;
            }

            result.add(current);

            System.out.println("Stack：" + stack);
            System.out.println("Visited：" + visited);
            System.out.println();

            List<String> neighbors = graph.get(current);

            for (int i = neighbors.size() - 1; i >= 0; i--) {

                String next = neighbors.get(i);

                if (graph.containsKey(next)
                        && !visited.contains(next)) {

                    stack.push(next);

                    System.out.println("Push " + next);
                    System.out.println("Stack：" + stack);
                    System.out.println("Visited：" + visited);
                    System.out.println();
                }
            }
        }

        return result;
    }

    public static void main(String[] args) {

        Map<String, List<String>> graph = new LinkedHashMap<>();

        graph.put("A", List.of("B", "C"));
        graph.put("B", List.of("D"));
        graph.put("C", List.of("D"));
        graph.put("D", List.of("A"));

        System.out.println("一般案例：");

        List<String> result = dfsTrace(graph, "A");

        System.out.println("DFS順序：" + result);

        System.out.println("\nMissing vertex：");
        dfsTrace(graph, "X");

        System.out.println("\nEmpty graph：");
        Map<String, List<String>> emptyGraph = new LinkedHashMap<>();
        dfsTrace(emptyGraph, "A");
    }
}