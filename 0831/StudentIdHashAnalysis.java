import java.util.ArrayList;
import java.util.List;

public class StudentIdHashAnalysis {

    public static void analyze(List<String> studentIds, int bucketCount) {
        if (bucketCount <= 0 || studentIds == null || studentIds.isEmpty()) {
            System.out.println("Invalid input");
            return;
        }

        int[] bucketSizes = new int[bucketCount];
        for (String id : studentIds) {
            if (id == null) continue;
            // 使用 hashCode 並用 Math.floorMod 防護負數
            int index = Math.floorMod(id.hashCode(), bucketCount);
            bucketSizes[index]++;
        }

        int totalCollisions = 0;
        int maxChain = 0;
        int occupiedBuckets = 0;
        int totalItems = 0;

        for (int i = 0; i < bucketCount; i++) {
            int size = bucketSizes[i];
            System.out.println("Bucket " + i + " count: " + size);
            if (size > 0) {
                occupiedBuckets++;
                totalItems += size;
                if (size > 1) {
                    totalCollisions += (size - 1);
                }
            }
            if (size > maxChain) {
                maxChain = size;
            }
        }

        double avgChain = occupiedBuckets == 0 ? 0 : (double) totalItems / occupiedBuckets;

        System.out.println("Total Collisions: " + totalCollisions);
        System.out.println("Max Chain Length: " + maxChain);
        System.out.println("Average Chain (Occupied): " + String.format("%.2f", avgChain));
    }

    public static void main(String[] args) {
        List<String> ids = new ArrayList<>();
        ids.add("S001");
        ids.add("S002");
        ids.add("S003");
        ids.add("S004");
        ids.add("S005");
        
        System.out.println("--- 桶數 3 分析 ---");
        analyze(ids, 3);
        System.out.println("\n--- 桶數 10 分析 ---");
        analyze(ids, 10);
    }
}