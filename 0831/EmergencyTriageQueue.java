import java.util.PriorityQueue;

class Patient implements Comparable<Patient> {
    String id;
    int severity;
    int arrivalOrder;

    public Patient(String id, int severity, int arrivalOrder) {
        this.id = id;
        this.severity = severity;
        this.arrivalOrder = arrivalOrder;
    }

    @Override
    public int compareTo(Patient other) {
        if (this.severity != other.severity) {
            return Integer.compare(other.severity, this.severity); // 危機程度大優先
        }
        return Integer.compare(this.arrivalOrder, other.arrivalOrder); // 到院順序小優先
    }
}

public class EmergencyTriageQueue {
    private PriorityQueue<Patient> queue = new PriorityQueue<>();
    private int arrivalCounter = 0;

    public void checkIn(String id, int severity) {
        queue.offer(new Patient(id, severity, ++arrivalCounter));
        System.out.println(id + " 已報到 (緊急程度: " + severity + ")");
    }

    public void peekNext() {
        if (queue.isEmpty()) {
            System.out.println("目前無人候診");
        } else {
            System.out.println("下一位候診: " + queue.peek().id);
        }
    }

    public void callNext() {
        if (queue.isEmpty()) {
            System.out.println("空佇列，無法叫號");
        } else {
            System.out.println("請 " + queue.poll().id + " 進入診間");
        }
    }

    public void getWaitingCount() {
        System.out.println("目前候診人數: " + queue.size());
    }

    public static void main(String[] args) {
        EmergencyTriageQueue triage = new EmergencyTriageQueue();
        triage.callNext();
        triage.checkIn("P01", 3);
        triage.checkIn("P02", 5);
        triage.checkIn("P03", 3);
        
        triage.getWaitingCount();
        triage.peekNext();
        triage.callNext();
        triage.callNext();
        triage.callNext();
        triage.callNext();
    }
}