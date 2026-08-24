class TraceNode {
    int val;
    TraceNode left, right;
    TraceNode(int val) { this.val = val; }
}

public class BstSearchTrace {
    private TraceNode root;

    public void insert(int val) {
        root = insertRec(root, val);
    }

    private TraceNode insertRec(TraceNode node, int val) {
        if (node == null) return new TraceNode(val);
        if (val < node.val) node.left = insertRec(node.left, val);
        else if (val > node.val) node.right = insertRec(node.right, val);
        return node;
    }

    public void search(int val) {
        System.out.println("Searching for: " + val);
        int comparisons = 0;
        TraceNode current = root;
        while (current != null) {
            comparisons++;
            System.out.print("Current: " + current.val + " | Count: " + comparisons + " | Direction: ");
            if (val == current.val) {
                System.out.println("Found");
                return;
            } else if (val < current.val) {
                System.out.println("Left");
                current = current.left;
            } else {
                System.out.println("Right");
                current = current.right;
            }
        }
        System.out.println("Missing");
    }

    public static void main(String[] args) {
        BstSearchTrace bst = new BstSearchTrace();
        bst.insert(50);
        bst.insert(30);
        bst.insert(70);
        bst.insert(20);
        bst.insert(40);

        bst.search(50);
        System.out.println("---");
        bst.search(20);
        System.out.println("---");
        bst.search(30);
        System.out.println("---");
        bst.search(99);
    }
}