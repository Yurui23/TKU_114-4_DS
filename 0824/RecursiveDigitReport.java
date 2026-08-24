public class RecursiveDigitReport {

    public static int digitSum(int n) {
        if (n == 0) return 0;
        int absN = Math.abs(n);
        return (absN % 10) + digitSum(absN / 10);
    }

    public static int digitCount(int n) {
        if (n == 0) return 1;
        return countHelper(Math.abs(n));
    }

    private static int countHelper(int n) {
        if (n == 0) return 0;
        return 1 + countHelper(n / 10);
    }

    public static int countDigit(int n, int target) {
        if (n == 0 && target == 0) return 1;
        if (n == 0) return 0;
        return countDigitHelper(Math.abs(n), Math.abs(target));
    }

    private static int countDigitHelper(int n, int target) {
        if (n == 0) return 0;
        int match = (n % 10 == target) ? 1 : 0;
        return match + countDigitHelper(n / 10, target);
    }

    public static void main(String[] args) {
        int[] testCases = {50205, 0, -731};
        for (int val : testCases) {
            System.out.println("Value: " + val);
            System.out.println("Sum: " + digitSum(val));
            System.out.println("Count: " + digitCount(val));
            System.out.println("Count of 0: " + countDigit(val, 0));
            System.out.println("---");
        }
    }
}