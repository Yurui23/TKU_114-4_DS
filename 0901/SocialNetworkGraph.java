import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class SocialNetworkGraph {
    private Map<String, Set<String>> adjList = new HashMap<>();

    public void addUser(String user) {
        adjList.putIfAbsent(user, new HashSet<>());
    }

    public void addFriend(String u, String v) {
        addUser(u); addUser(v);
        adjList.get(u).add(v);
        adjList.get(v).add(u); // Undirected
    }

    public void removeFriend(String u, String v) {
        if (adjList.containsKey(u) && adjList.containsKey(v)) {
            adjList.get(u).remove(v);
            adjList.get(v).remove(u);
        }
    }

    public List<String> getCommonFriends(String u, String v) {
        if (!adjList.containsKey(u) || !adjList.containsKey(v)) return new ArrayList<>();
        Set<String> common = new TreeSet<>(adjList.get(u)); // 用 TreeSet 保持順序
        common.retainAll(adjList.get(v));
        return new ArrayList<>(common);
    }

    public List<String> getIsolatedUsers() {
        List<String> isolated = new ArrayList<>();
        for (Map.Entry<String, Set<String>> entry : adjList.entrySet()) {
            if (entry.getValue().isEmpty()) {
                isolated.add(entry.getKey());
            }
        }
        return isolated;
    }

    public static void main(String[] args) {
        SocialNetworkGraph sn = new SocialNetworkGraph();
        sn.addFriend("Alice", "Bob");
        sn.addFriend("Alice", "Charlie");
        sn.addFriend("Bob", "Charlie");
        sn.addUser("David"); // Isolated

        System.out.println("Common friends of Alice and Bob: " + sn.getCommonFriends("Alice", "Bob"));
        System.out.println("Isolated users: " + sn.getIsolatedUsers());
    }
}