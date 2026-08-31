import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

class EnrollmentKey {
    String studentId;
    String courseId;
    EnrollmentKey(String studentId, String courseId) {
        this.studentId = studentId;
        this.courseId = courseId;
    }
    
    // Equality 必須與 hash 一致
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EnrollmentKey that = (EnrollmentKey) o;
        return Objects.equals(studentId, that.studentId) && Objects.equals(courseId, that.courseId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentId, courseId);
    }
}

public class EnrollmentConflictSet {
    public static void processEnrollments(List<EnrollmentKey> records) {
        Set<EnrollmentKey> uniqueSet = new HashSet<>();
        List<String> conflicts = new ArrayList<>();
        Map<String, List<String>> studentCourses = new HashMap<>();
        Map<String, Integer> courseCounts = new HashMap<>();

        if (records != null) {
            for (EnrollmentKey r : records) {
                if (!uniqueSet.add(r)) {
                    conflicts.add(r.studentId + "-" + r.courseId);
                } else {
                    studentCourses.putIfAbsent(r.studentId, new ArrayList<>());
                    studentCourses.get(r.studentId).add(r.courseId);
                    courseCounts.put(r.courseId, courseCounts.getOrDefault(r.courseId, 0) + 1);
                }
            }
        }

        Collections.sort(conflicts); // 排序輸出
        System.out.println("重複紀錄: " + conflicts);
        
        System.out.println("每人課程集合: ");
        for (Map.Entry<String, List<String>> entry : studentCourses.entrySet()) {
            Collections.sort(entry.getValue());
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }

        System.out.println("每門課修課人數: " + courseCounts);
    }

    public static void main(String[] args) {
        List<EnrollmentKey> list = new ArrayList<>();
        list.add(new EnrollmentKey("S1", "C1"));
        list.add(new EnrollmentKey("S1", "C2"));
        list.add(new EnrollmentKey("S1", "C1")); // Conflict
        processEnrollments(list);
    }
}