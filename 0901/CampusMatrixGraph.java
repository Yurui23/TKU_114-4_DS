import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CampusMatrixGraph {
    private List<String> vertices = new ArrayList<>();
    private Map<String, Integer> vMap = new HashMap<>();
    private List<List<Integer>> matrix = new ArrayList<>();

    public void addVertex(String v) {
        if (!vMap.containsKey(v)) {
            vMap.put(v, vertices.size());
            vertices.add(v);
            for (List<Integer> row : matrix) {
                row.add(0);
            }
            List<Integer> newRow = new ArrayList<>();
            for (int i = 0; i < vertices.size(); i++) newRow.add(0);
            matrix.add(newRow);
        }
    }

    public void addEdge(String u, String v) {
        addVertex(u); addVertex(v);
        int i = vMap.get(u), j = vMap.get(v);
        matrix.get(i).set(j, 1);
        matrix.get(j).set(i, 1); // Undirected 對稱
    }

    public void removeEdge(String u, String v) {
        if (vMap.containsKey(u) && vMap.containsKey(v)) {
            int i = vMap.get(u), j = vMap.get(v);
            matrix.get(i).set(j, 0);
            matrix.get(j).set(i, 0);
        }
    }

    public int getDegree(String u) {
        if (!vMap.containsKey(u)) return 0;
        int i = vMap.get(u), deg = 0;
        for (int val : matrix.get(i)) {
            if (val == 1) deg++;
        }
        return deg;
    }

    public List<String> getNeighbors(String u) {
        List<String> neighbors = new ArrayList<>();
        if (!vMap.containsKey(u)) return neighbors;
        int i = vMap.get(u);
        for (int j = 0; j < matrix.get(i).size(); j++) {
            if (matrix.get(i).get(j) == 1) {
                neighbors.add(vertices.get(j));
            }
        }
        return neighbors;
    }

    public int getEdgeCount() {
        int sum = 0;
        for (List<Integer> row : matrix) {
            for (int val : row) {
                if (val == 1) sum++;
            }
        }
        return sum / 2; // Undirected degree sum / 2
    }

    public static void main(String[] args) {
        CampusMatrixGraph graph = new CampusMatrixGraph();
        graph.addEdge("Library", "Dorm");
        graph.addEdge("Library", "Cafeteria");
        graph.addEdge("Library", "Dorm"); // 重複邊測試
        System.out.println("Degree of Library: " + graph.getDegree("Library"));
        System.out.println("Edge Count: " + graph.getEdgeCount());
    }
}