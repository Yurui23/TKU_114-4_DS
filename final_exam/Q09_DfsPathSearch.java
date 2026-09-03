import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Q09_DfsPathSearch {

    public static List<String> dfs(Map<String, List<String>> graph, String start) {
        List<String> result = new ArrayList<>();
        if (graph == null || start == null || !graph.containsKey(start)) {
            return result;
        }
        
        Set<String> visited = new HashSet<>();
        dfsHelper(graph, start, visited, result);
        return result;
    }

    private static void dfsHelper(Map<String, List<String>> graph, String curr, Set<String> visited, List<String> result) {
        visited.add(curr);
        result.add(curr);
        
        List<String> neighbors = graph.get(curr);
        if (neighbors != null) {
            for (String neighbor : neighbors) {
                if (!visited.contains(neighbor) && graph.containsKey(neighbor)) {
                    dfsHelper(graph, neighbor, visited, result);
                }
            }
        }
    }

    public static boolean reachable(Map<String, List<String>> graph, String start, String target) {
        if (graph == null || start == null || target == null) return false;
        if (!graph.containsKey(start) || !graph.containsKey(target)) return false;
        
        Set<String> visited = new HashSet<>();
        return reachableHelper(graph, start, target, visited);
    }

    private static boolean reachableHelper(Map<String, List<String>> graph, String curr, String target, Set<String> visited) {
        if (curr.equals(target)) return true;
        
        visited.add(curr);
        List<String> neighbors = graph.get(curr);
        
        if (neighbors != null) {
            for (String neighbor : neighbors) {
                if (!visited.contains(neighbor) && graph.containsKey(neighbor)) {
                    if (reachableHelper(graph, neighbor, target, visited)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    // 測試用主程式
    public static void main(String[] args) {
        Map<String, List<String>> graph = new HashMap<>();
        graph.put("A", Arrays.asList("B", "C"));
        graph.put("B", Arrays.asList("D"));
        graph.put("C", Arrays.asList());
        graph.put("D", Arrays.asList());

        System.out.println("DFS: " + dfs(graph, "A"));
        System.out.println("A to D Reachable: " + reachable(graph, "A", "D"));
        System.out.println("C to B Reachable: " + reachable(graph, "C", "B"));
    }
}