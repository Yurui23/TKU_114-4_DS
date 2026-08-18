class CourseGrade {
    private String studentId;
    private String name;
    private int assignmentScore; // 平時成績 (50%)
    private int midtermScore;    // 期中成績 (20%)
    private int finalScore;      // 期末成績 (20%)
    private int attendanceScore; // 出席成績 (10%)

    public CourseGrade(String studentId, String name, int assignmentScore, int midtermScore, int finalScore, int attendanceScore) {
        this.studentId = (studentId == null) ? "UNKNOWN" : studentId.trim();
        this.name = (name == null) ? "UNKNOWN" : name.trim();
        this.assignmentScore = clampScore(assignmentScore);
        this.midtermScore = clampScore(midtermScore);
        this.finalScore = clampScore(finalScore);
        this.attendanceScore = clampScore(attendanceScore);
    }

    private int clampScore(int score) {
        if (score < 0) return 0;
        if (score > 100) return 100;
        return score;
    }

    public double calculateFinalScore() {
        return (assignmentScore * 0.50) + (midtermScore * 0.20) + (finalScore * 0.20) + (attendanceScore * 0.10);
    }

    public String getLevel() {
        double score = calculateFinalScore();
        if (score >= 90.0) return "A";
        if (score >= 80.0) return "B";
        if (score >= 70.0) return "C";
        if (score >= 60.0) return "D";
        return "F";
    }

    public String getStudentId() { return studentId; }
    public String getName() { return name; }

    @Override
    public String toString() {
        return String.format("學號: %s | 姓名: %-4s | 平時: %3d | 期中: %3d | 期末: %3d | 出席: %3d | 總分: %5.1f | 等級: %s",
                studentId, name, assignmentScore, midtermScore, finalScore, attendanceScore, calculateFinalScore(), getLevel());
    }
}

public class CourseGradeManager {
    public static void main(String[] args) {
        System.out.println("=== 課後作業三：課程成績物件系統 ===");

        CourseGrade[] students = {
            new CourseGrade("S001", "陳小華", 85, 90, 88, 100),
            new CourseGrade("S002", "林大同", 45, 50, 40, 60),
            new CourseGrade("S003", "黃美玲", 95, 92, 96, 90),
            new CourseGrade("S004", "趙子龍", 60, 55, 65, 70),
            new CourseGrade("S005", "孫悟空", 30, 20, 10, 50)
        };

        System.out.println("--- 1. 所有學生成績列表 ---");
        double sum = 0;
        CourseGrade topStudent = students[0];

        for (CourseGrade student : students) {
            System.out.println(student);
            double score = student.calculateFinalScore();
            sum += score;
            if (score > topStudent.calculateFinalScore()) {
                topStudent = student;
            }
        }

        double average = sum / students.length;
        System.out.println("\n----------------------------------------");
        System.out.println(String.format("全班平均成績: %.2f 分", average));

        System.out.println("\n----------------------------------------");
        System.out.println("最高分學生:");
        System.out.println(topStudent);

        System.out.println("\n----------------------------------------");
        System.out.println("不及格名單 (總分 < 60):");
        for (CourseGrade student : students) {
            if (student.calculateFinalScore() < 60.0) {
                System.out.println(" - " + student.getName() + " (學號: " + student.getStudentId() + ", 總分: " + String.format("%.1f", student.calculateFinalScore()) + ")");
            }
        }
    }
}