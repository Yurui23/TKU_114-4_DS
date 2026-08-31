import java.util.HashMap;
import java.util.Map;

public class LogisticsWeightedGraph {
    // Directed graph: Source -> (Destination -> Weight)
    private Map<String, Map<String, Integer>> adjList = new HashMap<>();

    public void addVertex(String v) {
        if (v != null) adjList.putIfAbsent(v, new HashMap<>());
    }

    public boolean addEdge(String from, String to, int weight) {
        if (weight < 0 || !adjList.containsKey(from) || !adjList.containsKey(to)) {
            return false; // 拒絕負權重及不存在 vertex
        }
        adjList.get(from).put(to, weight);
        return true;
    }

    public boolean updateEdge(String from, String to, int weight) {
        if (weight < 0 || !adjList.containsKey(from) || !adjList.containsKey(to)) return false;
        if (adjList.get(from).containsKey(to)) {
            adjList.get(from).put(to, weight);
            return true;
        }
        return false;
    }

    public boolean removeEdge(String from, String to) {
        if (adjList.containsKey(from) && adjList.get(from).containsKey(to)) {
            adjList.get(from).remove(to);
            return true;
        }
        return false;
    }

    public int getWeight(String from, String to) {
        if (adjList.containsKey(from) && adjList.get(from).containsKey(to)) {
            return adjList.get(from).get(to);
        }
        return -1; // -1 表示 Edge 不存在
    }

    public static void main(String[] args) {
        LogisticsWeightedGraph lg = new LogisticsWeightedGraph();
        lg.addVertex("HubA");
        lg.addVertex("HubB");
        System.out.println("Add Valid Edge: " + lg.addEdge("HubA", "HubB", 50));
        System.out.println("Add Negative Weight: " + lg.addEdge("HubA", "HubB", -10)); // Should be false
        System.out.println("Add Missing Vertex: " + lg.addEdge("HubA", "HubC", 10)); // Should be false
        System.out.println("Get Weight: " + lg.getWeight("HubA", "HubB"));
    }
}