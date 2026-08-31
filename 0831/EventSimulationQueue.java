import java.util.PriorityQueue;

class SimulationEvent implements Comparable<SimulationEvent> {
    int time;
    String type;
    int sequence;

    public SimulationEvent(int time, String type, int sequence) {
        this.time = time;
        this.type = type;
        this.sequence = sequence;
    }

    @Override
    public int compareTo(SimulationEvent other) {
        if (this.time != other.time) {
            return Integer.compare(this.time, other.time); // 時間先後
        }
        return Integer.compare(this.sequence, other.sequence); // Sequence 先後
    }
}

public class EventSimulationQueue {
    private PriorityQueue<SimulationEvent> queue = new PriorityQueue<>();

    public void addEvent(int time, String type, int sequence) {
        queue.offer(new SimulationEvent(time, type, sequence));
    }

    public void cancelEvent(int targetSequence) {
        queue.removeIf(e -> e.sequence == targetSequence);
    }

    public void runSimulation() {
        System.out.println("--- 執行紀錄 ---");
        while (!queue.isEmpty()) {
            SimulationEvent e = queue.poll();
            System.out.println("Time: " + e.time + " | Seq: " + e.sequence + " | Event: " + e.type);
        }
    }

    public static void main(String[] args) {
        EventSimulationQueue sim = new EventSimulationQueue();
        sim.addEvent(10, "Login", 1);
        sim.addEvent(30, "Logout", 3);
        sim.addEvent(10, "Click", 2);
        sim.addEvent(20, "Purchase", 4);
        
        sim.cancelEvent(2); // 取消 Click 事件
        sim.runSimulation();
    }
}