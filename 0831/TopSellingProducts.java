import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

class Product implements Comparable<Product> {
    String id;
    int sales;

    public Product(String id, int sales) {
        this.id = id;
        this.sales = sales;
    }

    @Override
    public int compareTo(Product other) {
        if (this.sales != other.sales) {
            return Integer.compare(this.sales, other.sales); // 銷量小優先淘汰 (Min Heap)
        }
        return other.id.compareTo(this.id); // 銷量相同時，字串「大」的優先淘汰 (保留字典序小者)
    }
}

public class TopSellingProducts {
    public static List<String> getTopK(List<Product> records, int k) {
        if (k <= 0 || records == null) return new ArrayList<>();

        // 合併銷量
        Map<String, Integer> salesMap = new HashMap<>();
        for (Product p : records) {
            if (p != null && p.id != null) {
                salesMap.put(p.id, salesMap.getOrDefault(p.id, 0) + Math.max(0, p.sales));
            }
        }

        // 使用 Min Heap 保留 Top-K
        PriorityQueue<Product> minHeap = new PriorityQueue<>();
        for (Map.Entry<String, Integer> entry : salesMap.entrySet()) {
            minHeap.offer(new Product(entry.getKey(), entry.getValue()));
            if (minHeap.size() > k) {
                minHeap.poll();
            }
        }

        // 轉換結果並反轉（從高到低）
        List<Product> topProducts = new ArrayList<>();
        while (!minHeap.isEmpty()) {
            topProducts.add(minHeap.poll());
        }
        Collections.reverse(topProducts);

        List<String> result = new ArrayList<>();
        for (Product p : topProducts) {
            result.add(p.id + "(" + p.sales + ")");
        }
        return result;
    }

    public static void main(String[] args) {
        List<Product> records = new ArrayList<>();
        records.add(new Product("ItemB", 100));
        records.add(new Product("ItemA", 50));
        records.add(new Product("ItemA", 50)); // 合併後 100
        records.add(new Product("ItemC", 200));

        // Top 2：ItemC (200), ItemA (100) -> 銷量相同時 A 優先於 B
        System.out.println(getTopK(records, 2));
    }
}