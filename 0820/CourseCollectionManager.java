import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

// 建立學生的課程紀錄
class StudentRecord {
    private String studentId;
    private int score;
    private Set<String> tags;

    public StudentRecord(String studentId, int score, Set<String> tags) {
        this.studentId = (studentId == null) ? "UNKNOWN" : studentId;
        this.score = Math.max(0, score); // 防護負數分數
        this.tags = (tags != null) ? new HashSet<>(tags) : new HashSet<>();
    }

    public String getStudentId() { return studentId; }
    public int getScore() { return score; }
    public void setScore(int score) { this.score = Math.max(0, score); }
    public Set<String> getTags() { return tags; }

    // 依據 studentId 決定物件的唯一性 (供 Set 與 Map 正確運作)
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudentRecord that = (StudentRecord) o;
        return Objects.equals(studentId, that.studentId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentId);
    }

    @Override
    public String toString() {
        return "學號: " + studentId + " | 分數: " + score + " | Tags: " + tags;
    }
}

public class CourseCollectionManager {
    // 依規定使用三種 Collection
    private List<StudentRecord> list = new ArrayList<>();
    private Set<StudentRecord> set = new HashSet<>();
    private Map<String, StudentRecord> map = new HashMap<>();

    // 加入資料 (處理重複學號，若重複則覆寫，並保持三者一致性)
    public void addRecord(StudentRecord record) {
        if (record == null) return;
        
        String id = record.getStudentId();
        if (map.containsKey(id)) {
            StudentRecord oldRecord = map.get(id);
            list.remove(oldRecord);
            set.remove(oldRecord);
        }
        
        list.add(record);
        set.add(record);
        map.put(id, record);
    }

    // 1. 更新分數
    public void updateScore(String studentId, int score) {
        if (studentId != null && map.containsKey(studentId)) {
            map.get(studentId).setScore(score);
        }
    }

    // 2. 尋找 Tag (處理空白與 Null)
    public List<StudentRecord> findByTag(String tag) {
        List<StudentRecord> result = new ArrayList<>();
        String searchTag = (tag == null) ? "" : tag.trim();
        
        for (StudentRecord record : list) {
            for (String t : record.getTags()) {
                if (t != null && t.trim().equals(searchTag)) {
                    result.add(record);
                    break; // 找到就換下一位學生
                }
            }
        }
        return result;
    }

    // 3. 分數分佈統計
    public Map<String, Integer> scoreDistribution() {
        Map<String, Integer> dist = new LinkedHashMap<>();
        dist.put("A", 0); dist.put("B", 0); dist.put("C", 0); 
        dist.put("D", 0); dist.put("F", 0);

        for (StudentRecord record : list) {
            int s = record.getScore();
            if (s >= 90) dist.put("A", dist.get("A") + 1);
            else if (s >= 80) dist.put("B", dist.get("B") + 1);
            else if (s >= 70) dist.put("C", dist.get("C") + 1);
            else if (s >= 60) dist.put("D", dist.get("D") + 1);
            else dist.put("F", dist.get("F") + 1);
        }
        return dist;
    }

    // 4. 回傳排名前 count 名 (大於人數回傳所有，同分依學號升冪)
    public List<StudentRecord> top(int count) {
        if (count <= 0) return new ArrayList<>();
        
        List<StudentRecord> sortedList = new ArrayList<>(list);
        sortedList.sort((r1, r2) -> {
            int scoreCompare = Integer.compare(r2.getScore(), r1.getScore()); // 分數降冪
            if (scoreCompare != 0) return scoreCompare;
            return r1.getStudentId().compareTo(r2.getStudentId()); // 遇同分則學號升冪
        });

        return sortedList.subList(0, Math.min(count, sortedList.size()));
    }

    // 5. 移除低於 minimum 分數的資料 (嚴格規定：保持三集合一致性)
    public void removeBelow(int minimum) {
        // 使用 Iterator.remove() 避免 ConcurrentModificationException
        Iterator<StudentRecord> iterator = list.iterator();
        while (iterator.hasNext()) {
            StudentRecord record = iterator.next();
            if (record.getScore() < minimum) {
                iterator.remove(); // 從 List 移除
                set.remove(record); // 從 Set 移除
                map.remove(record.getStudentId()); // 從 Map 移除
            }
        }
    }

    // 驗證三種集合狀態的輔助方法
    public void printStatus() {
        System.out.println("【集合一致性檢查】List size: " + list.size() + " | Set size: " + set.size() + " | Map size: " + map.size());
    }

    public static void main(String[] args) {
        CourseCollectionManager manager = new CourseCollectionManager();

        // 建立測試資料 (包含至少 6 筆，具備重複學號 S001、同分 85、空白 Tag)
        manager.addRecord(new StudentRecord("S001", 85, new HashSet<>(Arrays.asList("Java", "Backend"))));
        manager.addRecord(new StudentRecord("S002", 92, new HashSet<>(Arrays.asList("Python", "AI"))));
        manager.addRecord(new StudentRecord("S003", 55, new HashSet<>(Arrays.asList("C++", "  ")))); // 空白 tag "  "
        manager.addRecord(new StudentRecord("S004", 85, new HashSet<>(Arrays.asList("Java", "Frontend")))); // 同分 (與S001)
        manager.addRecord(new StudentRecord("S005", 78, new HashSet<>(Arrays.asList("Network", "")))); // 空白 tag ""
        manager.addRecord(new StudentRecord("S006", 45, new HashSet<>(Arrays.asList("Math"))));
        
        // 測試重複學號：再次加入 S001 (將會覆寫原來的 85 分變成 98 分)
        manager.addRecord(new StudentRecord("S001", 98, new HashSet<>(Arrays.asList("Java", "Advanced")))); 

        System.out.println("--- 初始資料 (S001 被成功覆寫，集合保持一致) ---");
        for (StudentRecord r : manager.list) System.out.println(r);
        manager.printStatus();

        System.out.println("\n--- 1. 測試 updateScore (S005 改為 80分) ---");
        manager.updateScore("S005", 80);
        System.out.println(manager.map.get("S005"));

        System.out.println("\n--- 2. 測試 findByTag (尋找空白/全形空白 Tag) ---");
        List<StudentRecord> blankTagStudents = manager.findByTag("  "); // 輸入空白也能精準抓出
        for (StudentRecord r : blankTagStudents) System.out.println(r);

        System.out.println("\n--- 3. 測試 scoreDistribution ---");
        Map<String, Integer> dist = manager.scoreDistribution();
        dist.forEach((grade, c) -> System.out.println("等級 " + grade + ": " + c + " 人"));

        System.out.println("\n--- 4. 測試 top (取前 10 名，大於總人數) ---");
        List<StudentRecord> topList = manager.top(10);
        for (StudentRecord r : topList) System.out.println(r);

        System.out.println("\n--- 5. 測試 removeBelow (移除小於 60 分) ---");
        manager.removeBelow(60);
        for (StudentRecord r : manager.list) System.out.println(r);
        manager.printStatus(); // 確保移除後 List, Set, Map 數量完全一致
    }
}