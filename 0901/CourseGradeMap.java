import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CourseGradeMap {

    private Map<String, List<Integer>> courseGrades;

    public CourseGradeMap() {
        courseGrades = new HashMap<>();
    }

    public void addGrade(String course, int grade) {
        if (!courseGrades.containsKey(course)) {
            courseGrades.put(course, new ArrayList<>());
        }

        courseGrades.get(course).add(grade);
    }

    public double getAverage(String course) {
        List<Integer> grades = courseGrades.get(course);

        if (grades == null || grades.isEmpty()) {
            return 0;
        }

        int sum = 0;

        for (int grade : grades) {
            sum += grade;
        }

        return (double) sum / grades.size();
    }

    public int getHighest(String course) {
        List<Integer> grades = courseGrades.get(course);

        if (grades == null || grades.isEmpty()) {
            return -1;
        }

        int highest = grades.get(0);

        for (int grade : grades) {
            if (grade > highest) {
                highest = grade;
            }
        }

        return highest;
    }

    public void printReport() {
        List<String> courses = new ArrayList<>(courseGrades.keySet());

        Collections.sort(courses);

        System.out.println("課程成績報告");

        for (String course : courses) {
            System.out.println("課號：" + course);
            System.out.println("成績：" + courseGrades.get(course));
            System.out.println("平均：" + getAverage(course));
            System.out.println("最高分：" + getHighest(course));
            System.out.println();
        }
    }

    public static void main(String[] args) {
        CourseGradeMap system = new CourseGradeMap();

        system.addGrade("CS101", 80);
        system.addGrade("CS101", 90);
        system.addGrade("CS101", 75);

        system.addGrade("IM202", 88);
        system.addGrade("IM202", 95);
        system.addGrade("IM202", 70);

        system.addGrade("DS301", 100);
        system.addGrade("DS301", 85);

        system.printReport();
    }
}