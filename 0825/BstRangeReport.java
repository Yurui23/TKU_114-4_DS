class RangeNode {
    int val;
    RangeNode left, right;
    RangeNode(int val) { this.val = val; }
}

public class BstRangeReport {
    private RangeNode root;

    public void insert(int val) {
        root = insertRec(root, val);
    }

    private RangeNode insertRec(RangeNode node, int val) {
        if (node == null) return new RangeNode(val);
        if (val < node.val) node.left = insertRec(node.left, val);
        else if (val > node.val) node.right = insertRec(node.right, val);
        return node;
    }

    public Integer min() {
        if (root == null) return null;
        RangeNode current = root;
        while (current.left != null) current = current.left;
        return current.val;
    }

    public Integer max() {
        if (root == null) return null;
        RangeNode current = root;
        while (current.right != null) current = current.right;
        return current.val;
    }

    public void printRange(int low, int high) {
        if (low > high) return;
        printRangeRec(root, low, high);
        System.out.println();
    }

    private void printRangeRec(RangeNode node, int low, int high) {
        if (node == null) return;
        if (low < node.val) printRangeRec(node.left, low, high);
        if (low <= node.val && high >= node.val) {
            System.out.print(node.val + " ");
        }
        if (high > node.val) printRangeRec(node.right, low, high);
    }

    public static void main(String[] args) {
        BstRangeReport bst = new BstRangeReport();
        bst.insert(20);
        bst.insert(8);
        bst.insert(22);
        bst.insert(4);
        bst.insert(12);

        System.out.println(bst.min());
        System.out.println(bst.max());
        bst.printRange(10, 22);
        bst.printRange(30, 10);
    }
}