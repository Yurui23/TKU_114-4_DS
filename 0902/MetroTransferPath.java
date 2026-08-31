import java.util.*;

public class MetroTransferPath {
    private Map<String, List<String>> adjList = new LinkedHashMap<>();

    public void addConnection(String s1, String s2) {
        adjList.putIfAbsent(s1, new ArrayList<>());
        adjList.putIfAbsent(s2, new ArrayList<>());
        adjList.get(s1).add(s2);
        adjList.get(s2).add(s1);
    }

    public void printShortestPath(String start, String target) {
        if (!adjList.containsKey(start) || !adjList.containsKey(target)) {
            System.out.println("站點不存在 (Path: [], Edges: 0)");
            return;
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

            for (String neighbor : adjList.get(curr)) {
                if (!visited.contains(neighbor)) {
                    queue.offer(neighbor);
                    visited.add(neighbor); // offer 時標記
                    predecessor.put(neighbor, curr);
                }
            }
        }

        // 規則：未到達傳回 empty list
        List<String> path = new ArrayList<>();
        if (!found) {
            System.out.println("無法到達 (Path: [], Edges: 0)");
            return;
        }

        // 規則：回溯後 reverse
        String step = target;
        while (step != null) {
            path.add(step);
            step = predecessor.get(step);
        }
        Collections.reverse(path); 

        System.out.println("Path: " + path + ", Edge count: " + (path.size() - 1));
    }

    public static void main(String[] args) {
        MetroTransferPath metro = new MetroTransferPath();
        metro.addConnection("TPE", "Zhongshan");
        metro.addConnection("Zhongshan", "Shuanglian");
        metro.addConnection("Ximen", "Beimen"); // 獨立路線

        metro.printShortestPath("TPE", "Shuanglian");
        metro.printShortestPath("TPE", "Ximen"); // 無法到達
        metro.printShortestPath("Missing", "TPE");
    }
}