import java.util.Arrays;

class CircularQueue<T> {
    private Object[] array;
    private int front;
    private int rear;
    private int size;
    private int capacity;

    public CircularQueue(int capacity) {
        this.capacity = capacity > 0 ? capacity : 4;
        this.array = new Object[this.capacity];
        this.front = 0;
        this.rear = -1;
        this.size = 0;
    }

    public void enqueue(T item) {
        if (size == capacity) {
            System.out.println("enqueue " + item + " (Queue is full)");
            return;
        }
        rear = (rear + 1) % capacity;
        array[rear] = item;
        size++;
        printState("enqueue " + item);
    }

    @SuppressWarnings("unchecked")
    public T dequeue() {
        if (size == 0) {
            System.out.println("dequeue (Queue is empty)");
            return null;
        }
        T item = (T) array[front];
        array[front] = null;
        front = (front + 1) % capacity;
        size--;
        printState("dequeue");
        return item;
    }
    
    @SuppressWarnings("unchecked")
    public T poll() {
        if (size == 0) return null;
        T item = (T) array[front];
        array[front] = null;
        front = (front + 1) % capacity;
        size--;
        return item;
    }

    private void printState(String action) {
        System.out.println(action + " \t-> array: " + Arrays.toString(array) + 
                           ", front: " + front + 
                           ", rear: " + rear + 
                           ", size: " + size);
    }

    public boolean isEmpty() {
        return size == 0;
    }
}

public class CircularQueuePractice {
    public static void main(String[] args) {
        CircularQueue<String> queue = new CircularQueue<>(4);
        
        queue.enqueue("A");
        queue.enqueue("B");
        queue.enqueue("C");
        queue.dequeue();
        queue.dequeue();
        queue.enqueue("D");
        queue.enqueue("E");
        queue.enqueue("F");
        queue.dequeue();
        queue.enqueue("G");

        System.out.println("\n--- 依 FIFO 順序取出所有元素 ---");
        while (!queue.isEmpty()) {
            System.out.println(queue.poll());
        }
    }
}