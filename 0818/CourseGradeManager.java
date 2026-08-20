class CourseGrade {
    private String id;
    private String name;
    private double usual;
    private double midterm;
    private double finalExam;
    private double attend;

    public CourseGrade(String id, String name, double usual,
            double midterm, double finalExam, double attend) {

        this.id = id;
        this.name = name;

        if (usual >= 0 && usual <= 100) {
            this.usual = usual;
        }

        if (midterm >= 0 && midterm <= 100) {
            this.midterm = midterm;
        }

        if (finalExam >= 0 && finalExam <= 100) {
            this.finalExam = finalExam;
        }

        if (attend >= 0 && attend <= 100) {
            this.attend = attend;
        }
    }

    double calculateFinalScore() {
        double score = 0;

        score += usual * 0.5;
        score += midterm * 0.2;
        score += finalExam * 0.2;
        score += attend * 0.1;

        return score;
    }

    String getLevel() {
        double score = calculateFinalScore();

        if (score >= 90) {
            return "A";
        } else if (score >= 80) {
            return "B";
        } else if (score >= 70) {
            return "C";
        } else if (score >= 60) {
            return "D";
        } else {
            return "F";
        }
    }

    String getId() {
        return id;
    }

    String getName() {
        return name;
    }

    public String toString() {
        return String.format(
                "學號：%s, 姓名：%s, 平時：%.1f, 期中：%.1f, 期末：%.1f, 出席：%.1f, 總分：%.1f, 等級：%s",
                id, name, usual, midterm, finalExam,
                attend, calculateFinalScore(), getLevel());
    }
}

public class CourseGradeManager {
    public static void main(String[] args) {
        CourseGrade[] grades = new CourseGrade[5];

        grades[0] = new CourseGrade("111", "Eric", 90, 85, 88, 100);
        grades[1] = new CourseGrade("112", "Amy", 75, 80, 70, 90);
        grades[2] = new CourseGrade("113", "John", 60, 55, 50, 80);
        grades[3] = new CourseGrade("114", "Mary", 95, 92, 90, 100);
        grades[4] = new CourseGrade("115", "David", 50, 45, 40, 70);

        System.out.println("===== 課程成績 =====");

        double total = 0;
        double highest = grades[0].calculateFinalScore();
        String highestName = grades[0].getName();

        for (int i = 0; i < grades.length; i++) {
            System.out.println(grades[i]);

            double score = grades[i].calculateFinalScore();

            total += score;

            if (score > highest) {
                highest = score;
                highestName = grades[i].getName();
            }
        }

        double average = total / grades.length;

        System.out.println("\n===== 成績統計 =====");
        System.out.println("平均分：" + average);
        System.out.println("最高分：" + highest);
        System.out.println("最高分學生：" + highestName);

        System.out.println("\n===== 不及格名單 =====");

        for (int i = 0; i < grades.length; i++) {
            if (grades[i].calculateFinalScore() < 60) {
                System.out.println(
                        grades[i].getId() + " "
                                + grades[i].getName()
                                + "：" + grades[i].calculateFinalScore());
            }
        }
    }
}