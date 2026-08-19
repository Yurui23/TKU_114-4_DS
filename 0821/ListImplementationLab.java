import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ListImplementationLab {
    public static void performOperations(List<Integer> list, String listType) {
        if (list == null) return;
        for (int i = 1; i <= 5; i++) {
            list.add(i * 10);
        }
        list.add(2, 99);
        int searchIndex = list.indexOf(30);
        list.remove(Integer.valueOf(20));
        int sum = 0;
        for (int val : list) {
            sum += val;
        }
        System.out.println(listType + " 執行結果 -> Search index: " + searchIndex + ", Sum: " + sum);
    }

    public static void main(String[] args) {
        List<Integer> arrayList = new ArrayList<>();
        List<LinkedListLabTest> tempCheck = null;
        List<Integer> linkedList = new LinkedList<>();

        performOperations(arrayList, "ArrayList");
        performOperations(linkedList, "LinkedList");

        System.out.println("\n--- 內部成本差異說明 ---");
        System.out.println("ArrayList 底層使用連續記憶體陣列：尾端新增極快，但中間插入與刪除需要搬移大量元素；隨機存取 (get) 效率極高，時間複雜度為 O(1)。");
        System.out.println("LinkedList 底層使用雙向鏈結串列：節點分散在記憶體中，中間插入與刪除只需調整前後節點指標 O(1)（若已知節點位置），但隨機存取需從頭開始走訪，時間複雜度為 O(n)。");
    }
}

class LinkedListLabTest {}