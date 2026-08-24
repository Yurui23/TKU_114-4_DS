import java.util.ArrayList;
import java.util.List;

class RangeStatNode {
    int val;
    RangeStatNode left, right;
    RangeStatNode(int val) { this.val = val; }
}

public class BstRangeStatistics {
    private RangeStatNode root;

    public void add(int val) {
        root = addRec(root, val);
    }

    private RangeStatNode addRec(RangeStatNode node, int val) {
        if (node == null) return new RangeStatNode(val);
        if (val < node.val) node.left = addRec(node.left, val);
        else if (val > node.val) node.right = addRec(node.right, val);
        return node;
    }

    public List<Integer> valuesBetween(int low, int high) {
        List<Integer> list = new ArrayList<>();
        if (low > high) return list;
        valuesRec(root, low, high, list);
        return list;
    }

    private void valuesRec(RangeStatNode node, int low, int high, List<Integer> list) {
        if (node == null) return;
        if (low < node.val) valuesRec(node.left, low, high, list);
        if (node.val >= low && node.val <= high) list.add(node.val);
        if (high > node.val) valuesRec(node.right, low, high, list);
    }

    public int countBetween(int low, int high) {
        if (low > high) return 0;
        return countRec(root, low, high);
    }

    private int countRec(RangeStatNode node, int low, int high) {
        if (node == null) return 0;
        int count = 0;
        if (low < node.val) count += countRec(node.left, low, high);
        if (node.val >= low && node.val <= high) count++;
        if (high > node.val) count += countRec(node.right, low, high);
        return count;
    }

    public int sumBetween(int low, int high) {
        if (low > high) return 0;
        return sumRec(root, low, high);
    }

    private int sumRec(RangeStatNode node, int low, int high) {
        if (node == null) return 0;
        int sum = 0;
        if (low < node.val) sum += sumRec(node.left, low, high);
        if (node.val >= low && node.val <= high) sum += node.val;
        if (high > node.val) sum += sumRec(node.right, low, high);
        return sum;
    }

    public static void main(String[] args) {
        BstRangeStatistics bst = new BstRangeStatistics();
        int[] data = {50, 30, 70, 20, 40, 60, 80};
        for (int d : data) bst.add(d);

        System.out.println(bst.valuesBetween(35, 65));
        System.out.println(bst.countBetween(35, 65));
        System.out.println(bst.sumBetween(35, 65));
        
        System.out.println(bst.valuesBetween(90, 100));
        System.out.println(bst.valuesBetween(60, 40)); 
    }
}