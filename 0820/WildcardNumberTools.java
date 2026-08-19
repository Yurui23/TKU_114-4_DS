import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WildcardNumberTools {

    public static double average(List<? extends Number> values) {
        if (values == null || values.isEmpty()) {
            return 0.0;
        }
        
        double sum = 0.0;
        int count = 0;
        
        for (Number number : values) {
            if (number != null) {
                sum += number.doubleValue();
                count++;
            }
        }
        
        return count == 0 ? 0.0 : sum / count;
    }

    public static double maximum(List<? extends Number> values) {
        if (values == null || values.isEmpty()) {
            return Double.NaN;
        }
        
        double max = Double.NEGATIVE_INFINITY;
        boolean hasValidNumber = false;
        
        for (Number number : values) {
            if (number != null) {
                double currentValue = number.doubleValue();
                if (currentValue > max || !hasValidNumber) {
                    max = currentValue;
                    hasValidNumber = true;
                }
            }
        }
        
        return hasValidNumber ? max : Double.NaN;
    }

    public static void addRange(List<? super Integer> target, int start, int end) {
        if (target == null || start > end) {
            return;
        }
        
        for (int i = start; i <= end; i++) {
            target.add(i);
        }
    }

    public static void main(String[] args) {
        List<Integer> intList = Arrays.asList(10, 20, 30);
        System.out.println(average(intList));
        System.out.println(maximum(intList));

        List<Double> doubleList = Arrays.asList(1.5, 2.5, 3.5);
        System.out.println(average(doubleList));
        System.out.println(maximum(doubleList));

        List<Integer> emptyList = new ArrayList<>();
        System.out.println(average(emptyList));
        System.out.println(maximum(emptyList));

        List<Number> targetList = new ArrayList<>();
        addRange(targetList, 5, 8);
        System.out.println(targetList);
        
        addRange(targetList, 10, 5);
        System.out.println(targetList);
    }
}