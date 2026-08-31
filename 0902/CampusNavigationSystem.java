import java.util.*;

class Location {
    String id;
    String name;
    Location(String id, String name) { this.id = id; this.name = name; }
}

public class CampusNavigationSystem {
    private Map<String, Location> locations = new HashMap<>();
    private Map<String, List<String>> adjList = new HashMap<>();

    public void addLocation(String id, String name) {
        locations.put(id, new Location(id, name));
        adjList.putIfAbsent(id, new ArrayList<>());
    }

    public void addRoad(String id1, String id2) {
        if (locations.containsKey(id1) && locations.containsKey(id2)) {
            adjList.get(id1).add(id2);
            adjList.get(id2).add(id1);
        }
    }

    public List<String> findShortestPath(String startId, String endId) {
        if (!locations.containsKey(startId) || !locations.containsKey(endId)) {
            return new ArrayList<>(); // 找不到 target 傳回 empty
        }

        Queue<String> queue = new ArrayDeque<>();
        Set<String> visited = new HashSet<>();
        Map<String, String> predecessor = new HashMap<>();

        queue.offer(startId);
        visited.add(startId); // offer 時加入
        boolean found = false;

        while (!queue.isEmpty()) {
            String curr = queue.poll();
            if (curr.equals(endId)) {
                found = true;
                break;
            }
            for (String neighbor : adjList.get(curr)) {
                if (!visited.contains(neighbor)) {
                    queue.offer(neighbor);
                    visited.add(neighbor); // offer 時加入
                    predecessor.put(neighbor, curr);
                }
            }
        }

        List<String> path = new ArrayList<>();
        if (!found) return path; // 找不到 target 傳回 empty

        String step = endId;
        while (step != null) {
            path.add(locations.get(step).name);
            step = predecessor.get(step);
        }
        Collections.reverse(path); // 回溯後 reverse
        return path;
    }

    public static void main(String[] args) {
        CampusNavigationSystem nav = new CampusNavigationSystem();
        nav.addLocation("L1", "Library");
        nav.addLocation("L2", "Dorm");
        nav.addLocation("L3", "Gym");
        nav.addRoad("L1", "L2");
        nav.addRoad("L2", "L3");

        System.out.println("Path (L1 -> L3): " + nav.findShortestPath("L1", "L3"));
        System.out.println("Path Missing Target: " + nav.findShortestPath("L1", "L99"));
    }
}