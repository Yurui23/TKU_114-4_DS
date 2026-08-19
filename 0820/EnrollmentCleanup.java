import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class EnrollmentCleanup {
    public static void main(String[] args) {
        List<String> names = new ArrayList<>(Arrays.asList(
            "Alice", null, "Bob", "   ", "Charlie", "Alice", "", "Dave", "Bob", null, "Eve"
        ));

        System.out.println("清理前名單: " + names);

        Iterator<String> iterator = names.iterator();
        while (iterator.hasNext()) {
            String name = iterator.next();
            if (name == null || name.trim().isEmpty()) {
                iterator.remove();
            }
        }

        Set<String> seenNames = new HashSet<>();
        Set<String> duplicateNames = new HashSet<>();

        for (String name : names) {
            if (!seenNames.add(name)) {
                duplicateNames.add(name);
            }
        }

        System.out.println("清理後名單: " + names);
        System.out.println("重複報名者: " + duplicateNames);
    }
}