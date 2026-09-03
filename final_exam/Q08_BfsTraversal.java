import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class Q08_BfsTraversal {
    
    public static List<String> bfs(Map<String, List<String>> graph, String start) {
        List<String> result = new ArrayList<>();
        if (graph == null || start == null || !graph.containsKey(start)) {
            return result;
        }

        Queue<String> queue = new ArrayDeque<>();
        Set<String> visited = new HashSet<>();

        queue.offer(start);
        visited.add(start);

        while (!queue.isEmpty()) {
            String curr = queue.poll();
            result.add(curr);

            List<String> neighbors = graph.get(curr);
            if (neighbors != null) {
                for (String neighbor : neighbors) {
                    if (!visited.contains(neighbor) && graph.containsKey(neighbor)) {
                        queue.offer(neighbor);
                        visited.add(neighbor);
                    }
                }
            }
        }
        return result;
    }

    public static Map<String, Integer> distanceFrom(Map<String, List<String>> graph, String start) {
        Map<String, Integer> distances = new HashMap<>();
        if (graph == null || start == null || !graph.containsKey(start)) {
            return distances;
        }

        Queue<String> queue = new ArrayDeque<>();
        Set<String> visited = new HashSet<>();

        queue.offer(start);
        visited.add(start);
        distances.put(start, 0);

        while (!queue.isEmpty()) {
            String curr = queue.poll();
            int dist = distances.get(curr);

            List<String> neighbors = graph.get(curr);
            if (neighbors != null) {
                for (String neighbor : neighbors) {
                    if (!visited.contains(neighbor) && graph.containsKey(neighbor)) {
                        queue.offer(neighbor);
                        visited.add(neighbor);
                        distances.put(neighbor, dist + 1);
                    }
                }
            }
        }
        return distances;
    }

    // 測試用主程式
    public static void main(String[] args) {
        Map<String, List<String>> graph = new HashMap<>();
        graph.put("A", Arrays.asList("B", "C"));
        graph.put("B", Arrays.asList("D"));
        graph.put("C", Arrays.asList("D"));
        graph.put("D", new ArrayList<>());

        System.out.println("BFS Traversal: " + bfs(graph, "A"));
        System.out.println("Distances from A: " + distanceFrom(graph, "A"));
    }
}