import java.util.ArrayDeque;
import java.util.Deque;

class Customer {
    private String name;

    public Customer(String name) {
        this.name = (name == null || name.trim().isEmpty()) ? "Guest" : name;
    }

    public String getName() {
        return name;
    }
}

class CounterQueue {
    private Deque<Customer> queue = new ArrayDeque<>();

    public void addCustomer(Customer customer) {
        if (customer != null) {
            queue.offer(customer);
            System.out.println(customer.getName() + " 已取號加入佇列。");
        }
    }

    public void peekNext() {
        Customer next = queue.peek();
        if (next == null) {
            System.out.println("目前沒有等待中的顧客（空佇列）。");
        } else {
            System.out.println("下一位叫號: " + next.getName());
        }
    }

    public void serveNext() {
        Customer served = queue.poll();
        if (served == null) {
            System.out.println("佇列已空，無法提供服務。");
        } else {
            System.out.println("正在為 " + served.getName() + " 辦理業務。");
        }
    }

    public int getWaitingCount() {
        return queue.size();
    }
}

public class CounterWaitingQueue {
    public static void main(String[] args) {
        CounterQueue cq = new CounterQueue();
        cq.peekNext();
        cq.serveNext();

        cq.addCustomer(new Customer("Alice"));
        cq.addCustomer(new Customer("Bob"));
        cq.addCustomer(new Customer("Charlie"));

        System.out.println("目前等待人數: " + cq.getWaitingCount());
        cq.peekNext();

        cq.serveNext();
        System.out.println("剩餘等待人數: " + cq.getWaitingCount());
        
        cq.serveNext();
        cq.serveNext();
        cq.serveNext();
    }
}