public class RecursiveCallReport {
    public static int sum(int[] data, int index) {
        if (data == null || index >= data.length || index < 0) {
            System.out.println("Index: " + index + " | Base Case | Return: 0");
            return 0;
        }
        int currentVal = data[index];
        int recursiveResult = sum(data, index + 1);
        int returnVal = currentVal + recursiveResult;
        System.out.println("Index: " + index + " | Current: " + currentVal + " | RecursiveResult: " + recursiveResult + " | Return: " + returnVal);
        return returnVal;
    }

    public static void main(String[] args) {
        sum(new int[]{3, 7, 2}, 0);
        System.out.println("---");
        sum(new int[]{42}, 0);
        System.out.println("---");
        sum(new int[]{}, 0);
        System.out.println("---");
        sum(null, 0);
    }
}