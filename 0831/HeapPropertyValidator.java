import java.util.List;

public class HeapPropertyValidator {
    public static boolean isMinHeap(List<Integer> list) {
        if (list == null) return false;
        if (list.size() <= 1) return true;

        for (int i = 0; i <= (list.size() - 2) / 2; i++) {
            int left = 2 * i + 1;
            int right = 2 * i + 2;
            
            if (left < list.size() && list.get(i) > list.get(left)) return false;
            if (right < list.size() && list.get(i) > list.get(right)) return false;
        }
        return true;
    }

    public static boolean isMaxHeap(List<Integer> list) {
        if (list == null) return false;
        if (list.size() <= 1) return true;

        for (int i = 0; i <= (list.size() - 2) / 2; i++) {
            int left = 2 * i + 1;
            int right = 2 * i + 2;
            
            if (left < list.size() && list.get(i) < list.get(left)) return false;
            if (right < list.size() && list.get(i) < list.get(right)) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        List<Integer> minHeap = java.util.Arrays.asList(10, 20, 30, 40, 50);
        List<Integer> maxHeap = java.util.Arrays.asList(90, 80, 70, 60, 50);
        List<Integer> invalid = java.util.Arrays.asList(10, 90, 20);

        System.out.println("isMinHeap (valid): " + isMinHeap(minHeap));
        System.out.println("isMaxHeap (valid): " + isMaxHeap(maxHeap));
        System.out.println("isMinHeap (invalid): " + isMinHeap(invalid));
        System.out.println("isMaxHeap (null): " + isMaxHeap(null));
    }
}