import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class EnrollmentConflictSet {

    public static void main(String[] args) {

        String[][] enrollments = {
                {"411630001", "DS"},
                {"411630001", "JAVA"},
                {"411630002", "DS"},
                {"411630002", "DB"},
                {"411630001", "DS"},
                {"411630003", "JAVA"},
                {"411630002", "DS"},
                {"411630003", "GRAPH"}
        };

        Set<String> enrollmentSet = new HashSet<>();
        Set<String> duplicateSet = new HashSet<>();

        Map<String, Set<String>> studentCourses =
                new HashMap<>();

        Map<String, Integer> courseCount =
                new HashMap<>();

        for (String[] enrollment : enrollments) {

            String studentId = enrollment[0];
            String courseId = enrollment[1];

            String key = studentId + "#" + courseId;

            if (!enrollmentSet.add(key)) {
                duplicateSet.add(key);
                continue;
            }

            if (!studentCourses.containsKey(studentId)) {
                studentCourses.put(studentId, new HashSet<>());
            }

            studentCourses.get(studentId).add(courseId);

            if (courseCount.containsKey(courseId)) {
                courseCount.put(
                        courseId,
                        courseCount.get(courseId) + 1
                );
            } else {
                courseCount.put(courseId, 1);
            }
        }

        System.out.println("===== 重複選課紀錄 =====");

        if (duplicateSet.isEmpty()) {
            System.out.println("沒有重複選課");
        } else {
            for (String duplicate : duplicateSet) {
                System.out.println(duplicate);
            }
        }

        System.out.println();
        System.out.println("===== 每人修課集合 =====");

        for (String studentId : studentCourses.keySet()) {
            System.out.println(
                    studentId + ":"
                            + studentCourses.get(studentId)
            );
        }

        System.out.println();
        System.out.println("===== 每門課修課人數 =====");

        for (String courseId : courseCount.keySet()) {
            System.out.println(
                    courseId + "：" +
                            courseCount.get(courseId) + "人"
            );
        }
    }
}