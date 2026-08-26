import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class Q06_EnrollmentIndex {
    private final Map<String, Set<String>> courseMap;

    public Q06_EnrollmentIndex() {
        courseMap = new HashMap<>();
    }

    public boolean enroll(String courseCode, String studentId) {
        if (courseCode == null || courseCode.trim().isEmpty() || studentId == null || studentId.trim().isEmpty()) {
            return false;
        }
        courseMap.putIfAbsent(courseCode, new HashSet<>());
        return courseMap.get(courseCode).add(studentId);
    }

    public boolean drop(String courseCode, String studentId) {
        if (courseCode == null || courseCode.trim().isEmpty() || studentId == null || studentId.trim().isEmpty()) {
            return false;
        }
        if (courseMap.containsKey(courseCode)) {
            boolean removed = courseMap.get(courseCode).remove(studentId);
            if (courseMap.get(courseCode).isEmpty()) {
                courseMap.remove(courseCode);
            }
            return removed;
        }
        return false;
    }

    public int courseSize(String courseCode) {
        if (courseCode != null && courseMap.containsKey(courseCode)) {
            return courseMap.get(courseCode).size();
        }
        return 0;
    }

    public List<String> studentsOf(String courseCode) {
        if (courseCode == null || !courseMap.containsKey(courseCode)) {
            return new ArrayList<>();
        }
        List<String> students = new ArrayList<>(courseMap.get(courseCode));
        Collections.sort(students);
        return students;
    }

    public List<String> coursesOf(String studentId) {
        List<String> courses = new ArrayList<>();
        if (studentId == null || studentId.trim().isEmpty()) return courses;
        
        for (Map.Entry<String, Set<String>> entry : courseMap.entrySet()) {
            if (entry.getValue().contains(studentId)) {
                courses.add(entry.getKey());
            }
        }
        Collections.sort(courses);
        return courses;
    }

    public Map<String, Integer> summary() {
        Map<String, Integer> result = new TreeMap<>();
        for (Map.Entry<String, Set<String>> entry : courseMap.entrySet()) {
            result.put(entry.getKey(), entry.getValue().size());
        }
        return result;
    }
}