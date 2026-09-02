import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CoursePlanningGraph {

    private Map<String, List<String>> graph;

    public CoursePlanningGraph() {
        graph = new HashMap<>();
    }

    public void addCourse(String course) {

        if (!graph.containsKey(course)) {
            graph.put(course, new ArrayList<>());
        }
    }

    public void addPrerequisite(
            String prerequisite,
            String course) {

        if (!graph.containsKey(prerequisite)
                || !graph.containsKey(course)) {

            System.out.println("課程不存在");
            return;
        }

        graph.get(prerequisite).add(course);
    }

    public boolean reachable(
            String start,
            String target) {

        if (!graph.containsKey(start)
                || !graph.containsKey(target)) {
            return false;
        }

        Set<String> visited = new HashSet<>();

        return dfsReachable(
                start,
                target,
                visited
        );
    }

    private boolean dfsReachable(
            String current,
            String target,
            Set<String> visited) {

        if (current.equals(target)) {
            return true;
        }

        visited.add(current);

        for (String next : graph.get(current)) {

            if (!visited.contains(next)) {

                if (dfsReachable(
                        next,
                        target,
                        visited)) {

                    return true;
                }
            }
        }

        return false;
    }

    public List<String> affectedCourses(String course) {

        List<String> result = new ArrayList<>();

        if (!graph.containsKey(course)) {
            return result;
        }

        Set<String> visited = new HashSet<>();

        dfsAffected(
                course,
                visited,
                result
        );

        return result;
    }

    private void dfsAffected(
            String current,
            Set<String> visited,
            List<String> result) {

        visited.add(current);

        for (String next : graph.get(current)) {

            if (!visited.contains(next)) {

                result.add(next);

                dfsAffected(
                        next,
                        visited,
                        result
                );
            }
        }
    }

    public void report() {

        System.out.println("===== 課程先修關係 =====");

        for (String course : graph.keySet()) {
            System.out.println(
                    course + " :"
                            + graph.get(course)
            );
        }
    }

    public static void main(String[] args) {

        CoursePlanningGraph courseGraph =
                new CoursePlanningGraph();

        courseGraph.addCourse("Java");
        courseGraph.addCourse("Data Structure");
        courseGraph.addCourse("Algorithm");
        courseGraph.addCourse("Advanced Algorithm");
        courseGraph.addCourse("Database");
        courseGraph.addCourse("AI");

        courseGraph.addPrerequisite(
                "Java",
                "Data Structure"
        );

        courseGraph.addPrerequisite(
                "Data Structure",
                "Algorithm"
        );

        courseGraph.addPrerequisite(
                "Algorithm",
                "Advanced Algorithm"
        );

        courseGraph.addPrerequisite(
                "Data Structure",
                "AI"
        );

        courseGraph.addPrerequisite(
                "Database",
                "AI"
        );

        courseGraph.report();

        System.out.println();

        System.out.println(
                "Java是否影響Advanced Algorithm："
                        + courseGraph.reachable(
                                "Java",
                                "Advanced Algorithm"
                        )
        );

        System.out.println(
                "Database是否影響Algorithm："
                        + courseGraph.reachable(
                                "Database",
                                "Algorithm"
                        )
        );

        System.out.println();

        System.out.println(
                "Data Structure受影響課程："
                        + courseGraph.affectedCourses(
                                "Data Structure"
                        )
        );

        System.out.println(
                "Java受影響課程："
                        + courseGraph.affectedCourses(
                                "Java"
                        )
        );
    }
}