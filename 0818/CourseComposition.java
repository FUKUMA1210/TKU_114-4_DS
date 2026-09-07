class Instructor {
    private String id;
    private String name;

    public Instructor(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getInfo() {
        return id + " " + name;
    }
}

class Course {
    private String courseCode;
    private String title;
    private Instructor instructor;

    public Course(String courseCode, String title, Instructor instructor) {
        this.courseCode = courseCode;
        this.title = title;
        this.instructor = instructor;
    }

    public String summary() {
        return "課程代碼：" + courseCode
                + "，課程名稱：" + title
                + "，授課老師：" + instructor.getInfo();
    }
}

public class CourseComposition {
    public static void main(String[] args) {
        Instructor instructor = new Instructor("T001", "AAA");

        Course course1 = new Course(
                "CS101",
                "Java",
                instructor
        );

        Course course2 = new Course(
                "CS102",
                "資料結構",
                instructor
        );

        System.out.println(course1.summary());
        System.out.println(course2.summary());
    }
}