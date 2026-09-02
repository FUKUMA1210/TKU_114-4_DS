import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class CampusNavigationSystem {

    private Map<String, String> locations;
    private Map<String, List<String>> graph;

    public CampusNavigationSystem() {
        locations = new HashMap<>();
        graph = new HashMap<>();
    }

    public void addLocation(String id, String name) {
        locations.put(id, name);

        if (!graph.containsKey(id)) {
            graph.put(id, new ArrayList<>());
        }
    }

    public void addRoad(String from, String to) {
        if (!locations.containsKey(from)
                || !locations.containsKey(to)) {

            System.out.println("地點不存在");
            return;
        }

        graph.get(from).add(to);
        graph.get(to).add(from);
    }

    public List<String> shortestPath(String start, String target) {

        List<String> path = new ArrayList<>();

        if (!graph.containsKey(start)
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
                if (!visited.contains(next)) {
                    visited.add(next);
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

    public void printPath(String start, String target) {
        List<String> path = shortestPath(start, target);

        if (path.isEmpty()) {
            System.out.println("找不到路徑");
            return;
        }

        System.out.println("最少Edge路徑：");

        for (int i = 0; i < path.size(); i++) {
            String id = path.get(i);

            System.out.print(locations.get(id));

            if (i < path.size() - 1) {
                System.out.print(":");
            }
        }

        System.out.println();
        System.out.println("Edge數量：" + (path.size() - 1));
    }

    public static void main(String[] args) {

        CampusNavigationSystem campus =
                new CampusNavigationSystem();

        campus.addLocation("A", "校門口");
        campus.addLocation("B", "商管大樓");
        campus.addLocation("C", "圖書館");
        campus.addLocation("D", "工學大樓");
        campus.addLocation("E", "學生餐廳");
        campus.addLocation("F", "操場");

        campus.addRoad("A", "B");
        campus.addRoad("A", "C");
        campus.addRoad("B", "D");
        campus.addRoad("C", "D");
        campus.addRoad("C", "E");
        campus.addRoad("D", "F");
        campus.addRoad("E", "F");

        campus.printPath("A", "F");

        System.out.println();

        campus.printPath("A", "X");
    }
}