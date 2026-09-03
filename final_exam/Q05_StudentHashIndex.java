import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Q05_StudentHashIndex {
    private Map<String, Set<String>> studentToCourses = new HashMap<>();
    private Map<String, Set<String>> courseToStudents = new HashMap<>();
    private int enrollmentCount = 0;

    private String normalize(String str) {
        if (str == null || str.trim().isEmpty()) return null;
        return str.trim().toUpperCase();
    }

    public boolean enroll(String studentId, String courseId) {
        String sId = normalize(studentId);
        String cId = normalize(courseId);
        if (sId == null || cId == null) return false;

        studentToCourses.putIfAbsent(sId, new HashSet<>());
        if (!studentToCourses.get(sId).add(cId)) {
            return false;
        }

        courseToStudents.putIfAbsent(cId, new HashSet<>());
        courseToStudents.get(cId).add(sId);
        
        enrollmentCount++;
        return true;
    }

    public boolean drop(String studentId, String courseId) {
        String sId = normalize(studentId);
        String cId = normalize(courseId);
        if (sId == null || cId == null) return false;

        if (studentToCourses.containsKey(sId) && studentToCourses.get(sId).remove(cId)) {
            if (studentToCourses.get(sId).isEmpty()) {
                studentToCourses.remove(sId);
            }
            
            if (courseToStudents.containsKey(cId)) {
                courseToStudents.get(cId).remove(sId);
                if (courseToStudents.get(cId).isEmpty()) {
                    courseToStudents.remove(cId);
                }
            }
            
            enrollmentCount--;
            return true;
        }
        return false;
    }

    public Set<String> coursesOf(String studentId) {
        String sId = normalize(studentId);
        if (sId == null || !studentToCourses.containsKey(sId)) {
            return Collections.emptySet();
        }
        return Collections.unmodifiableSet(new HashSet<>(studentToCourses.get(sId)));
    }

    public Set<String> studentsIn(String courseId) {
        String cId = normalize(courseId);
        if (cId == null || !courseToStudents.containsKey(cId)) {
            return Collections.emptySet();
        }
        return Collections.unmodifiableSet(new HashSet<>(courseToStudents.get(cId)));
    }

    public int enrollmentCount() {
        return enrollmentCount;
    }

    // 測試用主程式
    public static void main(String[] args) {
        Q05_StudentHashIndex index = new Q05_StudentHashIndex();
        index.enroll(" S01 ", "cs101");
        index.enroll("s02", "CS101");
        
        System.out.println("Students in CS101: " + index.studentsIn("cs101"));
        System.out.println("Courses of S01: " + index.coursesOf("S01"));
        System.out.println("Total Enrollments: " + index.enrollmentCount());
    }
}