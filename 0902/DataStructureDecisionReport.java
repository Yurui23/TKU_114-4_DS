import java.util.*;

public class DataStructureDecisionReport {
    static class Decision {
        String requirement;
        String dsSelection;
        String reason;
        String bigO;

        Decision(String req, String ds, String reason, String bigO) {
            this.requirement = req;
            this.dsSelection = ds;
            this.reason = reason;
            this.bigO = bigO;
        }
    }

    public static void printReport() {
        List<Decision> report = new ArrayList<>();
        report.add(new Decision("1. 依序保存任務，無須頻繁從中刪除", "ArrayList", "循序寫入與讀取極快", "Add O(1), Get O(1)"));
        report.add(new Decision("2. 頻繁在清單頭尾新增或刪除資料", "LinkedList/Deque", "指標操作無須搬移陣列", "AddFirst/Last O(1)"));
        report.add(new Decision("3. 保證資料不重複且不需維持順序", "HashSet", "使用 Hash 防重，速度最快", "Add/Search O(1)"));
        report.add(new Decision("4. 保證資料不重複且需依加入順序走訪", "LinkedHashSet", "結合 Hash 與鏈結串列", "Add/Search O(1)"));
        report.add(new Decision("5. 依據特定 ID 快速查閱與更新物件", "HashMap", "Key-Value 對應", "Put/Get O(1)"));
        report.add(new Decision("6. 需持續取得優先權最高或最低的任務", "PriorityQueue (Heap)", "自動維護極值在 Root", "Peek O(1), Poll O(log N)"));
        report.add(new Decision("7. 需要快速執行區間查詢 (Range Query)", "TreeMap / BST", "樹狀結構維持排序狀態", "Search O(log N)"));
        report.add(new Decision("8. 處理 Undo/Redo 或巢狀配對", "Stack (ArrayDeque)", "後進先出 (LIFO) 特性", "Push/Pop O(1)"));
        report.add(new Decision("9. 處理排隊叫號或廣度優先搜尋 (BFS)", "Queue (ArrayDeque)", "先進先出 (FIFO) 特性", "Offer/Poll O(1)"));
        report.add(new Decision("10. 模擬道路網、社交關係，找出最短路徑", "Graph (Adj List)", "有效表示多對多關係", "BFS O(V+E)"));
        report.add(new Decision("11. 表達上下層級、目錄結構或組織圖", "Tree (N-ary Tree)", "層級式關係明確", "Traversal O(N)"));
        report.add(new Decision("12. 記錄兩個獨立集合間的連通元件", "Disjoint Set (Union-Find)", "極速合併與查詢連通性", "Union/Find O(α(N))"));

        for (Decision d : report) {
            System.out.println(String.format("需求: %s\n選擇: %-20s | Big-O: %-20s\n理由: %s\n", 
                d.requirement, d.dsSelection, d.bigO, d.reason));
        }
    }

    public static void main(String[] args) {
        printReport();
    }
}