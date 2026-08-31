import java.util.ArrayList;
import java.util.List;

public class MetroMatrixGraph {
    private String[] stations;
    private boolean[][] matrix;

    public MetroMatrixGraph(String[] stationNames) {
        this.stations = stationNames.clone();
        int n = stations.length;
        this.matrix = new boolean[n][n];
    }

    private int getIndex(String station) {
        if (station == null) return -1;
        for (int i = 0; i < stations.length; i++) {
            if (stations[i].equals(station)) return i;
        }
        return -1;
    }

    public void addEdge(String s1, String s2) {
        int i = getIndex(s1);
        int j = getIndex(s2);
        if (i != -1 && j != -1) {
            matrix[i][j] = true;
            matrix[j][i] = true; // Undirected 對稱
        }
    }

    public List<String> getNeighbors(String station) {
        List<String> neighbors = new ArrayList<>();
        int i = getIndex(station);
        if (i != -1) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j]) neighbors.add(stations[j]);
            }
        }
        return neighbors;
    }

    public int getDegree(String station) {
        return getNeighbors(station).size();
    }

    public int getEdgeCount() {
        int count = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j]) count++;
            }
        }
        return count / 2; // Undirected sum 必須除以 2
    }

    public void matrixReport() {
        System.out.print("   ");
        for (String s : stations) System.out.print(s + " ");
        System.out.println();
        for (int i = 0; i < matrix.length; i++) {
            System.out.print(stations[i] + " ");
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print((matrix[i][j] ? "1" : "0") + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        MetroMatrixGraph mg = new MetroMatrixGraph(new String[]{"A", "B", "C"});
        mg.addEdge("A", "B");
        mg.addEdge("B", "C");
        System.out.println("Degree of B: " + mg.getDegree("B"));
        System.out.println("Total Edges: " + mg.getEdgeCount());
        mg.matrixReport();
    }
}