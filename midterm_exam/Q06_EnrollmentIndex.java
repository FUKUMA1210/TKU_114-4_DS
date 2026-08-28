import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Q06_EnrollmentIndex {

    public static void main(String[] args) {

        Q06_EnrollmentIndex index = new Q06_EnrollmentIndex();

        index.enroll("DS", "S02");
        index.enroll("DS", "S01");
        index.enroll("JAVA", "S01");

        System.out.println(index.studentsOf("DS"));
        System.out.println(index.coursesOf("S01"));
        System.out.println(index.summary());
    }

    private Map<String, Set<String>> enrollmentMapR26;

    public Q06_EnrollmentIndex() {
        enrollmentMapR26 = new HashMap<>();
    }

    public boolean enroll(String courseCode, String studentId) {

        if (courseCode == null || courseCode.trim().equals("")) {
            return false;
        }

        if (studentId == null || studentId.trim().equals("")) {
            return false;
        }

        courseCode = courseCode.trim();
        studentId = studentId.trim();

        if (!enrollmentMapR26.containsKey(courseCode)) {
            enrollmentMapR26.put(courseCode, new HashSet<>());
        }

        Set<String> students = enrollmentMapR26.get(courseCode);

        if (students.contains(studentId)) {
            return false;
        }

        students.add(studentId);

        return true;
    }

    public boolean drop(String courseCode, String studentId) {

        if (courseCode == null || courseCode.trim().equals("")) {
            return false;
        }

        if (studentId == null || studentId.trim().equals("")) {
            return false;
        }

        courseCode = courseCode.trim();
        studentId = studentId.trim();

        if (!enrollmentMapR26.containsKey(courseCode)) {
            return false;
        }

        Set<String> students = enrollmentMapR26.get(courseCode);

        if (!students.contains(studentId)) {
            return false;
        }

        students.remove(studentId);

        if (students.isEmpty()) {
            enrollmentMapR26.remove(courseCode);
        }

        return true;
    }

    public int courseSize(String courseCode) {

        if (courseCode == null || courseCode.trim().equals("")) {
            return 0;
        }

        courseCode = courseCode.trim();

        if (!enrollmentMapR26.containsKey(courseCode)) {
            return 0;
        }

        return enrollmentMapR26.get(courseCode).size();
    }

    public List<String> studentsOf(String courseCode) {

        List<String> result = new ArrayList<>();

        if (courseCode == null || courseCode.trim().equals("")) {
            return result;
        }

        courseCode = courseCode.trim();

        if (!enrollmentMapR26.containsKey(courseCode)) {
            return result;
        }

        result.addAll(enrollmentMapR26.get(courseCode));
        Collections.sort(result);

        return result;
    }

    public List<String> coursesOf(String studentId) {

        List<String> result = new ArrayList<>();

        if (studentId == null || studentId.trim().equals("")) {
            return result;
        }

        studentId = studentId.trim();

        for (String courseCode : enrollmentMapR26.keySet()) {

            Set<String> students = enrollmentMapR26.get(courseCode);

            if (students.contains(studentId)) {
                result.add(courseCode);
            }
        }

        Collections.sort(result);

        return result;
    }

    public Map<String, Integer> summary() {

        Map<String, Integer> result = new java.util.TreeMap<>();

        for (String courseCode : enrollmentMapR26.keySet()) {
            result.put(courseCode, enrollmentMapR26.get(courseCode).size());
        }

        return result;
    }
}