public class RecursiveArrayStatistics {

    public static int maximum(int[] arr) {
        if (arr == null || arr.length == 0) throw new IllegalArgumentException();
        return maxHelper(arr, 0);
    }

    private static int maxHelper(int[] arr, int index) {
        if (index == arr.length - 1) return arr[index];
        return Math.max(arr[index], maxHelper(arr, index + 1));
    }

    public static int minimum(int[] arr) {
        if (arr == null || arr.length == 0) throw new IllegalArgumentException();
        return minHelper(arr, 0);
    }

    private static int minHelper(int[] arr, int index) {
        if (index == arr.length - 1) return arr[index];
        return Math.min(arr[index], minHelper(arr, index + 1));
    }

    public static int countAbove(int[] arr, int threshold) {
        if (arr == null || arr.length == 0) throw new IllegalArgumentException();
        return countHelper(arr, threshold, 0);
    }

    private static int countHelper(int[] arr, int threshold, int index) {
        if (index == arr.length) return 0;
        int count = arr[index] > threshold ? 1 : 0;
        return count + countHelper(arr, threshold, index + 1);
    }

    public static void main(String[] args) {
        int[] data = {3, 8, 1, 9, 4, 2};
        System.out.println(maximum(data));
        System.out.println(minimum(data));
        System.out.println(countAbove(data, 3));
    }
}