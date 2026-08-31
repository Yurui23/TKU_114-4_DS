import java.util.*;

public class NetworkComponents {
    private Map<String, List<String>> adjList = new LinkedHashMap<>();

    // 規則：依全部 vertex 啟動搜尋，支援 isolated vertex
    public void addVertex(String v) {
        adjList.putIfAbsent(v, new ArrayList<>());
    }

    public void addEdge(String u, String v) {
        addVertex(u);
        addVertex(v);
        adjList.get(u).add(v);
        adjList.get(v).add(u);
    }

    public void reportComponents() {
        Set<String> visited = new HashSet<>();
        List<List<String>> components = new ArrayList<>();
        int maxSize = 0;
        List<String> maxComponent = new ArrayList<>();

        // 掃描全部 Vertex
        for (String node : adjList.keySet()) {
            if (!visited.contains(node)) {
                List<String> comp = new ArrayList<>();
                bfs(node, visited, comp);
                components.add(comp);
                
                if (comp.size() > maxSize) {
                    maxSize = comp.size();
                    maxComponent = comp;
                }
            }
        }

        System.out.println("Components: " + components);
        System.out.println("Component count: " + components.size());
        System.out.println("Max component: " + maxComponent + " (Size: " + maxSize + ")");
    }

    private void bfs(String start, Set<String> visited, List<String> comp) {
        Queue<String> queue = new ArrayDeque<>();
        queue.offer(start);
        visited.add(start);

        while (!queue.isEmpty()) {
            String curr = queue.poll();
            comp.add(curr);

            for (String neighbor : adjList.get(curr)) {
                if (!visited.contains(neighbor)) {
                    queue.offer(neighbor);
                    visited.add(neighbor);
                }
            }
        }
    }

    public static void main(String[] args) {
        NetworkComponents net = new NetworkComponents();
        net.addEdge("A", "B");
        net.addEdge("C", "D");
        net.addEdge("D", "E");
        net.addVertex("Isolated"); // 測試 isolated vertex

        net.reportComponents();
        
        System.out.println("\nEmpty 案例:");
        new NetworkComponents().reportComponents();
    }
}