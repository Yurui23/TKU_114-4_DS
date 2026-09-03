import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Q07_AdjacencyListGraph {
    private Map<String, Set<String>> adjList = new HashMap<>();
    private int edges = 0;

    public boolean addVertex(String vertex) {
        if (vertex == null || adjList.containsKey(vertex)) {
            return false;
        }
        adjList.put(vertex, new LinkedHashSet<>());
        return true;
    }

    public boolean addEdge(String from, String to) {
        if (from == null || to == null || from.equals(to)) return false;
        if (!adjList.containsKey(from) || !adjList.containsKey(to)) return false;
        
        if (adjList.get(from).add(to)) {
            edges++;
            return true;
        }
        return false;
    }

    public boolean removeEdge(String from, String to) {
        if (from == null || to == null) return false;
        if (!adjList.containsKey(from) || !adjList.containsKey(to)) return false;
        
        if (adjList.get(from).remove(to)) {
            edges--;
            return true;
        }
        return false;
    }

    public List<String> outgoing(String vertex) {
        if (vertex == null || !adjList.containsKey(vertex)) {
            return new ArrayList<>();
        }
        return new ArrayList<>(adjList.get(vertex));
    }

    public int inDegree(String vertex) {
        if (vertex == null || !adjList.containsKey(vertex)) {
            return 0;
        }
        int count = 0;
        for (Set<String> outEdges : adjList.values()) {
            if (outEdges.contains(vertex)) {
                count++;
            }
        }
        return count;
    }

    public int edgeCount() {
        return edges;
    }

    // 測試用主程式
    public static void main(String[] args) {
        Q07_AdjacencyListGraph graph = new Q07_AdjacencyListGraph();
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");
        graph.addEdge("A", "B");
        graph.addEdge("A", "C");
        
        System.out.println("Outgoing from A: " + graph.outgoing("A"));
        System.out.println("In-degree of C: " + graph.inDegree("C"));
        System.out.println("Total Edges: " + graph.edgeCount());
    }
}