class SkewNode {
    int val;
    SkewNode left, right;
    SkewNode(int val) { this.val = val; }
}

public class SkewedBstReport {
    private SkewNode root;
    private int size;

    public void insert(int val) {
        root = insertRec(root, val);
        size++;
    }

    private SkewNode insertRec(SkewNode node, int val) {
        if (node == null) return new SkewNode(val);
        if (val < node.val) node.left = insertRec(node.left, val);
        else if (val > node.val) node.right = insertRec(node.right, val);
        return node;
    }

    public int getHeight() {
        return heightRec(root);
    }

    private int heightRec(SkewNode node) {
        if (node == null) return -1;
        return 1 + Math.max(heightRec(node.left), heightRec(node.right));
    }

    public int searchComparisons(int val) {
        int count = 0;
        SkewNode current = root;
        while (current != null) {
            count++;
            if (val == current.val) return count;
            if (val < current.val) current = current.left;
            else current = current.right;
        }
        return count;
    }

    public int getSize() {
        return size;
    }

    public static void main(String[] args) {
        SkewedBstReport skewed = new SkewedBstReport();
        for (int i = 1; i <= 7; i++) {
            skewed.insert(i * 10);
        }

        SkewedBstReport balanced = new SkewedBstReport();
        int[] balData = {40, 20, 60, 10, 30, 50, 70};
        for (int val : balData) {
            balanced.insert(val);
        }

        System.out.println(skewed.getSize());
        System.out.println(skewed.getHeight());
        System.out.println(skewed.searchComparisons(70));

        System.out.println("---");
        
        System.out.println(balanced.getSize());
        System.out.println(balanced.getHeight());
        System.out.println(balanced.searchComparisons(70));
    }
}