public class GenericArrayTools {

    public static <T> int countMatches(T[] data, T target) {
        if (data == null) {
            return 0;
        }
        
        int count = 0;
        for (T item : data) {
            if (target == null) {
                if (item == null) {
                    count++;
                }
            } else {
                if (target.equals(item)) {
                    count++;
                }
            }
        }
        return count;
    }

    public static <T> T last(T[] data) {
        if (data == null || data.length == 0) {
            return null;
        }
        return data[data.length - 1];
    }

    public static <T> void swap(T[] data, int first, int second) {
        if (data == null || data.length == 0) {
            return;
        }
        if (first < 0 || first >= data.length || second < 0 || second >= data.length) {
            return;
        }
        
        T temp = data[first];
        data[first] = data[second];
        data[second] = temp;
    }

    public static void main(String[] args) {
        Integer[] numbers = {1, 5, 3, 5, 2, 5};
        System.out.println("Matches for 5: " + countMatches(numbers, 5));
        System.out.println("Matches in null array: " + countMatches(null, 5));

        String[] words = {"Apple", "Banana", "Cherry"};
        System.out.println("Last element: " + last(words));
        System.out.println("Last in empty array: " + last(new String[0]));

        swap(words, 0, 2);
        System.out.println("After swap (0, 2): " + words[0] + ", " + words[1] + ", " + words[2]);
        
        swap(words, -1, 10);
        System.out.println("After invalid swap: " + words[0] + ", " + words[1] + ", " + words[2]);
    }
}