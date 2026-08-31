import java.util.*;

public class IntegratedStructureAudit {
    
    public void auditSituation(String scenario, String proposedDS) {
        String diagnosis;
        boolean isReasonable = false;

        // 規則評量與診斷
        if (proposedDS.equals("HashMap") && scenario.contains("排序")) {
            diagnosis = "錯誤。HashMap 平均 O(1) 是基於雜湊映射，並不保證元素順序。需排序應使用 TreeMap。";
        } else if (proposedDS.equals("BST") && scenario.contains("極值") && !scenario.contains("範圍")) {
            diagnosis = "不佳。只取極值應使用 Heap(PriorityQueue)，O(1) 查極值，而非 BST。";
        } else if (proposedDS.equals("List") && scenario.contains("唯一") || scenario.contains("不重複")) {
            diagnosis = "錯誤。List 無法防止重複。應改用 HashSet。";
        } else if (proposedDS.contains("PriorityQueue") && proposedDS.contains("HashMap") && scenario.contains("取消")) {
            isReasonable = true;
            diagnosis = "合理。同一系統同時使用 HashMap (O(1) 查詢取消) 與 PriorityQueue (O(1) 取得最優先任務) 可滿足複合需求。";
        } else if (proposedDS.equals("Graph") && scenario.contains("路徑")) {
            isReasonable = true;
            diagnosis = "合理。找尋路徑應使用 Graph 搭配 BFS/DFS。";
        } else {
            isReasonable = true;
            diagnosis = "合理。此結構能滿足需求。";
        }

        System.out.println("情境: " + scenario);
        System.out.println("提案結構: " + proposedDS);
        System.out.println("判斷結果: " + (isReasonable ? "[PASS]" : "[FAIL]") + " " + diagnosis);
        System.out.println("--------------------------------------------------");
    }

    public static void main(String[] args) {
        IntegratedStructureAudit audit = new IntegratedStructureAudit();
        
        audit.auditSituation("需要依 ID 快速查找請求，並支援隨時取消請求", "PriorityQueue 與 HashMap");
        audit.auditSituation("輸出排行榜清單，必須隨時依照分數排序", "HashMap");
        audit.auditSituation("登記所有抵達的 IP 位址且不允許重複記錄", "List");
        audit.auditSituation("導航系統中尋找起點到終點的最少轉乘站點", "Graph");
    }
}