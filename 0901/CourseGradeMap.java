import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class CourseGradeMap {
    // 使用 TreeMap 自動依課號排序
    private Map<String, List<Integer>> gradesMap = new TreeMap<>();

    public void addGrade(String courseCode, int grade) {
        if (courseCode == null) return;
        gradesMap.putIfAbsent(courseCode, new ArrayList<>());
        gradesMap.get(courseCode).add(grade);
    }

    public double getAverage(String courseCode) {
        List<Integer> grades = gradesMap.get(courseCode);
        if (grades == null || grades.isEmpty()) return 0.0;
        int sum = 0;
        for (int g : grades) sum += g;
        return (double) sum / grades.size();
    }

    public int getMax(String courseCode) {
        List<Integer> grades = gradesMap.get(courseCode);
        if (grades == null || grades.isEmpty()) return -1;
        int max = grades.get(0);
        for (int g : grades) {
            if (g > max) max = g;
        }
        return max;
    }

    public void printReport() {
        for (Map.Entry<String, List<Integer>> entry : gradesMap.entrySet()) {
            String course = entry.getKey();
            System.out.println("Course: " + course + " | Avg: " + getAverage(course) + " | Max: " + getMax(course));
        }
    }

    public static void main(String[] args) {
        CourseGradeMap report = new CourseGradeMap();
        report.addGrade("CS101", 85);
        report.addGrade("CS101", 95);
        report.addGrade("MATH200", 70);
        report.printReport(); // Output will be sorted by course code
    }
}