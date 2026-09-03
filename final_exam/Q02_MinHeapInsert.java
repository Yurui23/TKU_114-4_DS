import java.util.ArrayList;
import java.util.List;

public class Q02_MinHeapInsert {
    private List<Integer> heap = new ArrayList<>();

    public void add(int value) {
        heap.add(value);
        bubbleUp(heap.size() - 1);
    }

    private void bubbleUp(int index) {
        while (index > 0) {
            int parent = (index - 1) / 2;
            if (heap.get(index) < heap.get(parent)) {
                int temp = heap.get(index);
                heap.set(index, heap.get(parent));
                heap.set(parent, temp);
                index = parent;
            } else {
                break;
            }
        }
    }

    public Integer peek() {
        if (heap.isEmpty()) return null;
        return heap.get(0);
    }

    public int size() {
        return heap.size();
    }

    public List<Integer> snapshot() {
        return new ArrayList<>(heap);
    }

    public boolean isValidMinHeap() {
        if (heap.isEmpty()) return true;
        for (int i = 0; i <= (heap.size() - 2) / 2; i++) {
            int left = 2 * i + 1;
            int right = 2 * i + 2;
            if (left < heap.size() && heap.get(i) > heap.get(left)) return false;
            if (right < heap.size() && heap.get(i) > heap.get(right)) return false;
        }
        return true;
    }

    // 測試用主程式
    public static void main(String[] args) {
        Q02_MinHeapInsert minHeap = new Q02_MinHeapInsert();
        minHeap.add(30);
        minHeap.add(10);
        minHeap.add(20);
        System.out.println("Snapshot: " + minHeap.snapshot());
        System.out.println("Peek Min: " + minHeap.peek());
        System.out.println("Is Valid: " + minHeap.isValidMinHeap());
    }
}