import java.util.*;

class ServiceRequest implements Comparable<ServiceRequest> {
    String id;
    int priority;
    ServiceRequest(String id, int priority) { this.id = id; this.priority = priority; }

    @Override
    public int compareTo(ServiceRequest other) {
        return Integer.compare(other.priority, this.priority); // 優先權高優先
    }
    @Override
    public String toString() { return id + "(P" + priority + ")"; }
}

public class ServiceRequestsSystem {
    private Map<String, ServiceRequest> requestMap = new HashMap<>();
    private PriorityQueue<ServiceRequest> pq = new PriorityQueue<>();

    public void addRequest(String id, int priority) {
        if (requestMap.containsKey(id)) return;
        ServiceRequest req = new ServiceRequest(id, priority);
        requestMap.put(id, req);
        pq.offer(req);
    }

    public ServiceRequest getNext() {
        ServiceRequest next = pq.poll();
        if (next != null) {
            requestMap.remove(next.id); // 兩份結構一致
        }
        return next;
    }

    public boolean cancelRequest(String id) {
        ServiceRequest req = requestMap.get(id);
        if (req != null) {
            requestMap.remove(id);
            pq.remove(req); // 兩份結構一致
            return true;
        }
        return false;
    }

    public ServiceRequest find(String id) {
        return requestMap.get(id);
    }

    public static void main(String[] args) {
        ServiceRequestsSystem sys = new ServiceRequestsSystem();
        sys.addRequest("R1", 1);
        sys.addRequest("R2", 5);
        sys.addRequest("R3", 3);

        System.out.println("Cancel R2: " + sys.cancelRequest("R2"));
        System.out.println("Find R2 (should be null): " + sys.find("R2"));
        System.out.println("Next request: " + sys.getNext()); // R3
        
        System.out.println("Cancel missing R99: " + sys.cancelRequest("R99"));
        System.out.println("Empty queue next: " + new ServiceRequestsSystem().getNext());
    }
}