import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CourseDependencyGraph {

    private Map<String, Set<String>> graph;

    public CourseDependencyGraph() {
        graph = new HashMap<>();
    }

    public boolean addCourse(String course) {
        if (graph.containsKey(course)) {
            return false;
        }

        graph.put(course, new HashSet<>());
        return true;
    }

    public boolean addDependency(String prerequisite, String course) {
        if (!graph.containsKey(prerequisite)
                || !graph.containsKey(course)) {
            return false;
        }

        return graph.get(prerequisite).add(course);
    }

    public List<String> getPrerequisites(String course) {
        List<String> result = new ArrayList<>();

        if (!graph.containsKey(course)) {
            return result;
        }

        for (String prerequisite : graph.keySet()) {
            if (graph.get(prerequisite).contains(course)) {
                result.add(prerequisite);
            }
        }

        return result;
    }

    public List<String> getNextCourses(String course) {
        List<String> result = new ArrayList<>();

        if (!graph.containsKey(course)) {
            return result;
        }

        result.addAll(graph.get(course));

        return result;
    }

    public int inDegree(String course) {
        if (!graph.containsKey(course)) {
            return 0;
        }

        int count = 0;

        for (String from : graph.keySet()) {
            if (graph.get(from).contains(course)) {
                count++;
            }
        }

        return count;
    }

    public int outDegree(String course) {
        if (!graph.containsKey(course)) {
            return 0;
        }

        return graph.get(course).size();
    }

    public void printReport() {
        System.out.println("課程相依關係報告");

        for (String course : graph.keySet()) {
            System.out.println("\n課程：" + course);
            System.out.println("先修課程：" + getPrerequisites(course));
            System.out.println("後續課程：" + getNextCourses(course));
            System.out.println("In Degree：" + inDegree(course));
            System.out.println("Out Degree：" + outDegree(course));
        }
    }

    public static void main(String[] args) {
        CourseDependencyGraph graph = new CourseDependencyGraph();

        graph.addCourse("Java");
        graph.addCourse("資料結構");
        graph.addCourse("演算法");
        graph.addCourse("資料庫");
        graph.addCourse("C");

        graph.addDependency("Java", "資料結構");
        graph.addDependency("Java", "C");
        graph.addDependency("資料結構", "演算法");
        graph.addDependency("資料庫", "演算法");

        graph.printReport();
    }
}