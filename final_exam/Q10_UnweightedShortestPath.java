import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class Q10_UnweightedShortestPath {

    public static List<String> shortestPath(Map<String, List<String>> graph, String start, String target) {
        List<String> path = new ArrayList<>();
        if (graph == null || start == null || target == null) return path;
        if (!graph.containsKey(start) || !graph.containsKey(target)) return path;

        if (start.equals(target)) {
            path.add(start);
            return path;
        }

        Queue<String> queue = new ArrayDeque<>();
        Set<String> visited = new HashSet<>();
        Map<String, String> predecessor = new HashMap<>();

        queue.offer(start);
        visited.add(start);
        
        boolean found = false;

        while (!queue.isEmpty()) {
            String curr = queue.poll();
            if (curr.equals(target)) {
                found = true;
                break;
            }

            List<String> neighbors = graph.get(curr);
            if (neighbors != null) {
                for (String neighbor : neighbors) {
                    if (!visited.contains(neighbor) && graph.containsKey(neighbor)) {
                        queue.offer(neighbor);
                        visited.add(neighbor);
                        predecessor.put(neighbor, curr);
                    }
                }
            }
        }

        if (!found) return path;

        String step = target;
        while (step != null) {
            path.add(step);
            step = predecessor.get(step);
        }
        
        Collections.reverse(path);
        return path;
    }

    // 測試用主程式
    public static void main(String[] args) {
        Map<String, List<String>> graph = new HashMap<>();
        graph.put("A", Arrays.asList("B", "C"));
        graph.put("B", Arrays.asList("D"));
        graph.put("C", Arrays.asList("D"));
        graph.put("D", new ArrayList<>());

        System.out.println("Shortest Path A to D: " + shortestPath(graph, "A", "D"));
    }
}