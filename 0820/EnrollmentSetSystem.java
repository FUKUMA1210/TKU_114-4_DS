import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

class Enrollment {
    private String studentId;
    private String courseCode;

    Enrollment(String studentId, String courseCode) {
        this.studentId = studentId;
        this.courseCode = courseCode;
    }

    @Override
    public boolean equals(Object other) {

        if (this == other) {
            return true;
        }

        if (!(other instanceof Enrollment)) {
            return false;
        }

        Enrollment enrollment = (Enrollment) other;

        return Objects.equals(studentId, enrollment.studentId)
                && Objects.equals(courseCode, enrollment.courseCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentId, courseCode);
    }

    @Override
    public String toString() {
        return studentId + " " + courseCode;
    }
}

public class EnrollmentSetSystem {
    public static void main(String[] args) {

        Set<Enrollment> enrollments = new HashSet<>();

        Enrollment e1 = new Enrollment("51121", "JAVA");
        Enrollment e2 = new Enrollment("51121", "DB");
        Enrollment e3 = new Enrollment("51121", "JAVA");

        System.out.println("新增 51121 JAVA：" 
                + enrollments.add(e1));

        System.out.println("新增 51121 DB：" 
                + enrollments.add(e2));

        System.out.println("重複新增 51121 JAVA：" 
                + enrollments.add(e3));

        System.out.println();

        System.out.println("目前報名：");
        System.out.println(enrollments);

        Enrollment test = new Enrollment("51121", "JAVA");

        System.out.println();

        System.out.println("contains：" 
                + enrollments.contains(test));

        System.out.println("remove：" 
                + enrollments.remove(test));

        System.out.println();

        System.out.println("取消後：");
        System.out.println(enrollments);
    }
}