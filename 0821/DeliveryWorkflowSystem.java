import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

class DeliveryTask {
    private String deliveryId;
    private String address;

    public DeliveryTask(String deliveryId, String address) {
        this.deliveryId = (deliveryId == null || deliveryId.trim().isEmpty()) ? "UNKNOWN" : deliveryId;
        this.address = (address == null || address.trim().isEmpty()) ? "Unknown" : address;
    }

    public String getDeliveryId() {
        return deliveryId;
    }

    @Override
    public String toString() {
        return deliveryId + " -> " + address;
    }
}

public class DeliveryWorkflowSystem {
    private Map<String, DeliveryTask> taskMap = new HashMap<>();
    private Queue<DeliveryTask> waitingQueue = new LinkedList<>();
    private Deque<DeliveryTask> completedStack = new ArrayDeque<>();

    public void addTask(DeliveryTask task) {
        if (task == null) {
            return;
        }
        if (taskMap.containsKey(task.getDeliveryId())) {
            System.out.println("新增失敗，配送編號已存在: " + task.getDeliveryId());
            return;
        }
        taskMap.put(task.getDeliveryId(), task);
        waitingQueue.offer(task);
        System.out.println("新增配送: " + task);
    }

    public void processNext() {
        DeliveryTask task = waitingQueue.poll();
        if (task == null) {
            System.out.println("目前沒有等待配送的任務。");
            return;
        }
        completedStack.push(task);
        System.out.println("處理完成: " + task);
    }

    public void undoLast() {
        if (completedStack.isEmpty()) {
            System.out.println("沒有可撤銷的完成紀錄。");
            return;
        }
        DeliveryTask task = completedStack.pop();
        ((LinkedList<DeliveryTask>) waitingQueue).addFirst(task);
        System.out.println("撤銷配送: " + task + " (已加回等待隊列前端)");
    }

    public void query(String deliveryId) {
        if (deliveryId == null || deliveryId.trim().isEmpty()) {
            return;
        }
        DeliveryTask task = taskMap.get(deliveryId);
        if (task != null) {
            System.out.println("查詢結果: " + task);
        } else {
            System.out.println("找不到配送編號: " + deliveryId);
        }
    }

    public void printStatistics() {
        System.out.println("--- 配送統計 ---");
        System.out.println("系統總任務數: " + taskMap.size());
        System.out.println("等待配送數: " + waitingQueue.size());
        System.out.println("已完成配送數: " + completedStack.size());
        System.out.println("----------------");
    }

    public static void main(String[] args) {
        DeliveryWorkflowSystem system = new DeliveryWorkflowSystem();
        system.addTask(new DeliveryTask("D001", "Taipei"));
        system.addTask(new DeliveryTask("D002", "Taichung"));
        system.addTask(new DeliveryTask("D001", "Kaohsiung"));

        system.processNext();
        system.processNext();
        system.processNext();
        
        system.printStatistics();
        
        system.undoLast();
        system.printStatistics();
        
        system.query("D001");
        system.query("D999");
    }
}