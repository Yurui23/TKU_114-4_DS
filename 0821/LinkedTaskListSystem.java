class Task {
    private String id;
    private String description;

    public Task(String id, String description) {
        this.id = id != null ? id : "";
        this.description = description != null ? description : "";
    }

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return "[" + id + "] " + description;
    }
}

class TaskNode {
    Task data;
    TaskNode next;

    public TaskNode(Task data) {
        this.data = data;
    }
}

class TaskLinkedList {
    private TaskNode head;
    private int count;

    public Task findById(String id) {
        if (id == null) return null;
        TaskNode current = head;
        while (current != null) {
            if (current.data.getId().equals(id)) {
                return current.data;
            }
            current = current.next;
        }
        return null;
    }

    public void addFirst(Task task) {
        if (task == null || findById(task.getId()) != null) return;
        TaskNode newNode = new TaskNode(task);
        newNode.next = head;
        head = newNode;
        count++;
    }

    public void addLast(Task task) {
        if (task == null || findById(task.getId()) != null) return;
        TaskNode newNode = new TaskNode(task);
        if (head == null) {
            head = newNode;
        } else {
            TaskNode current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
        count++;
    }

    public void insertAfter(String existingId, Task task) {
        if (task == null || existingId == null || findById(task.getId()) != null) return;
        TaskNode current = head;
        while (current != null) {
            if (current.data.getId().equals(existingId)) {
                TaskNode newNode = new TaskNode(task);
                newNode.next = current.next;
                current.next = newNode;
                count++;
                return;
            }
            current = current.next;
        }
    }

    public void removeById(String id) {
        if (id == null || head == null) return;
        
        if (head.data.getId().equals(id)) {
            head = head.next;
            count--;
            return;
        }
        
        TaskNode current = head;
        while (current.next != null) {
            if (current.next.data.getId().equals(id)) {
                current.next = current.next.next;
                count--;
                return;
            }
            current = current.next;
        }
    }

    public int size() {
        return count;
    }

    public void printAll() {
        TaskNode current = head;
        while (current != null) {
            System.out.print(current.data + " -> ");
            current = current.next;
        }
        System.out.println("null");
    }
}

public class LinkedTaskListSystem {
    public static void main(String[] args) {
        TaskLinkedList list = new TaskLinkedList();
        
        System.out.println("測試空 List 刪除:");
        list.removeById("T001");
        
        list.addLast(new Task("T001", "A"));
        list.addLast(new Task("T002", "B"));
        list.addLast(new Task("T003", "C"));
        list.addLast(new Task("T004", "D"));
        list.addLast(new Task("T005", "E"));
        
        System.out.println("加入重複 ID:");
        list.addFirst(new Task("T001", "Duplicate"));
        
        System.out.println("刪除 Head (T001):");
        list.removeById("T001");
        list.printAll();
        
        System.out.println("刪除 Middle (T003):");
        list.removeById("T003");
        list.printAll();
        
        System.out.println("刪除 Tail (T005):");
        list.removeById("T005");
        list.printAll();
        
        System.out.println("找不到 ID 的測試:");
        list.removeById("T999");
        Task notFound = list.findById("T999");
        System.out.println(notFound == null ? "T999 Not Found" : notFound);
    }
}