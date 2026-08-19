import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

class ServiceTicket {
    private String id;
    private String description;

    public ServiceTicket(String id, String description) {
        this.id = id != null ? id : "UNKNOWN";
        this.description = description != null ? description : "No description";
    }

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return "[" + id + "] " + description;
    }
}

public class ServiceCenterWorkflow {
    private Map<String, ServiceTicket> ticketMap = new HashMap<>();
    private Deque<ServiceTicket> waitingQueue = new ArrayDeque<>();
    private Deque<ServiceTicket> completionStack = new ArrayDeque<>();
    private Set<String> idSet = new HashSet<>();

    public void createTicket(ServiceTicket ticket) {
        if (ticket == null || idSet.contains(ticket.getId())) {
            System.out.println("Create failed: Duplicate ID or null.");
            return;
        }
        idSet.add(ticket.getId());
        ticketMap.put(ticket.getId(), ticket);
        waitingQueue.offer(ticket);
        System.out.println("Created: " + ticket);
    }

    public void processNext() {
        ServiceTicket ticket = waitingQueue.poll();
        if (ticket == null) {
            System.out.println("Process failed: Queue is empty.");
            return;
        }
        completionStack.push(ticket);
        System.out.println("Processed: " + ticket);
    }

    public void cancelWaiting(String id) {
        if (id == null || !idSet.contains(id)) {
            System.out.println("Cancel failed: ID not found.");
            return;
        }
        
        boolean removed = waitingQueue.removeIf(t -> t.getId().equals(id));
        if (removed) {
            idSet.remove(id);
            ticketMap.remove(id);
            System.out.println("Canceled: " + id);
        } else {
            System.out.println("Cancel failed: Ticket " + id + " is not in waiting queue.");
        }
    }

    public void undoLastCompletion() {
        if (completionStack.isEmpty()) {
            System.out.println("Undo failed: No completed tickets.");
            return;
        }
        ServiceTicket ticket = completionStack.pop();
        waitingQueue.addFirst(ticket);
        System.out.println("Undo: " + ticket.getId() + " returned to front of queue.");
    }

    public ServiceTicket findById(String id) {
        return ticketMap.get(id);
    }

    public void printSummary() {
        System.out.println("--- Summary ---");
        System.out.println("Waiting: " + waitingQueue.size());
        System.out.println("Completed: " + completionStack.size());
        System.out.println("Total Tracked: " + ticketMap.size());
        System.out.println("---------------");
    }

    public static void main(String[] args) {
        ServiceCenterWorkflow center = new ServiceCenterWorkflow();
        
        System.out.println("測試空 Queue:");
        center.processNext();
        
        System.out.println("\n測試取消不存在 ID:");
        center.cancelWaiting("TK999");
        
        System.out.println("\n新增資料:");
        center.createTicket(new ServiceTicket("TK001", "Issue 1"));
        center.createTicket(new ServiceTicket("TK002", "Issue 2"));
        center.createTicket(new ServiceTicket("TK003", "Issue 3"));
        
        System.out.println("\n測試重複 ID:");
        center.createTicket(new ServiceTicket("TK001", "Duplicate"));
        
        System.out.println("\n處理資料:");
        center.processNext();
        center.processNext();
        
        System.out.println("\n連續兩次 undo:");
        center.undoLastCompletion();
        center.undoLastCompletion();
        
        System.out.println("\n再嘗試 undo 空 stack:");
        center.undoLastCompletion();
        
        System.out.println("\nSummary:");
        center.printSummary();
    }
}