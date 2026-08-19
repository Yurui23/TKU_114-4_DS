import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

class Enrollment {
    private String studentId;
    private String courseCode;

    public Enrollment(String studentId, String courseCode) {
        this.studentId = studentId;
        this.courseCode = courseCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Enrollment that = (Enrollment) o;
        return Objects.equals(studentId, that.studentId) &&
               Objects.equals(courseCode, that.courseCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentId, courseCode);
    }

    @Override
    public String toString() {
        return studentId + "-" + courseCode;
    }
}

public class EnrollmentSetSystem {
    public static void main(String[] args) {
        Set<Enrollment> enrollments = new HashSet<>();

        System.out.println("--- 測試新增 ---");
        boolean add1 = enrollments.add(new Enrollment("S001", "CS101"));
        System.out.println("S001 加入 CS101: " + add1);

        boolean add2 = enrollments.add(new Enrollment("S001", "MA200"));
        System.out.println("S001 加入 MA200: " + add2);

        boolean add3 = enrollments.add(new Enrollment("S001", "CS101"));
        System.out.println("S001 重複加入 CS101: " + add3);

        boolean add4 = enrollments.add(new Enrollment("S002", "CS101"));
        System.out.println("S002 加入 CS101: " + add4);

        System.out.println("\n目前報名資料: " + enrollments);

        System.out.println("\n--- 測試相同身分的全新 Object ---");
        Enrollment testObj = new Enrollment("S001", "CS101");
        
        boolean containsTest = enrollments.contains(testObj);
        System.out.println("包含 testObj (S001-CS101): " + containsTest);

        boolean removeTest = enrollments.remove(testObj);
        System.out.println("移除 testObj (S001-CS101): " + removeTest);

        System.out.println("\n移除後報名資料: " + enrollments);
    }
}