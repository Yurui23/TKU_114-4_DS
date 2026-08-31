import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class InterestSetComparison {
    
    // 使用 TreeSet 確保輸出前已排序 (避免 HashSet 報告順序不固定)
    public static Set<String> getUnion(Set<String> s1, Set<String> s2) {
        Set<String> result = new TreeSet<>(s1);
        result.addAll(s2);
        return result;
    }

    public static Set<String> getIntersection(Set<String> s1, Set<String> s2) {
        Set<String> result = new TreeSet<>(s1);
        result.retainAll(s2);
        return result;
    }

    public static Set<String> getFirstOnly(Set<String> s1, Set<String> s2) {
        Set<String> result = new TreeSet<>(s1);
        result.removeAll(s2);
        return result;
    }

    public static Set<String> getSecondOnly(Set<String> s1, Set<String> s2) {
        Set<String> result = new TreeSet<>(s2);
        result.removeAll(s1);
        return result;
    }

    public static void main(String[] args) {
        Set<String> alice = new HashSet<>(Set.of("Music", "Art", "Sports"));
        Set<String> bob = new HashSet<>(Set.of("Sports", "Gaming", "Music"));

        System.out.println("Union: " + getUnion(alice, bob));
        System.out.println("Intersection: " + getIntersection(alice, bob));
        System.out.println("First Only (Alice): " + getFirstOnly(alice, bob));
        System.out.println("Second Only (Bob): " + getSecondOnly(alice, bob));
        System.out.println("Original Set untouched: " + alice.size() + " elements");
    }
}