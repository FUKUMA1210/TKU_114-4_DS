import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class NetworkComponents {

    public static List<List<String>> getComponents(
            Map<String, List<String>> graph) {

        List<List<String>> components = new ArrayList<>();

        if (graph == null || graph.isEmpty()) {
            return components;
        }

        Set<String> visited = new LinkedHashSet<>();

        for (String start : graph.keySet()) {

            if (visited.contains(start)) {
                continue;
            }

            List<String> component = new ArrayList<>();
            Queue<String> queue = new ArrayDeque<>();

            queue.offer(start);
            visited.add(start);

            while (!queue.isEmpty()) {

                String current = queue.poll();

                component.add(current);

                for (String next : graph.get(current)) {

                    if (graph.containsKey(next)
                            && visited.add(next)) {

                        queue.offer(next);
                    }
                }
            }

            components.add(component);
        }

        return components;
    }

    public static int getComponentCount(
            Map<String, List<String>> graph) {

        return getComponents(graph).size();
    }

    public static List<String> getLargestComponent(
            Map<String, List<String>> graph) {

        List<List<String>> components = getComponents(graph);

        List<String> largest = new ArrayList<>();

        for (List<String> component : components) {

            if (component.size() > largest.size()) {
                largest = component;
            }
        }

        return largest;
    }

    public static void printReport(
            Map<String, List<String>> graph) {

        List<List<String>> components = getComponents(graph);

        System.out.println("Components：");

        for (int i = 0; i < components.size(); i++) {
            System.out.println(
                    "Component " + (i + 1)
                            + "：" + components.get(i));
        }

        System.out.println("Component Count："
                + getComponentCount(graph));

        System.out.println("最大component："
                + getLargestComponent(graph));
    }

    public static void main(String[] args) {

        Map<String, List<String>> graph = new LinkedHashMap<>();

        graph.put("A", List.of("B", "C"));
        graph.put("B", List.of("A", "C"));
        graph.put("C", List.of("A", "B"));

        graph.put("D", List.of("E"));
        graph.put("E", List.of("D"));

        graph.put("F", List.of());

        System.out.println("一般案例：");
        printReport(graph);

        System.out.println("\nEmpty Graph：");

        Map<String, List<String>> emptyGraph =
                new LinkedHashMap<>();

        printReport(emptyGraph);
    }
}