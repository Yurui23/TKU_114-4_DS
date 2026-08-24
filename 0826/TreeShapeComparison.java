class ShapeCompNode {
    int val;
    ShapeCompNode left, right;
    ShapeCompNode(int val) { this.val = val; }
}

public class TreeShapeComparison {
    private ShapeCompNode root;

    public void add(int val) {
        root = addRec(root, val);
    }

    private ShapeCompNode addRec(ShapeCompNode node, int val) {
        if (node == null) return new ShapeCompNode(val);
        if (val < node.val) node.left = addRec(node.left, val);
        else if (val > node.val) node.right = addRec(node.right, val);
        return node;
    }

    public int getHeight() {
        return heightRec(root);
    }

    private int heightRec(ShapeCompNode node) {
        if (node == null) return -1;
        return 1 + Math.max(heightRec(node.left), heightRec(node.right));
    }

    public int searchComparisons(int val) {
        int count = 0;
        ShapeCompNode curr = root;
        while (curr != null) {
            count++;
            if (val == curr.val) return count;
            if (val < curr.val) curr = curr.left;
            else curr = curr.right;
        }
        return count;
    }

    public void runAnalysis(String type, int[] insertData, int[] searchData, int missingKey) {
        root = null;
        for (int v : insertData) add(v);
        int totalFound = 0;
        for (int v : searchData) totalFound += searchComparisons(v);
        int missingCount = searchComparisons(missingKey);
        
        System.out.println(type + " - Height: " + getHeight() + " | Total Search Count: " + totalFound + " | Missing Key Count: " + missingCount);
    }

    public static void main(String[] args) {
        TreeShapeComparison tree = new TreeShapeComparison();
        int[] asc = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};
        int[] desc = {15, 14, 13, 12, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1};
        int[] bal = {8, 4, 12, 2, 6, 10, 14, 1, 3, 5, 7, 9, 11, 13, 15};

        tree.runAnalysis("Ascending", asc, asc, 99);
        tree.runAnalysis("Descending", desc, desc, 99);
        tree.runAnalysis("Balanced", bal, bal, 99);
    }
}