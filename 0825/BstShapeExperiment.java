class ShapeNode {
    int val;
    ShapeNode left, right;
    ShapeNode(int val) { this.val = val; }
}

public class BstShapeExperiment {
    private ShapeNode root;

    public void insert(int val) {
        root = insertRec(root, val);
    }

    private ShapeNode insertRec(ShapeNode node, int val) {
        if (node == null) return new ShapeNode(val);
        if (val < node.val) node.left = insertRec(node.left, val);
        else if (val > node.val) node.right = insertRec(node.right, val);
        return node;
    }

    public int getHeight() {
        return heightRec(root);
    }

    private int heightRec(ShapeNode node) {
        if (node == null) return -1;
        return 1 + Math.max(heightRec(node.left), heightRec(node.right));
    }

    public int getTotalSearchComparisons(int[] data) {
        int total = 0;
        for (int val : data) {
            int count = 0;
            ShapeNode current = root;
            while (current != null) {
                count++;
                if (val == current.val) break;
                if (val < current.val) current = current.left;
                else current = current.right;
            }
            total += count;
        }
        return total;
    }

    public static void main(String[] args) {
        int[] data1 = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};
        BstShapeExperiment t1 = new BstShapeExperiment();
        for (int v : data1) t1.insert(v);
        System.out.println(t1.getHeight() + " " + t1.getTotalSearchComparisons(data1));

        int[] data2 = {15, 14, 13, 12, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1};
        BstShapeExperiment t2 = new BstShapeExperiment();
        for (int v : data2) t2.insert(v);
        System.out.println(t2.getHeight() + " " + t2.getTotalSearchComparisons(data2));

        int[] data3 = {8, 4, 12, 2, 6, 10, 14, 1, 3, 5, 7, 9, 11, 13, 15};
        BstShapeExperiment t3 = new BstShapeExperiment();
        for (int v : data3) t3.insert(v);
        System.out.println(t3.getHeight() + " " + t3.getTotalSearchComparisons(data3));
    }
}