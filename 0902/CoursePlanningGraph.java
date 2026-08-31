import java.util.*;

public class CoursePlanningGraph {
    private Map<String, List<String>> adjList = new HashMap<>();

    public void addPrerequisite(String pre, String course) {
        adjList.putIfAbsent(pre, new ArrayList<>());
        adjList.putIfAbsent(course, new ArrayList<>());
        adjList.get(pre).add(course); // Directed Edge: pre -> course
    }

    public boolean isReachable(String start, String target) {
        if (!adjList.containsKey(start) || !adjList.containsKey(target)) return false;
        Set<String> visited = new HashSet<>();
        return dfsSearch(start, target, visited);
    }

    private boolean dfsSearch(String curr, String target, Set<String> visited) {
        if (curr.equals(target)) return true;
        visited.add(curr);
        for (String neighbor : adjList.get(curr)) {
            if (!visited.contains(neighbor)) {
                if (dfsSearch(neighbor, target, visited)) return true;
            }
        }
        return false;
    }

    public List<String> getAffectedCourses(String course) {
        List<String> affected = new ArrayList<>();
        if (!adjList.containsKey(course)) return affected;
        
        Set<String> visited = new HashSet<>();
        dfsCollect(course, visited, affected);
        affected.remove(course); // 不包含自己
        return affected;
    }

    private void dfsCollect(String curr, Set<String> visited, List<String> affected) {
        visited.add(curr);
        affected.add(curr);
        for (String neighbor : adjList.get(curr)) {
            if (!visited.contains(neighbor)) {
                dfsCollect(neighbor, visited, affected);
            }
        }
    }

    public static void main(String[] args) {
        CoursePlanningGraph cp = new CoursePlanningGraph();
        cp.addPrerequisite("CS101", "CS102");
        cp.addPrerequisite("CS102", "CS201");
        cp.addPrerequisite("CS101", "MATH101");

        System.out.println("CS101 -> CS201 reachable? " + cp.isReachable("CS101", "CS201"));
        System.out.println("CS201 -> CS101 reachable? " + cp.isReachable("CS201", "CS101"));
        System.out.println("Affected by CS101: " + cp.getAffectedCourses("CS101"));
        
        System.out.println("Missing cases: " + cp.isReachable("X", "Y"));
    }
}