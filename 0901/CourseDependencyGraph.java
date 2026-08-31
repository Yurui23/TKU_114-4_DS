import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CourseDependencyGraph {
    private Map<String, Set<String>> outgoing = new HashMap<>(); // 後續課程
    private Map<String, Set<String>> incoming = new HashMap<>(); // 擋修課程 (Prerequisites)

    public void addCourse(String course) {
        outgoing.putIfAbsent(course, new HashSet<>());
        incoming.putIfAbsent(course, new HashSet<>());
    }

    public void addDependency(String prereq, String course) {
        addCourse(prereq);
        addCourse(course);
        outgoing.get(prereq).add(course);
        incoming.get(course).add(prereq);
    }

    public List<String> getPrerequisites(String course) {
        return new ArrayList<>(incoming.getOrDefault(course, new HashSet<>()));
    }

    public List<String> getSubsequentCourses(String course) {
        return new ArrayList<>(outgoing.getOrDefault(course, new HashSet<>()));
    }

    public int getInDegree(String course) {
        return incoming.getOrDefault(course, new HashSet<>()).size();
    }

    public int getOutDegree(String course) {
        return outgoing.getOrDefault(course, new HashSet<>()).size();
    }

    public static void main(String[] args) {
        CourseDependencyGraph cd = new CourseDependencyGraph();
        cd.addDependency("CS101", "CS102");
        cd.addDependency("MATH101", "CS102");
        System.out.println("Prerequisites of CS102: " + cd.getPrerequisites("CS102"));
        System.out.println("Out-degree of CS101: " + cd.getOutDegree("CS101"));
    }
}