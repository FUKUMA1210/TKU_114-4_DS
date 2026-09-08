import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class DirectedReachability {

    public static boolean reachable(
            Map<String, List<String>> graph,
            String from,
            String to) {

        if (graph == null || graph.isEmpty()
                || from == null || to == null
                || !graph.containsKey(from)
                || !graph.containsKey(to)) {

            return false;
        }

        if (from.equals(to)) {
            return true;
        }

        Queue<String> queue = new ArrayDeque<>();
        Set<String> visited = new HashSet<>();

        queue.offer(from);
        visited.add(from);

        while (!queue.isEmpty()) {

            String current = queue.poll();

            for (String next : graph.get(current)) {

                if (next.equals(to)) {
                    return true;
                }

                if (graph.containsKey(next)
                        && visited.add(next)) {

                    queue.offer(next);
                }
            }
        }

        return false;
    }

    public static void printQuery(
            Map<String, List<String>> graph,
            String from,
            String to) {

        System.out.println(from + " -> " + to
                + "：" + reachable(graph, from, to));
    }

    public static void main(String[] args) {

        Map<String, List<String>> graph = new LinkedHashMap<>();

        graph.put("A", List.of("B", "C"));
        graph.put("B", List.of("D"));
        graph.put("C", List.of("E"));
        graph.put("D", List.of("F"));
        graph.put("E", List.of());
        graph.put("F", List.of());

        System.out.println("多組查詢：");

        printQuery(graph, "A", "F");
        printQuery(graph, "A", "E");
        printQuery(graph, "B", "E");
        printQuery(graph, "F", "A");

        System.out.println("\nStart等於Target：");
        printQuery(graph, "A", "A");

        System.out.println("\nMissing Vertex：");
        printQuery(graph, "A", "X");

        System.out.println("\nEmpty Graph：");
        Map<String, List<String>> emptyGraph = new LinkedHashMap<>();
        printQuery(emptyGraph, "A", "B");
    }
}