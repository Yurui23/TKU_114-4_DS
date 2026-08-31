import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class WebsiteLinkGraph {
    private Map<String, Set<String>> outgoing = new HashMap<>();
    private Map<String, Integer> incomingCount = new HashMap<>();

    public void addPage(String page) {
        outgoing.putIfAbsent(page, new HashSet<>());
        incomingCount.putIfAbsent(page, 0);
    }

    public void addLink(String from, String to) {
        addPage(from); addPage(to);
        if (outgoing.get(from).add(to)) {
            incomingCount.put(to, incomingCount.get(to) + 1);
        }
    }

    public List<String> getOutgoingLinks(String page) {
        List<String> links = new ArrayList<>(outgoing.getOrDefault(page, new HashSet<>()));
        Collections.sort(links);
        return links;
    }

    public int getIncomingCount(String page) {
        return incomingCount.getOrDefault(page, 0);
    }

    public List<String> getNoIncomingPages() {
        List<String> result = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : incomingCount.entrySet()) {
            if (entry.getValue() == 0) result.add(entry.getKey());
        }
        Collections.sort(result);
        return result;
    }

    public List<String> getNoOutgoingPages() {
        List<String> result = new ArrayList<>();
        for (Map.Entry<String, Set<String>> entry : outgoing.entrySet()) {
            if (entry.getValue().isEmpty()) result.add(entry.getKey());
        }
        Collections.sort(result);
        return result;
    }

    public static void main(String[] args) {
        WebsiteLinkGraph wg = new WebsiteLinkGraph();
        wg.addLink("Home", "About");
        wg.addLink("Home", "Contact");
        System.out.println("No Incoming: " + wg.getNoIncomingPages());
        System.out.println("No Outgoing: " + wg.getNoOutgoingPages());
    }
}