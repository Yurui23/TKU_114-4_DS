import java.util.*;

public class DirectedReachability {
    private Map<String, List<String>> adjList = new LinkedHashMap<>();

    public void addDirectedEdge(String from, String to) {
        adjList.putIfAbsent(from, new ArrayList<>());
        adjList.putIfAbsent(to, new ArrayList<>());
        adjList.get(from).add(to); // 有向圖
    }

    public boolean isReachable(String from, String to) {
        if (from == null || to == null || !adjList.containsKey(from) || !adjList.containsKey(to)) {
            return false;
        }
        if (from.equals(to)) return true;

        Queue<String> queue = new ArrayDeque<>();
        Set<String> visited = new HashSet<>();

        queue.offer(from);
        visited.add(from); // offer 時標記

        while (!queue.isEmpty()) {
            String curr = queue.poll();
            if (curr.equals(to)) return true;

            for (String neighbor : adjList.get(curr)) {
                if (!visited.contains(neighbor)) {
                    queue.offer(neighbor);
                    visited.add(neighbor);
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        DirectedReachability graph = new DirectedReachability();
        graph.addDirectedEdge("A", "B");
        graph.addDirectedEdge("B", "C");
        graph.addDirectedEdge("D", "A");

        System.out.println("A -> C reachable? " + graph.isReachable("A", "C")); // true
        System.out.println("C -> A reachable? " + graph.isReachable("C", "A")); // false
        System.out.println("Missing 邊界 (X -> Y)? " + graph.isReachable("X", "Y")); // false
    }
}