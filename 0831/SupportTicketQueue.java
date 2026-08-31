import java.util.PriorityQueue;

class Ticket implements Comparable<Ticket> {
    String id;
    int severity;
    int createdOrder;

    public Ticket(String id, int severity, int createdOrder) {
        this.id = id;
        this.severity = severity;
        this.createdOrder = createdOrder;
    }

    @Override
    public int compareTo(Ticket other) {
        if (this.severity != other.severity) {
            return Integer.compare(other.severity, this.severity); // 數字越大越優先
        }
        return Integer.compare(this.createdOrder, other.createdOrder); // 數字越小越早
    }
}

public class SupportTicketQueue {
    public static void main(String[] args) {
        PriorityQueue<Ticket> queue = new PriorityQueue<>();
        queue.offer(new Ticket("T001", 3, 1));
        queue.offer(new Ticket("T002", 5, 2));
        queue.offer(new Ticket("T003", 5, 3));
        queue.offer(new Ticket("T004", 1, 4));
        queue.offer(new Ticket("T005", 3, 5));

        while (!queue.isEmpty()) {
            Ticket t = queue.poll();
            System.out.println(t.id + "|" + t.severity + "|" + t.createdOrder);
        }
    }
}