import java.util.ArrayDeque;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class BfsLayerReport {

    public static Map<String, Integer> getDistances(
            Map<String, List<String>> graph, String start) {

        Map<String, Integer> distance = new LinkedHashMap<>();

        if (graph == null || graph.isEmpty()
                || start == null || !graph.containsKey(start)) {
            return distance;
        }

        Queue<String> queue = new ArrayDeque<>();
        Set<String> visited = new LinkedHashSet<>();

        queue.offer(start);
        visited.add(start);
        distance.put(start, 0);

        while (!queue.isEmpty()) {
            String current = queue.poll();

            for (String next : graph.get(current)) {
                if (graph.containsKey(next) && !visited.contains(next)) {
                    visited.add(next);
                    queue.offer(next);

                    distance.put(next, distance.get(current) + 1);
                }
            }
        }

        return distance;
    }

    public static void printReport(
            Map<String, List<String>> graph, String start) {

        Map<String, Integer> distance = getDistances(graph, start);

        if (distance.isEmpty()) {
            System.out.println("找不到起點或graph為空");
            return;
        }

        System.out.println("起點：" + start);

        for (String vertex : distance.keySet()) {
            System.out.println(vertex + "距離 " + start
                    + "：" + distance.get(vertex));
        }
    }

    public static void main(String[] args) {

        Map<String, List<String>> graph = new LinkedHashMap<>();

        graph.put("A", List.of("B", "C"));
        graph.put("B", List.of("A", "D", "E"));
        graph.put("C", List.of("A", "F"));
        graph.put("D", List.of("B"));
        graph.put("E", List.of("B"));
        graph.put("F", List.of("C"));
        graph.put("G", List.of());

        System.out.println("一般案例：");
        printReport(graph, "A");

        System.out.println("\n孤立vertex：");
        printReport(graph, "G");

        System.out.println("\n不存在的vertex：");
        printReport(graph, "X");

        System.out.println("\nEmpty graph：");
        Map<String, List<String>> emptyGraph = new LinkedHashMap<>();
        printReport(emptyGraph, "A");
    }
}