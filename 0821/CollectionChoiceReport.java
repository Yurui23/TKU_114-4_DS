import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class CollectionChoiceReport {
    public static void main(String[] args) {
        System.out.println("--- 1. 保留搜尋紀錄且允許重複 ---");
        System.out.println("Interface: List");
        System.out.println("Implementation: ArrayList");
        List<String> searchHistory = new ArrayList<>();
        searchHistory.add("Java");
        searchHistory.add("Python");
        searchHistory.add("Java");
        System.out.println("操作結果: " + searchHistory);
        System.out.println();

        System.out.println("--- 2. 保存不重複會員編號 ---");
        System.out.println("Interface: Set");
        System.out.println("Implementation: HashSet");
        Set<String> memberIds = new HashSet<>();
        memberIds.add("M001");
        memberIds.add("M002");
        memberIds.add("M001");
        System.out.println("操作結果: " + memberIds);
        System.out.println();

        System.out.println("--- 3. 以學號查詢成績 ---");
        System.out.println("Interface: Map");
        System.out.println("Implementation: HashMap");
        Map<String, Integer> grades = new HashMap<>();
        grades.put("S001", 95);
        grades.put("S002", 88);
        System.out.println("操作結果: S001 成績為 " + grades.get("S001"));
        System.out.println();

        System.out.println("--- 4. 依到達順序處理列印工作 ---");
        System.out.println("Interface: Queue");
        System.out.println("Implementation: ArrayDeque");
        Queue<String> printJobs = new ArrayDeque<>();
        printJobs.offer("Doc1.pdf");
        printJobs.offer("Doc2.pdf");
        System.out.println("操作結果: 處理 " + printJobs.poll() + "，剩餘 " + printJobs);
        System.out.println();

        System.out.println("--- 5. 復原最近操作 ---");
        System.out.println("Interface: Deque");
        System.out.println("Implementation: ArrayDeque");
        Deque<String> undoStack = new ArrayDeque<>();
        undoStack.push("Type A");
        undoStack.push("Type B");
        System.out.println("操作結果: 復原 " + undoStack.pop() + "，剩餘 " + undoStack);
    }
}