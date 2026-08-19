import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CourseTagReport {
    public static void main(String[] args) {
        String[] inputTags = {"Java", "Python", "Java", "C++", "Python", "Java", "Spring"};

        List<String> tagList = new ArrayList<>();
        Set<String> tagSet = new LinkedHashSet<>();
        Map<String, Integer> tagCountMap = new HashMap<>();

        for (String tag : inputTags) {
            if (tag != null && !tag.trim().isEmpty()) {
                tagList.add(tag);
                tagSet.add(tag);
                tagCountMap.put(tag, tagCountMap.getOrDefault(tag, 0) + 1);
            }
        }

        System.out.println("List 用途：保存原始輸入順序與所有重複資料。");
        System.out.println("List 內容：" + tagList);
        System.out.println("-------------------------");

        System.out.println("Set 用途：過濾重複資料，確保集合中只保留唯一的標籤。");
        System.out.println("Set 內容：" + tagSet);
        System.out.println("-------------------------");

        System.out.println("Map 用途：建立標籤(Key)與其出現次數(Value)的對應關係，以進行統計。");
        for (Map.Entry<String, Integer> entry : tagCountMap.entrySet()) {
            System.out.println("標籤: " + entry.getKey() + " | 次數: " + entry.getValue());
        }
    }
}