public class RecursiveTextTools {

    public static String reverse(String s) {
        if (s == null || s.isEmpty()) return s;
        return reverse(s.substring(1)) + s.charAt(0);
    }

    public static boolean isPalindrome(String s) {
        if (s == null) return false;
        String cleaned = s.replaceAll("\\s+", "").toLowerCase();
        return isPalHelper(cleaned, 0, cleaned.length() - 1);
    }

    private static boolean isPalHelper(String s, int left, int right) {
        if (left >= right) return true;
        if (s.charAt(left) != s.charAt(right)) return false;
        return isPalHelper(s, left + 1, right - 1);
    }

    public static int countCharacter(String s, char c) {
        if (s == null || s.isEmpty()) return 0;
        int match = (Character.toLowerCase(s.charAt(0)) == Character.toLowerCase(c)) ? 1 : 0;
        return match + countCharacter(s.substring(1), c);
    }

    public static void main(String[] args) {
        System.out.println(reverse("Hello"));
        System.out.println(isPalindrome("Level"));
        System.out.println(isPalindrome("A nut for a jar of tuna"));
        System.out.println(isPalindrome(""));
        System.out.println(isPalindrome("X"));
        System.out.println(countCharacter("Application", 'a'));
    }
}