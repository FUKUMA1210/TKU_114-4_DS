import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

class CourseStudent {
    String studentId;
    String name;
    int score;
    String tag;

    CourseStudent(String studentId, String name, int score, String tag) {
        this.studentId = studentId;
        this.name = name;
        this.score = score;
        this.tag = tag;
    }

    public String toString() {
        return studentId + " " + name + " " + score + " " + tag;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }

        if (!(other instanceof CourseStudent)) {
            return false;
        }

        CourseStudent student = (CourseStudent) other;

        return studentId.equals(student.studentId);
    }

    @Override
    public int hashCode() {
        return studentId.hashCode();
    }
}

class CourseManager {
    private List<CourseStudent> students = new ArrayList<>();
    private Set<CourseStudent> studentSet = new HashSet<>();
    private Map<String, CourseStudent> studentMap = new HashMap<>();

    boolean add(CourseStudent student) {
        if (studentSet.add(student)) {
            students.add(student);
            studentMap.put(student.studentId, student);
            return true;
        }

        return false;
    }

    boolean updateScore(String studentId, int score) {
        CourseStudent student = studentMap.get(studentId);

        if (student == null) {
            return false;
        }

        student.score = score;
        return true;
    }

    List<CourseStudent> findByTag(String tag) {
        List<CourseStudent> result = new ArrayList<>();

        if (tag == null || tag.isBlank()) {
            return result;
        }

        for (CourseStudent student : students) {
            if (student.tag != null
                    && student.tag.equalsIgnoreCase(tag)) {
                result.add(student);
            }
        }

        return result;
    }

    Map<String, Integer> scoreDistribution() {
        Map<String, Integer> result = new HashMap<>();

        result.put("A", 0);
        result.put("B", 0);
        result.put("C", 0);
        result.put("D", 0);
        result.put("F", 0);

        for (CourseStudent student : students) {

            String level;

            if (student.score >= 90) {
                level = "A";
            } else if (student.score >= 80) {
                level = "B";
            } else if (student.score >= 70) {
                level = "C";
            } else if (student.score >= 60) {
                level = "D";
            } else {
                level = "F";
            }

            result.put(
                level,
                result.get(level) + 1
            );
        }

        return result;
    }

    List<CourseStudent> top(int count) {
        List<CourseStudent> result = new ArrayList<>(students);

        result.sort((a, b) -> b.score - a.score);

        if (count < result.size()) {
            return new ArrayList<>(
                result.subList(0, count)
            );
        }

        return result;
    }

    void removeBelow(int minimum) {

        for (int i = students.size() - 1; i >= 0; i--) {

            CourseStudent student = students.get(i);

            if (student.score < minimum) {
                students.remove(i);
                studentSet.remove(student);
                studentMap.remove(student.studentId);
            }
        }
    }

    void printAll() {
        for (CourseStudent student : students) {
            System.out.println(student);
        }
    }

    int size() {
        return students.size();
    }
}

public class CourseCollectionManager {
    public static void main(String[] args) {

        CourseManager manager = new CourseManager();

        manager.add(new CourseStudent(
                "111", "小明", 95, "Java"));

        manager.add(new CourseStudent(
                "112", "小華", 85, "Database"));

        manager.add(new CourseStudent(
                "113", "小美", 85, "Java"));

        manager.add(new CourseStudent(
                "114", "小強", 72, ""));

        manager.add(new CourseStudent(
                "115", "小安", 55, "Database"));

        manager.add(new CourseStudent(
                "111", "小明", 90, "Java"));

        manager.add(new CourseStudent(
                "116", "小婷", 65, "Java"));


        System.out.println("===== 所有報名資料 =====");
        manager.printAll();

        System.out.println();


        System.out.println("===== 更新成績 =====");

        System.out.println(
                "更新 114："
                + manager.updateScore("114", 78));

        manager.printAll();

        System.out.println();


        System.out.println("===== Java 標籤 =====");

        List<CourseStudent> javaStudents =
                manager.findByTag("Java");

        for (CourseStudent student : javaStudents) {
            System.out.println(student);
        }

        System.out.println();


        System.out.println("===== 成績分布 =====");

        Map<String, Integer> distribution =
                manager.scoreDistribution();

        System.out.println("A：" + distribution.get("A"));
        System.out.println("B：" + distribution.get("B"));
        System.out.println("C：" + distribution.get("C"));
        System.out.println("D：" + distribution.get("D"));
        System.out.println("F：" + distribution.get("F"));

        System.out.println();


        System.out.println("===== 前三名 =====");

        List<CourseStudent> topStudents =
                manager.top(3);

        for (CourseStudent student : topStudents) {
            System.out.println(student);
        }

        System.out.println();


        System.out.println("===== 移除 70 分以下 =====");

        manager.removeBelow(70);

        manager.printAll();

        System.out.println();

        System.out.println("剩餘人數：" + manager.size());
    }
}