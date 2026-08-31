import java.util.ArrayList;
import java.util.List;

public class MaxHeapInsertTrace {
    private List<Integer> heap = new ArrayList<>();

    public void add(int val) {
        heap.add(val);
        bubbleUp(heap.size() - 1);
        System.out.println("Insert " + val + " -> Snapshot: " + snapshot());
    }

    private void bubbleUp(int index) {
        while (index > 0) {
            int parentIndex = (index - 1) / 2;
            if (heap.get(index) > heap.get(parentIndex)) {
                int temp = heap.get(index);
                heap.set(index, heap.get(parentIndex));
                heap.set(parentIndex, temp);
                index = parentIndex;
            } else {
                break;
            }
        }
    }

    public Integer peekMax() {
        return heap.isEmpty() ? null : heap.get(0);
    }

    public List<Integer> snapshot() {
        return new ArrayList<>(heap);
    }

    public static void main(String[] args) {
        MaxHeapInsertTrace trace = new MaxHeapInsertTrace();
        int[] data = {25, 40, 10, 50, 30, 50};
        for (int val : data) {
            trace.add(val);
        }
        System.out.println("Final Root: " + trace.peekMax());
    }
}