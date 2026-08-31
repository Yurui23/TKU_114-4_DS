import java.util.*;

public class IterativeDfsTrace {
    private Map<String, List<String>> adjList = new LinkedHashMap<>();

    public void addEdge(String u, String v) {
        adjList.putIfAbsent(u, new ArrayList<>());
        adjList.putIfAbsent(v, new ArrayList<>());
        adjList.get(u).add(v);
        adjList.get(v).add(u); // 穩定順序 List
    }

    public void traceDfs(String start) {
        if (start == null || !adjList.containsKey(start)) {
            System.out.println("Start vertex not found or graph empty.");
            return;
        }

        Deque<String> stack = new ArrayDeque<>();
        Set<String> visited = new LinkedHashSet<>();

        stack.push(start);
        printTrace("PUSH " + start, stack, visited);

        while (!stack.isEmpty()) {
            String curr = stack.pop();
            printTrace("POP " + curr, stack, visited);

            if (!visited.contains(curr)) {
                visited.add(curr);
                printTrace("VISIT " + curr, stack, visited);

                // 為了與常見 DFS 走訪順序一致，將鄰居反轉 push
                List<String> neighbors = adjList.getOrDefault(curr, new ArrayList<>());
                for (int i = neighbors.size() - 1; i >= 0; i--) {
                    String n = neighbors.get(i);
                    if (!visited.contains(n)) {
                        stack.push(n);
                        printTrace("PUSH " + n, stack, visited);
                    }
                }
            }
        }
    }

    private void printTrace(String action, Deque<String> stack, Set<String> visited) {
        System.out.println(String.format("%-15s | Stack: %-15s | Visited: %s", action, stack.toString(), visited.toString()));
    }

    public static void main(String[] args) {
        IterativeDfsTrace graph = new IterativeDfsTrace();
        graph.addEdge("1", "2");
        graph.addEdge("1", "3");
        graph.addEdge("2", "4");

        System.out.println("--- 一般案例 trace ---");
        graph.traceDfs("1");
        
        System.out.println("\n--- Missing 案例 trace ---");
        graph.traceDfs("X");
    }
}