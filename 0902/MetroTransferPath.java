import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class MetroTransferPath {

    public static List<String> shortestPath(
            Map<String, List<String>> graph,
            String start,
            String target) {

        List<String> path = new ArrayList<>();

        if (graph == null || graph.isEmpty()
                || start == null || target == null
                || !graph.containsKey(start)
                || !graph.containsKey(target)) {

            return path;
        }

        Queue<String> queue = new ArrayDeque<>();
        Set<String> visited = new HashSet<>();
        Map<String, String> previous = new HashMap<>();

        queue.offer(start);
        visited.add(start);

        while (!queue.isEmpty()) {

            String current = queue.poll();

            if (current.equals(target)) {
                break;
            }

            for (String next : graph.get(current)) {

                if (graph.containsKey(next)
                        && visited.add(next)) {

                    previous.put(next, current);
                    queue.offer(next);
                }
            }
        }

        if (!visited.contains(target)) {
            return path;
        }

        String current = target;

        while (current != null) {
            path.add(current);
            current = previous.get(current);
        }

        Collections.reverse(path);

        return path;
    }

    public static int getEdgeCount(List<String> path) {

        if (path == null || path.size() <= 1) {
            return 0;
        }

        return path.size() - 1;
    }

    public static void printPath(
            Map<String, List<String>> graph,
            String start,
            String target) {

        List<String> path = shortestPath(graph, start, target);

        if (path.isEmpty()) {
            System.out.println("找不到路徑");
            return;
        }

        System.out.println("最少站數路徑：" + path);
        System.out.println("Edge Count：" + getEdgeCount(path));
    }

    public static void main(String[] args) {

        Map<String, List<String>> metro = new LinkedHashMap<>();

        metro.put("淡水", List.of("紅樹林"));
        metro.put("紅樹林", List.of("淡水", "竹圍"));
        metro.put("竹圍", List.of("紅樹林", "關渡"));
        metro.put("關渡", List.of("竹圍", "忠義"));
        metro.put("忠義", List.of("關渡", "北投"));
        metro.put("北投", List.of("忠義", "奇岩"));
        metro.put("奇岩", List.of("北投"));

        System.out.println("一般案例：");
        printPath(metro, "淡水", "北投");

        System.out.println("\nStart等於Target：");
        printPath(metro, "淡水", "淡水");

        System.out.println("\nMissing Station：");
        printPath(metro, "淡水", "台北車站");

        System.out.println("\nEmpty Graph：");
        Map<String, List<String>> emptyGraph = new LinkedHashMap<>();
        printPath(emptyGraph, "A", "B");
    }
}