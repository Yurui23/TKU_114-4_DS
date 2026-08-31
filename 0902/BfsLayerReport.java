import java.util.*;

public class BfsLayerReport {
    private Map<String, List<String>> adjList = new LinkedHashMap<>();

    public void addVertex(String v) {
        adjList.putIfAbsent(v, new ArrayList<>());
    }

    public void addEdge(String u, String v) {
        addVertex(u);
        addVertex(v);
        adjList.get(u).add(v);
        adjList.get(v).add(u);
    }

    public Map<String, Integer> getLayerReport(String start) {
        Map<String, Integer> distances = new LinkedHashMap<>();
        if (start == null || !adjList.containsKey(start)) return distances;

        Queue<String> queue = new ArrayDeque<>();
        Set<String> visited = new HashSet<>();

        // 規則：offer 時立即標記
        queue.offer(start);
        visited.add(start);
        distances.put(start, 0);

        while (!queue.isEmpty()) {
            String curr = queue.poll();
            int currentDist = distances.get(curr);

            for (String neighbor : adjList.get(curr)) {
                if (!visited.contains(neighbor)) {
                    queue.offer(neighbor);
                    visited.add(neighbor); // 規則：offer 時立即標記
                    distances.put(neighbor, currentDist + 1);
                }
            }
        }
        return distances;
    }

    public static void main(String[] args) {
        BfsLayerReport graph = new BfsLayerReport();
        graph.addEdge("A", "B");
        graph.addEdge("A", "C");
        graph.addEdge("B", "D");
        graph.addVertex("Isolated"); // 孤立節點測試

        System.out.println("一般案例 (Start A): " + graph.getLayerReport("A"));
        System.out.println("Missing 案例 (Start Z): " + graph.getLayerReport("Z"));
        System.out.println("Empty 案例: " + new BfsLayerReport().getLayerReport("A"));
    }
}