import java.util.ArrayList;
import java.util.List;

public class CollisionBucketReport {
    public static void generateReport(int[] keys, int bucketCount) {
        if (bucketCount <= 0 || keys == null) return;

        List<List<Integer>> buckets = new ArrayList<>(bucketCount);
        for (int i = 0; i < bucketCount; i++) {
            buckets.add(new ArrayList<>());
        }

        for (int key : keys) {
            // 使用 Math.floorMod 正確處理負數 key
            int index = Math.floorMod(key, bucketCount);
            buckets.get(index).add(key);
        }

        int totalCollisions = 0;
        int maxChain = 0;

        for (int i = 0; i < buckets.size(); i++) {
            List<Integer> bucket = buckets.get(i);
            System.out.println("Bucket " + i + ": " + bucket);
            if (bucket.size() > 1) {
                totalCollisions += (bucket.size() - 1);
            }
            if (bucket.size() > maxChain) {
                maxChain = bucket.size();
            }
        }
        
        System.out.println("Total Collisions: " + totalCollisions);
        System.out.println("Max Chain Length: " + maxChain);
    }

    public static void main(String[] args) {
        int[] keys = {15, 25, -5, 35, 10, 15}; // 包含負數與重複值
        generateReport(keys, 10);
    }
}