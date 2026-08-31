import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

public class LowestKPriceTracker {
    public static List<Integer> getLowestKPrices(List<Integer> prices, int k) {
        if (k <= 0 || prices == null) {
            return new ArrayList<>();
        }

        // 使用 Max Heap 來保留最小的 K 個元素
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());

        for (Integer price : prices) {
            if (price == null || price < 0) continue; // 忽略 null 與負數
            
            maxHeap.offer(price);
            if (maxHeap.size() > k) {
                maxHeap.poll(); // 剔除較大的價格
            }
        }

        List<Integer> result = new ArrayList<>(maxHeap);
        Collections.sort(result); // 依價格遞增排列
        return result;
    }

    public static void main(String[] args) {
        List<Integer> prices = java.util.Arrays.asList(150, 50, null, 200, 30, -10, 80, 10);
        System.out.println(getLowestKPrices(prices, 3));
        System.out.println(getLowestKPrices(prices, 0));
    }
}