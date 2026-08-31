import java.util.Arrays;
import java.util.NoSuchElementException;

public class ArrayMinHeap {
    private int[] data;
    private int size;

    public ArrayMinHeap(int initialCapacity) {
        data = new int[Math.max(1, initialCapacity)];
        size = 0;
    }

    public void add(int val) {
        if (size == data.length) {
            data = Arrays.copyOf(data, data.length * 2);
        }
        data[size] = val;
        bubbleUp(size);
        size++;
    }

    private void bubbleUp(int index) {
        while (index > 0) {
            int parentIndex = (index - 1) / 2;
            if (data[index] < data[parentIndex]) {
                swap(index, parentIndex);
                index = parentIndex;
            } else {
                break;
            }
        }
    }

    public int remove() {
        if (size == 0) throw new NoSuchElementException("Heap is empty");
        int min = data[0];
        data[0] = data[size - 1];
        size--;
        bubbleDown(0);
        return min;
    }

    private void bubbleDown(int index) {
        while (true) {
            int left = 2 * index + 1;
            int right = 2 * index + 2;
            int smallest = index;

            if (left < size && data[left] < data[smallest]) smallest = left;
            if (right < size && data[right] < data[smallest]) smallest = right;

            if (smallest != index) {
                swap(index, smallest);
                index = smallest;
            } else {
                break;
            }
        }
    }

    public int peek() {
        if (size == 0) throw new NoSuchElementException("Heap is empty");
        return data[0];
    }

    public int[] snapshot() {
        return Arrays.copyOf(data, size);
    }

    private void swap(int i, int j) {
        int temp = data[i];
        data[i] = data[j];
        data[j] = temp;
    }

    public static void main(String[] args) {
        ArrayMinHeap minHeap = new ArrayMinHeap(5);
        for (int i = 20; i > 0; i--) {
            minHeap.add(i); // 測試至少 20 筆資料及自動擴容
        }
        System.out.println("Snapshot: " + Arrays.toString(minHeap.snapshot()));
        System.out.println("Min: " + minHeap.remove());
        System.out.println("New Min: " + minHeap.peek());
    }
}