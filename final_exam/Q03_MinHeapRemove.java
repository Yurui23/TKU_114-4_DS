import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Q03_MinHeapRemove {
    private List<Integer> heap;

    public Q03_MinHeapRemove(List<Integer> values) {
        heap = new ArrayList<>();
        if (values != null) {
            for (Integer val : values) {
                if (val != null) {
                    heap.add(val);
                }
            }
            // Bottom-up heapify
            for (int i = (heap.size() / 2) - 1; i >= 0; i--) {
                bubbleDown(i);
            }
        }
    }

    public Integer removeMin() {
        if (heap.isEmpty()) return null;
        int min = heap.get(0);
        int last = heap.remove(heap.size() - 1);
        
        if (!heap.isEmpty()) {
            heap.set(0, last);
            bubbleDown(0);
        }
        return min;
    }

    private void bubbleDown(int index) {
        int size = heap.size();
        while (true) {
            int left = 2 * index + 1;
            int right = 2 * index + 2;
            int smallest = index;

            if (left < size && heap.get(left) < heap.get(smallest)) {
                smallest = left;
            }
            if (right < size && heap.get(right) < heap.get(smallest)) {
                smallest = right;
            }

            if (smallest != index) {
                int temp = heap.get(index);
                heap.set(index, heap.get(smallest));
                heap.set(smallest, temp);
                index = smallest;
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

    // 測試用主程式
    public static void main(String[] args) {
        Q03_MinHeapRemove heapObj = new Q03_MinHeapRemove(Arrays.asList(40, 10, 30, 20, 50));
        System.out.println("Initial Snapshot: " + heapObj.snapshot());
        System.out.println("Removed: " + heapObj.removeMin());
        System.out.println("After Remove Snapshot: " + heapObj.snapshot());
    }
}