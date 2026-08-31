import java.util.PriorityQueue;
import java.util.NoSuchElementException;
import java.util.ArrayList;
import java.util.List;

public class IntegerMinHeap {
    private PriorityQueue<Integer> minHeap = new PriorityQueue<>();

    public void add(int val) {
        minHeap.offer(val);
    }

    public int peek() {
        if (minHeap.isEmpty()) {
            throw new NoSuchElementException("Heap is empty");
        }
        return minHeap.peek();
    }

    public int removeMin() {
        if (minHeap.isEmpty()) {
            throw new NoSuchElementException("Heap is empty");
        }
        return minHeap.poll();
    }

    public int size() {
        return minHeap.size();
    }

    public boolean isEmpty() {
        return minHeap.isEmpty();
    }

    public static void main(String[] args) {
        IntegerMinHeap heap = new IntegerMinHeap();
        int[] data = {45, 20, 80, 15, 30};
        for (int val : data) {
            heap.add(val);
        }

        System.out.println("移除驗證非遞減順序:");
        List<Integer> removedItems = new ArrayList<>();
        while (!heap.isEmpty()) {
            int val = heap.removeMin();
            removedItems.add(val);
            System.out.print(val + " ");
        }
        System.out.println();
        
        try {
            heap.peek();
        } catch (NoSuchElementException e) {
            System.out.println("Empty peek 拋出例外成功: " + e.getMessage());
        }
    }
}