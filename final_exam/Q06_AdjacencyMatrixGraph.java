import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Q06_AdjacencyMatrixGraph {
    private List<String> vertices;
    private Map<String, Integer> vMap;
    private boolean[][] matrix;

    public Q06_AdjacencyMatrixGraph(List<String> vertices) {
        this.vertices = new ArrayList<>();
        this.vMap = new HashMap<>();
        
        if (vertices != null) {
            for (String v : vertices) {
                if (v != null && !vMap.containsKey(v)) {
                    vMap.put(v, this.vertices.size());
                    this.vertices.add(v);
                }
            }
        }
        
        int n = this.vertices.size();
        matrix = new boolean[n][n];
    }

    private int getIndex(String vertex) {
        if (vertex == null) return -1;
        return vMap.getOrDefault(vertex, -1);
    }

    public boolean addEdge(String first, String second) {
        int u = getIndex(first);
        int v = getIndex(second);
        
        if (u == -1 || v == -1 || u == v || matrix[u][v]) {
            return false;
        }
        
        matrix[u][v] = true;
        matrix[v][u] = true;
        return true;
    }

    public boolean removeEdge(String first, String second) {
        int u = getIndex(first);
        int v = getIndex(second);
        
        if (u == -1 || v == -1 || !matrix[u][v]) {
            return false;
        }
        
        matrix[u][v] = false;
        matrix[v][u] = false;
        return true;
    }

    public boolean hasEdge(String first, String second) {
        int u = getIndex(first);
        int v = getIndex(second);
        if (u == -1 || v == -1) return false;
        return matrix[u][v];
    }

    public int degree(String vertex) {
        int u = getIndex(vertex);
        if (u == -1) return -1; 
        
        int deg = 0;
        for (int i = 0; i < vertices.size(); i++) {
            if (matrix[u][i]) deg++;
        }
        return deg;
    }

    public List<String> neighbors(String vertex) {
        List<String> result = new ArrayList<>();
        int u = getIndex(vertex);
        if (u == -1) return result;
        
        for (int i = 0; i < vertices.size(); i++) {
            if (matrix[u][i]) {
                result.add(vertices.get(i));
            }
        }
        return result;
    }

    // 測試用主程式
    public static void main(String[] args) {
        Q06_AdjacencyMatrixGraph graph = new Q06_AdjacencyMatrixGraph(Arrays.asList("A", "B", "C"));
        graph.addEdge("A", "B");
        System.out.println("Has edge A-B: " + graph.hasEdge("A", "B"));
        System.out.println("Degree of B: " + graph.degree("B"));
        System.out.println("Neighbors of A: " + graph.neighbors("A"));
    }
}