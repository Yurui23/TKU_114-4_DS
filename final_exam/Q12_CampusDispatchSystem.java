import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

public class Q12_CampusDispatchSystem {
    
    public record Request(String id, String location, int priority, long sequence) {}

    private Map<String, List<String>> adjList = new HashMap<>();
    private Map<String, Request> requestMap = new HashMap<>();
    private PriorityQueue<Request> pq = new PriorityQueue<>(
        Comparator.comparingInt(Request::priority)
                  .thenComparingLong(Request::sequence)
    );

    public boolean addLocation(String location) {
        if (location == null || adjList.containsKey(location)) return false;
        adjList.put(location, new ArrayList<>());
        return true;
    }

    public boolean addRoad(String first, String second) {
        if (first == null || second == null) return false;
        if (!adjList.containsKey(first) || !adjList.containsKey(second)) return false;
        if (first.equals(second)) return false;

        adjList.get(first).add(second);
        adjList.get(second).add(first);
        return true;
    }

    public boolean submit(Request request) {
        if (request == null || request.id() == null || requestMap.containsKey(request.id())) {
            return false;
        }
        if (!adjList.containsKey(request.location())) {
            return false;
        }
        
        requestMap.put(request.id(), request);
        pq.offer(request);
        return true;
    }

    public Request nextReachable(String serviceCenter) {
        if (serviceCenter == null || !adjList.containsKey(serviceCenter)) return null;
        
        Set<String> reachable = new HashSet<>();
        Queue<String> q = new ArrayDeque<>();
        q.offer(serviceCenter);
        reachable.add(serviceCenter);
        
        while (!q.isEmpty()) {
            String curr = q.poll();
            for (String neighbor : adjList.get(curr)) {
                if (!reachable.contains(neighbor)) {
                    q.offer(neighbor);
                    reachable.add(neighbor);
                }
            }
        }

        List<Request> unreachableRequests = new ArrayList<>();
        Request target = null;
        
        while (!pq.isEmpty()) {
            Request req = pq.poll();
            if (reachable.contains(req.location())) {
                target = req;
                break;
            } else {
                unreachableRequests.add(req);
            }
        }
        
        for (Request req : unreachableRequests) {
            pq.offer(req);
        }
        
        if (target != null) {
            requestMap.remove(target.id());
        }
        
        return target;
    }

    public List<String> route(String start, String target) {
        List<String> path = new ArrayList<>();
        if (start == null || target == null) return path;
        if (!adjList.containsKey(start) || !adjList.containsKey(target)) return path;

        if (start.equals(target)) {
            path.add(start);
            return path;
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
                    visited.add(neighbor);
                    predecessor.put(neighbor, curr);
                }
            }
        }

        if (!found) return path;

        String step = target;
        while (step != null) {
            path.add(step);
            step = predecessor.get(step);
        }
        Collections.reverse(path);
        return path;
    }

    public int pendingCount() {
        return requestMap.size();
    }

    // 測試用主程式
    public static void main(String[] args) {
        Q12_CampusDispatchSystem sys = new Q12_CampusDispatchSystem();
        sys.addLocation("Center");
        sys.addLocation("Dorm");
        sys.addRoad("Center", "Dorm");
        
        sys.submit(new Request("Req1", "Dorm", 1, 100L));
        
        System.out.println("Pending Count: " + sys.pendingCount());
        System.out.println("Route Center to Dorm: " + sys.route("Center", "Dorm"));
        System.out.println("Next Reachable from Center: " + sys.nextReachable("Center"));
    }
}